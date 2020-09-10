package com.qf.config;

import com.qf.job.HotDataReadJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleScheduler {

    @Bean
    public JobDetail daysJobDetail() {
        // 链式编程,可以携带多个参数,在Job类中声明属性 + setter方法
        return JobBuilder.newJob(HotDataReadJob.class).withIdentity("daysJob")
                .usingJobData("name","World").storeDurably().build();
    }

    @Bean
    public Trigger daysTrigger(){
        // 每秒执行一次
        SimpleScheduleBuilder scheduleBuilder =
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60 * 60 * 24).repeatForever();
        return TriggerBuilder.newTrigger().forJob(daysJobDetail()).withIdentity("daysTrigger")
                .withSchedule(scheduleBuilder).build();
    }
}
