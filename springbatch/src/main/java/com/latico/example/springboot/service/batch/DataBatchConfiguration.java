package com.latico.example.springboot.service.batch;

import com.latico.example.springboot.bean.dto.Result;
import com.latico.example.springboot.dao.primary.JpaDataSourceConfigPrimary;
import com.latico.example.springboot.dao.primary.entity.Access;
import com.latico.example.springboot.service.listener.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * <PRE>
 *  @EnableBatchProcessing提供用于构建批处理作业的基本配置
 *
 * </PRE>
 * @Author: latico
 * @Date: 2019-06-20 17:26:04
 * @Version: 1.0
 */
@Configuration
@EnableBatchProcessing
public class DataBatchConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DataBatchConfiguration.class);

    @Resource
    private JobBuilderFactory jobBuilderFactory;    //用于构建JOB

    @Resource
    private StepBuilderFactory stepBuilderFactory;  //用于构建Step

    @Resource(name = JpaDataSourceConfigPrimary.entityManagerFactoryBeanName)
    private EntityManagerFactory emf;           //注入实例化Factory 访问数据

    @Resource
    private JobListener jobListener;            //简单的JOB listener

    /**
     * 一个简单基础的Job通常由一个或者多个Step组成
     */
    @Bean
    public Job dataHandleJob() {
        return jobBuilderFactory.get("dataHandleJob").
                incrementer(new RunIdIncrementer()).
                start(handleDataStep()).    //start是JOB执行的第一个step
//                next(xxxStep()).
//                next(xxxStep()).
//                ...
        listener(jobListener).      //设置了一个简单JobListener
                build();
    }

    /**
     * 一个简单基础的Step主要分为三个部分
     * ItemReader : 用于读取数据
     * ItemProcessor : 用于处理数据
     * ItemWriter : 用于写数据
     */
    @Bean
    public Step handleDataStep() {
        return stepBuilderFactory.get("getData").
                <Access, Result>chunk(100).        // <输入,输出> 。chunk通俗的讲类似于SQL的commit; 这里表示处理(processor)100条后写入(writer)一次。
                faultTolerant().retryLimit(3).retry(Exception.class).skipLimit(100).skip(Exception.class). //捕捉到异常就重试,重试100次还是异常,JOB就停止并标志失败
                reader(getDataReader()).         //指定ItemReader
                processor(getDataProcessor()).   //指定ItemProcessor
                writer(getDataWriter()).         //指定ItemWriter
                build();
    }

    @Bean
    public ItemReader<? extends Access> getDataReader() {
        //读取数据,这里可以用JPA,JDBC,JMS，按行读取文件 等方式 读入数据
        JpaPagingItemReader<Access> reader = new JpaPagingItemReader<>();
        //这里选择JPA方式读数据 一个简单的 native SQL
        String sqlQuery = "SELECT * FROM access";
        try {
            JpaNativeQueryProvider<Access> queryProvider = new JpaNativeQueryProvider<>();
            queryProvider.setSqlQuery(sqlQuery);
            queryProvider.setEntityClass(Access.class);
            queryProvider.afterPropertiesSet();
            reader.setEntityManagerFactory(emf);
//            每次读取10条
            reader.setPageSize(10);
            reader.setQueryProvider(queryProvider);
            reader.afterPropertiesSet();
            //所有ItemReader和ItemWriter实现都会在ExecutionContext提交之前将其当前状态存储在其中,如果不希望这样做,可以设置setSaveState(false)
            reader.setSaveState(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reader;
    }

    @Bean
    public ItemProcessor<Access, Result> getDataProcessor() {
        return new ItemProcessor<Access, Result>() {
            @Override
            public Result process(Access access) throws Exception {
                log.info("processor data : " + access.toString());  //模拟  假装处理数据,这里处理就是打印一下

//                转成结果进行输出
                Result result = new Result();
                result.setId(access.getId());
                result.setUsername(access.getUsername());
                result.setShopName(access.getShopName());
                result.setCategoryName(access.getCategoryName());
                result.setBrandName(access.getBrandName());
                result.setShopId(access.getShopId());
                result.setOmit(access.getOmit());
                result.setUpdateTime(access.getUpdateTime());
                result.setDeleteStatus(access.isDeleteStatus());
                result.setCreateTime(access.getCreateTime());
                result.setDescription(access.getDescription());

                return result;
            }
        };
    }

    @Bean
    public ItemWriter<Result> getDataWriter() {

       ItemWriter<Result> itemWriter = new ItemWriter<Result>() {
            @Override
            public void write(List<? extends Result> items) throws Exception {
                for (Result result : items) {
                    log.info("write data : " + result); //模拟 假装写数据 ,这里写真正写入数据的逻辑
                }
            }
        };
       return itemWriter;

//        lambda表达式写法
//        return list -> {
//            for (Result access : list) {
//                log.info("write data : " + access); //模拟 假装写数据 ,这里写真正写入数据的逻辑
//            }
//        };
    }


}
