package academiaapi.ipd.gob.pe.academiaapi.config;

import org.apache.tools.ant.taskdefs.Exec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class asyncConfig {

    @Bean(name = "appTaskExecutor")
    public Executor appTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);      // hilos concurrentes
        executor.setMaxPoolSize(10);      // máximo de hilos
        executor.setQueueCapacity(100);   // tamaño de la cola
        executor.setThreadNamePrefix("AppTask-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "emailTaskExecutor")
    public Executor emailTskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("email-");
        executor.initialize();
        return executor;
    }
}
