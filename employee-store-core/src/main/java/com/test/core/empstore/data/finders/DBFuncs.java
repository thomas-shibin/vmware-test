package com.test.core.empstore.data.finders;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by shibthom on 1/25/2019.
 */
@Data
public class DBFuncs {

    @Autowired
    public DAOFinders daoFinders;
}
