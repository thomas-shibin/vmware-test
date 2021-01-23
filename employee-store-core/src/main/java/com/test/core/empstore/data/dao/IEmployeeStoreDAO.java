package com.test.core.empstore.data.dao;

import com.test.core.empstore.data.entity.EmployeeStore;
import com.test.core.empstore.data.qo.IEmployeeQO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shibthom on 1/23/2021.
 */
@Repository
public interface IEmployeeStoreDAO extends CrudRepository<EmployeeStore, Integer> {

    @Query(value = "select emp.name, age from S_WP_EMP_DATA_DEL emp  JOIN " +
            "  (select name, max(FILE_TIMESTAMP) FILE_GENERATED_TIME " +
            " from S_WP_EMP_DATA_DEL GROUP by name) emp1 " +
            "on (emp.NAME = emp1.NAME and emp.FILE_TIMESTAMP = emp1.FILE_GENERATED_TIME)", nativeQuery = true)
    List<IEmployeeQO> getAllEmployees();

    @Query(value = "select emp.name, age from S_WP_EMP_DATA_DEL emp  JOIN " +
            "  (select name, max(FILE_TIMESTAMP) FILE_GENERATED_TIME " +
            " from S_WP_EMP_DATA_DEL GROUP by name) emp1 " +
            "on (emp.NAME = emp1.NAME and emp.FILE_TIMESTAMP = emp1.FILE_GENERATED_TIME) " +
            " where wp.name = ?1", nativeQuery = true)
    IEmployeeQO getEmployeeByName(String name);
}
