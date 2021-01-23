package com.test.core.empstore.config;

import com.test.core.empstore.data.finders.DAOFinders;
import com.test.core.empstore.data.finders.DBFuncs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shibthom on 1/25/2019.
 */
@Configuration
public class AppConfig {


    @Bean
    public DAOFinders getDAOFinders(){
        return new DAOFinders();
    }

    @Bean
    public DBFuncs getDBFuncs(){
        return new DBFuncs();
    }


}
