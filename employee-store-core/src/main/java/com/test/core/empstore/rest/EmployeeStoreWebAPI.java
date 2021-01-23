package com.test.core.empstore.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.test.core.empstore.constant.FileUploadStatusEnm;
import com.test.core.empstore.data.entity.FileUploadStatus;
import com.test.core.empstore.data.ro.EmployeeStoreRO;
import com.test.core.empstore.data.ro.FileUploadStatusRO;
import com.test.core.empstore.exception.EmployeeStoreCustomException;
import com.test.core.empstore.service.EmployeeDataService;
import com.test.core.empstore.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by shibthom on 1/23/2021.
 */
@RestController
@RequestMapping("/api/")
public class EmployeeStoreWebAPI {

    @Autowired
    private EmployeeDataService dataService;
    @Autowired
    private FileUploadService uploadService;

    @GetMapping(value = "upload/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileUploadStatusRO> getUploadStatusById(@RequestParam("id") @NotNull(message = "Id cannot be null")  int id){

        FileUploadStatusRO response = null;
        try{
            response = uploadService.findUploadStatusById(id);
        }catch (Exception ex){
            throw new EmployeeStoreCustomException(ex.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "upload/status/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FileUploadStatusRO>> getAllUploadStatus(){

        List<FileUploadStatusRO> response = null;
        try{
            response = uploadService.findAllUploadStatus();
        }catch (Exception ex){
            throw new EmployeeStoreCustomException(ex.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "employee")
    public ResponseEntity<EntityModel<FileUploadStatus>> uploadFile(@RequestParam("action") @NotNull(message = "Action cannot be null")  String action, @RequestParam("file")MultipartFile file){

        if("upload".equalsIgnoreCase(action)){
            FileUploadStatus response =uploadService.uploadFile(file);

            if(response.getFileStatus().equalsIgnoreCase(FileUploadStatusEnm.IN_PROGRESS.name())){
                uploadService.processFile(response);
            }
            EntityModel<FileUploadStatus> entityModel = EntityModel.of(response);

            WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUploadStatusById(response.getId()));

            entityModel.add(linkTo.withRel("upload-status"));

            return ResponseEntity.ok(entityModel);
        } else {
            throw new EmployeeStoreCustomException("No action items found");
        }
    }

    @GetMapping(value = "employee/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeStoreRO>> getAllEmployees(){

        List<EmployeeStoreRO> response = null;
        try{
            response = dataService.getAllEmployeeData();
        }catch (Exception ex){
            throw new EmployeeStoreCustomException(ex.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "employee/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeStoreRO> getEmployeeByName(@PathVariable("name") String name){

        EmployeeStoreRO response = null;
        try{
            response = dataService.getEmployeeDataByName(name);
        }catch (Exception ex){
            throw new EmployeeStoreCustomException(ex.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "employee/all/delete")
    public ResponseEntity<String> deleteAllEmployees(){

        try{
            dataService.deleteAllEmployeeData();
        }catch (Exception ex){
            throw new EmployeeStoreCustomException(ex.getMessage());
        }
        return ResponseEntity.ok("success");
    }

    @DeleteMapping(value = "employee/delete/{name}")
    public ResponseEntity<String> deleteEmployeeByName(@PathVariable("name") String name){

        try{
            dataService.deleteEmployeeDataByName(name);
        }catch (Exception ex){
            throw new EmployeeStoreCustomException(ex.getMessage());
        }
        return ResponseEntity.ok("success");
    }
}
