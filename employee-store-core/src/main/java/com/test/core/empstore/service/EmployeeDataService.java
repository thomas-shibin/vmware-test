package com.test.core.empstore.service;

import com.test.core.empstore.data.entity.EmployeeStore;
import com.test.core.empstore.data.finders.DBFuncs;
import com.test.core.empstore.data.qo.IEmployeeQO;
import com.test.core.empstore.data.ro.EmployeeStoreRO;
import com.test.core.empstore.exception.EmployeeStoreCustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shibthom on 1/23/2021.
 */
@Service
public class EmployeeDataService {

    @Autowired
    private DBFuncs dbFuncs;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDataService.class);

    /**
     *
     * @param employeeStoreList
     */
    public void saveEmployeesData(List<EmployeeStore> employeeStoreList){

        LOGGER.debug("START - Saving employee data");
        dbFuncs.getDaoFinders().getEmployeeStoreDAO().saveAll(employeeStoreList);
        LOGGER.debug("END - Saving employee data");
    }

    /**
     *
     * @return
     */
    public List<EmployeeStoreRO> getAllEmployeeData(){

        LOGGER.debug("START - getAllEmployeeData");
        List<IEmployeeQO> employeeQOList =  dbFuncs.getDaoFinders().getEmployeeStoreDAO().getAllEmployees();

        List<EmployeeStoreRO> response = new ArrayList<>();

        for(IEmployeeQO qo : employeeQOList) response.add(new EmployeeStoreRO(qo.getName(), qo.getAge()));
        LOGGER.debug("END - getAllEmployeeData");

        return response;
    }

    /**
     *
     * @param name
     * @return
     */
    public EmployeeStoreRO getEmployeeDataByName(String name){

        LOGGER.debug("START - getEmployeeDataByName name "+name);
        IEmployeeQO qo =  dbFuncs.getDaoFinders().getEmployeeStoreDAO().getEmployeeByName(name);

        EmployeeStoreRO response = new EmployeeStoreRO(qo.getName(), qo.getAge());
        LOGGER.debug("END - getEmployeeDataByName name "+name);

        return response;
    }

    /**
     *
     * @param name
     * @return
     */
    public void deleteEmployeeDataByName(String name){

        LOGGER.debug("START - deleteEmployeeDataByName name "+name);

        List<EmployeeStore> employeeStoreList = dbFuncs.getDaoFinders().getEmployeeStoreDAO().findByName(name);

        if(employeeStoreList == null || employeeStoreList.size() == 0) throw new EmployeeStoreCustomException("No employee data found");

        dbFuncs.getDaoFinders().getEmployeeStoreDAO().deleteAll(employeeStoreList);

        LOGGER.debug("END - deleteEmployeeDataByName name "+name);
    }

    /**
     *
     * @return
     */
    public void deleteAllEmployeeData(){

        LOGGER.debug("START - deleteAllEmployeeDataByName");

        dbFuncs.getDaoFinders().getEmployeeStoreDAO().deleteAll();

        LOGGER.debug("END - deleteAllEmployeeDataByName");
    }
}
