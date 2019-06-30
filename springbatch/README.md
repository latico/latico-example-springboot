# 作用
    大数据量分批处理框架，比如分页从库里面读取数据（处理后，按批量大小写入其他地方），按行读取大文件，分批写入到其他地方；
# 程序流程
    1、每次从库里面读取10条数据放进处理队列处理；
    2、处理完成队列满足了100条就执行一次批量写操作；
# 注意
    配置文件中，已经把自动启动关了，需要手工启动，目前在启动监听器那里判断是否启动，如果不是自动启动，那么就会在监听器启动，
    而在实际使用中，一般是结合定时器quartz等进行定时启动，请看springbatch-quartz模块；
# 应用场景
    1、从库读取数据，写进csv文件；
    2、从CSV文件读取数据入到库中；
# 踩坑
    1、因为Access实体类没有放进entity包中，导致org.springframework.batch.core.step.item.SimpleChunkProvider.doRead方法出错，
    catch (Exception e) {
        if (logger.isDebugEnabled()) {
            logger.debug(e.getMessage() + " : " + e.getClass().getName());
        }
        listener.onReadError(e);
        throw e;
    }
    
    报了：exception just for purpose of providing stack trace
     看此错误看不出来什么问题，又因为logback日志配置的spring级别是info导致org.springframework.batch.core.step.item.SimpleChunkProvider.doRead方法的真正错误没有
     打印出来，实际上的错误是UnKnow the entity Access,把spring的级别调成debug后就能成功打印出来了；
     
# 术语
    https://blog.csdn.net/william_jm/article/details/78964538 
    Step：表示作业Job中的一个完整业务逻辑步骤，一个Job可以有一个或者多个Step组成。
    
    StepExecution：表示试运行一个步骤step的句柄。只有步骤step真的得到运行才会被创建。
    
    Job（作业）：作业是封装整个批处理过程的实体。一个简单的作业需要配置作业名、有序的步骤step、及是否重启。
    
    JobInstance（作业实例）：一个作业实例与其要加载的数据无硬性关联，这完全是由数据读入器ItemReader决定。比如：是否使用同一个作业实例，是由ItemReader根据前一次执行的状态位（state）决定。用新的JobInstance意味从开头读取数据，用已有的表示从上次结束的地方开始。
    
    JobParameter（作业参数）：是指一个批量作业开始的参数集。同时，可以用于标识JobInstance的唯一性。所以可以认为JobInstance=Job+JobParameter。
    
    JobExecution：表示试运行一个作业的句柄。
    
    如下图2.2.1所示，Job好比是容器，可以包含多个业务逻辑步骤step与多个JobInstance，来组织作业的执行（亦可以保证作业的重启），而JobExecution则是致力于记录执行状态。每一次执行中JobExecution和step都会进行数据信息传输，比如：commitCount、rollbackCount、startTime、endTime等，这些都会记录进StepExecution。
    JobLauncher（作业调度器）：是Spring Batch框架基础设施层提供运行Job的能力。对于将给定Job名称和作Job Parameters的Job，由Java程序、命令行或者其它调度框架（如Quartz）中调用JobLauncher执行Job。
    
    JobRepository（作业仓库）：来存储Job执行期的元数据（这里的元数据是指Job Instance、Job Execution、Job Parameters、Step Execution、Execution Context等数据）。有两种默认实现——内存或数据库。若将元数据存放在数据库中，可以随时监控批处理Job的执行状态。Job执行结果是成功还是失败，并且使失败Job重新启动Job成为可能。
    
    ItemReader：是对step的输入的抽象，每次只读入一条记录，读取完所有记录后，则返回null。
    
    ItemProcessor：是对每条记录按业务逻辑处理的抽象。
    
    ItemWriter：是对step的输出的抽象，每次只可以提供给一次批作业或记录队（chunk）。
    
    下图2.2.2显示了完整的SpringBatch领域概念模型。JobLancaster启动Job，Job可有多个Step组合，每一个step对应一个ItemReader、ItemProcessor及ItemWriter，而JobRepository记录Job执行信息。