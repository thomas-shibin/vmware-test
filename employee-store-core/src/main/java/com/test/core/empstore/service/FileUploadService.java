package com.test.core.empstore.service;

import com.test.core.empstore.constant.FileUploadStatusEnm;
import com.test.core.empstore.data.entity.EmployeeStore;
import com.test.core.empstore.data.entity.FileUploadStatus;
import com.test.core.empstore.data.finders.DBFuncs;
import com.test.core.empstore.data.ro.FileUploadStatusRO;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by shibthom on 1/23/2021.
 */
@Service
public class FileUploadService {

    @Autowired
    private DBFuncs dbFuncs;
    @Autowired
    private EmployeeDataService employeeDataService;

    @Value("${upload.location}")
    private String uploadLoc;
    @Value("${upload.done.location}")
    private String uploadCompletedLoc;
    @Value("${upload.error.location}")
    private String uploadFailLoc;
    @Value("${batch.size}")
    private Integer batchSize;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);

    /**
     *
     * @param file
     * @return
     */
    public FileUploadStatus uploadFile(MultipartFile file){

        LOGGER.debug("START - Uploading file "+file.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        FileUploadStatus uploadStatus = new FileUploadStatus();
        if (!file.isEmpty()) {
            try {
                String fileName = FilenameUtils.removeExtension(file.getOriginalFilename());
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                File dir = new File(uploadLoc);

                if (!dir.exists()) dir.mkdirs();

                fileName = uploadLoc + fileName + "_" + sdf.format(Calendar.getInstance().getTime()) + "." + extension;
                byte[] bytes = file.getBytes();
                File newFile = new File(fileName);
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(bytes);
                stream.close();

                uploadStatus.setFileName(newFile.getName());
                uploadStatus.setFileLocation(fileName);
                uploadStatus.setFileStatus(FileUploadStatusEnm.IN_PROGRESS.name());
                uploadStatus.setStartTime(LocalDateTime.now());
            } catch (Exception e) {
                uploadStatus.setFileStatus(FileUploadStatusEnm.FAILED.name());
                uploadStatus.setStartTime(LocalDateTime.now());
                uploadStatus.setEndTime(LocalDateTime.now());
                uploadStatus.setErrorMessage(e.getMessage());
                uploadStatus.setFileLocation(file.getName());
            }
        }

        LOGGER.debug("END - Uploading file "+file.getName());
        return saveUploadStatus(uploadStatus);
    }

    /**
     *
     * @param uploadStatus
     */
    @Async
    public void processFile(FileUploadStatus uploadStatus){

        String status = null;
        String fileLocation = null;
        try(Stream<String> lines = Files.lines(Paths.get(uploadStatus.getFileLocation()))) {

            List<EmployeeStore> employeeStoreList = new ArrayList<>();

            String[] array = lines.toArray(String[]::new);

            Supplier<Stream<String>> supplier = () -> Stream.of(array);

            long count = supplier.get().count();

            Integer progressPercentage = (int)count / 100;

            List<Integer> counter = new ArrayList<>();

            supplier.get().forEach(s -> {

                String[] str = s.split(" ");

                if(str.length > 0) {
                    EmployeeStore employeeStore = new EmployeeStore();
                    int age = Integer.valueOf(str[str.length-1]);
                    StringBuilder name = new StringBuilder();
                    for(int i = 0; i < str.length-1; i++){
                        name.append(str[i]);
                        name.append(" ");
                    }
                    employeeStore.setName(name.toString().trim());
                    employeeStore.setAge(age);
                    employeeStore.setFileTimestamp(uploadStatus.getStartTime());
                    employeeStoreList.add(employeeStore);
                }

                counter.add(0);
                if(counter.size() % (progressPercentage < 1? 1: progressPercentage) == 0){
                    int curProgress = uploadStatus.getProgressPercentage() +1;
                    uploadStatus.setProgressPercentage(curProgress);
                    saveUploadStatus(uploadStatus);
                }
                if(employeeStoreList.size() % batchSize == 0){
                    employeeDataService.saveEmployeesData(employeeStoreList);
                    employeeStoreList.clear();
                }
            });

            if(employeeStoreList.size() > 0){
                employeeDataService.saveEmployeesData(employeeStoreList);
                employeeStoreList.clear();
            }

            fileLocation = uploadCompletedLoc;
        } catch (IOException ex) {
            LOGGER.error("Exception occured during file processing "+ex.getMessage());
            status = FileUploadStatusEnm.FAILED.name();
            fileLocation = uploadFailLoc;
            uploadStatus.setErrorMessage(ex.getMessage());
        }

        File dir = new File(fileLocation);
        if(!dir.exists()) dir.mkdirs();
        fileLocation = fileLocation + uploadStatus.getFileName();
        File oldFile = new File(uploadStatus.getFileLocation());
        oldFile.renameTo(new File(fileLocation));

        uploadStatus.setFileStatus(status);
        uploadStatus.setFileLocation(fileLocation);
        uploadStatus.setEndTime(LocalDateTime.now());
        uploadStatus.setProgressPercentage(100);
        saveUploadStatus(uploadStatus);
    }

    /**
     *
     * @return
     */
    public List<FileUploadStatusRO> findAllUploadStatus(){

        List<FileUploadStatusRO> response = null;
        LOGGER.debug("START - findAllUploadStatus");
        response = dbFuncs.getDaoFinders().getFileUploadStatusDAO().getAllFileUploadStatuses();
        LOGGER.debug("END - findAllUploadStatus");
        return response;
    }

    /**
     *
     * @param id
     * @return
     */
    public FileUploadStatusRO findUploadStatusById(int id){

        FileUploadStatusRO response = null;
        LOGGER.debug("START - findUploadStatusById");
        response = dbFuncs.getDaoFinders().getFileUploadStatusDAO().getFileUploadStatusById(id);
        LOGGER.debug("END - findUploadStatusById");
        return response;
    }

    private FileUploadStatus saveUploadStatus(FileUploadStatus uploadStatus){

        LOGGER.debug("START - Saving upload status");
        uploadStatus = dbFuncs.getDaoFinders().getFileUploadStatusDAO().save(uploadStatus);
        LOGGER.debug("END - Saving upload status");
        return uploadStatus;
    }
}
