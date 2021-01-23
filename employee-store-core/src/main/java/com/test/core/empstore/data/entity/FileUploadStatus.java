package com.test.core.empstore.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by shibthom on 1/23/2021.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_UPLOAD_STATUS")
public class FileUploadStatus implements Serializable{

    private static final long serialVersionUID = -1013855332009811389L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name="FILE_NAME", nullable = false)
    private String fileName;

    @Column(name="FILE_LOCATION")
    private String fileLocation;

    @Column(name="FILE_STATUS")
    private String fileStatus;

    @Column(name="ERROR_MESSAGE")
    private String errorMessage;

    @Column(name = "PROGRESS_PERCENTAGE")
    private Integer progressPercentage = 0;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "END_TIME")
    private LocalDateTime endTime;

    @CreationTimestamp
    @Column(name = "CREATED_TIME", nullable = false, updatable=false)
    private LocalDateTime createdTime;
}
