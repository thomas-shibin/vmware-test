package com.test.core.empstore.data.finders;

import com.test.core.empstore.data.dao.IEmployeeStoreDAO;
import com.test.core.empstore.data.dao.IFileUploadStatusDAO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shibthom on 1/25/2019.
 */
@Data
public class DAOFinders {

    @Autowired
    private IEmployeeStoreDAO employeeStoreDAO;
    @Autowired
    private IFileUploadStatusDAO fileUploadStatusDAO;
}
