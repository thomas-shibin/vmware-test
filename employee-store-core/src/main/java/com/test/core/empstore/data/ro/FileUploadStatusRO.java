package com.test.core.empstore.data.ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by shibthom on 1/23/2021.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadStatusRO implements Serializable {

    private static final long serialVersionUID = -3460302631824522834L;

    private String fileName;
    private String fileLocation;
    private String fileStatus;
    private String errorMessage;
    private Integer progressPercentage = 0;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
