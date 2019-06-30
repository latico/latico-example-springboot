# 注意
    当配置文件里定义spring.batch.job.enabled为true，或者没定义（默认为true）的时候，会初始化一个JobLauncherCommandLineRunner的bean，自动执行batch配置好的作业Job。鉴于我们将batch的作业Job调度任务交由Quartz调度，所以设置为false，这样工程启动后只会初始化batch作业配置，但不执行。