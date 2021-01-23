package com.test.core.empstore.rest;

import com.test.core.empstore.data.finders.DBFuncs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shibthom on 1/23/2021.
 */
@Service
public class EmployeeDataService {

    @Autowired
    private DBFuncs dbFuncs;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDataService.class);
}
