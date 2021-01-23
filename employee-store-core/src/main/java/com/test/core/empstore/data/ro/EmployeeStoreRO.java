package com.test.core.empstore.data.ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by shibthom on 1/23/2021.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeStoreRO implements Serializable{

    private static final long serialVersionUID = 8097156630966387461L;

    private String name;
    private Integer age;
}
