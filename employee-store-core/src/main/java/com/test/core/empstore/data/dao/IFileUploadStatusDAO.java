package com.test.core.empstore.data.dao;

import com.test.core.empstore.data.entity.FileUploadStatus;
import com.test.core.empstore.data.ro.FileUploadStatusRO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shibthom on 1/23/2021.
 */
@Repository
public interface IFileUploadStatusDAO extends CrudRepository<FileUploadStatus, Integer> {

    @Query("select new com.test.core.empstore.data.ro.FileUploadStatusRO(up.fileName, up.fileLocation, up.fileStatus, up.errorMessage, up.progressPercentage, up.startTime, up.endTime) from FileUploadStatus up")
    List<FileUploadStatusRO> getAllFileUploadStatuses();

    @Query("select new com.test.core.empstore.data.ro.FileUploadStatusRO(up.fileName, up.fileLocation, up.fileStatus, up.errorMessage, up.progressPercentage, up.startTime, up.endTime) from FileUploadStatus up where up.id = ?1")
    FileUploadStatusRO getFileUploadStatusById(int id);
}
