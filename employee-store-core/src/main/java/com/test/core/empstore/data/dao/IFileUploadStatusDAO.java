package com.test.core.empstore.data.dao;

import com.test.core.empstore.data.entity.FileUploadStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shibthom on 1/23/2021.
 */
@Repository
public interface IFileUploadStatusDAO extends CrudRepository<FileUploadStatus, Integer> {

}
