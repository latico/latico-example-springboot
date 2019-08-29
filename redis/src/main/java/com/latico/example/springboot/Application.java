package com.latico.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <PRE>
 * 程序启动入口类
 * <p>
 *
 SpringBootApplication 上使用@ServletComponentScan 注解后
 Servlet可以直接通过@WebServlet注解自动注册
 Filter可以直接通过@WebFilter注解自动注册
 Listener可以直接通过@WebListener 注解自动注册

 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-13 22:06:01
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackageClasses = {Application.class})
@ServletComponentScan
public class Application {
    /**
     * 日志类
     */
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    /** APP总运行状态,默认true,控制总状态，在钩子函数关闭设置为false后，其它线程应该要感知此状态以此关闭自身线程 */
    public static final AtomicBoolean APP_RUN_STATUS = new AtomicBoolean(true);

    /**
     * springboot启动
     *
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        //        打开程序状态开关
        APP_RUN_STATUS.set(true);

        long startTime = System.currentTimeMillis();
        System.out.println("开始启动程序,时间点:" + new Timestamp(startTime));
        LOG.info("开始启动程序,时间点:" + new Timestamp(startTime));

        SpringApplication application = new SpringApplication(Application.class);

//启动程序
        startShutDownHook();
        application.run(args);

        long endTime = System.currentTimeMillis();
        long useTime = endTime - startTime;
        System.out.println("启动程序完成,耗时(毫秒):" + useTime);
        LOG.info("启动程序完成,耗时(毫秒):" + useTime);
    }

    /**
     * 该方法不需要改动，添加钩子和打印日志用
     * 防止程序意外退出没有关闭资源，通过钩子函数来清空资源，主要关闭连接，释放列表等。
     * 注意：kill -9 pid是不会执行钩子函数，所以应该用kill pid命令杀进程，如果杀不死再用kill -9 pid
     */
    private static void startShutDownHook() {

        LOG.info("程序启动钩子函数监听功能...");
        //注册一个新的虚拟机关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("开始执行钩子函数的任务...");
                    LOG.info("开始执行钩子函数的任务...");
                    executeShutDownHook();
                } catch (Throwable e) {
                    e.printStackTrace(System.err);
                    LOG.error("钩子函数执行处理任务时操作异常...");
                }
                System.out.println("钩子函数执行完毕，系统已完全关闭！");
                LOG.info("钩子函数执行完毕，系统已完全关闭！");
            }
        });
        LOG.info("程序启动钩子函数监听功能完毕");
        System.out.println("程序启动钩子函数监听功能完毕");
    }

    /**
     * 钩子函数具体的执行业务处理操作
     * TODO 在此处执行钩子函数要具体处理的释放资源等操作。
     * @throws Exception
     */
    private static void executeShutDownHook() throws Exception{
        System.out.println("执行ShutDownHook业务操作");
        LOG.warn("执行ShutDownHook业务操作");
        APP_RUN_STATUS.set(false);
    }
}
