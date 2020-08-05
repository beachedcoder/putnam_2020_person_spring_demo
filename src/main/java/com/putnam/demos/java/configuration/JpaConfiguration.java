package com.putnam.demos.java.configuration;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFAULT,basePackages = {"com.putnam.demos.java.domain","com.putnam.demos.java.repositories"},
enableDefaultTransactions = true)
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class JpaConfiguration {
    
}