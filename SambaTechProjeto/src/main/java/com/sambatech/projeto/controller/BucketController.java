package com.sambatech.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sambatech.projeto.model.AmazonClient;

/***
 * 
 * @author Talita
 * Controller para armazenamento de arquivos em bucket da Amazon S3
 * Ref: https://medium.com/oril/uploading-files-to-aws-s3-bucket-using-spring-boot-483fcb6f8646
 *
 */

// @RestController
// @RequestMapping("/storage/")
public class BucketController {

    private AmazonClient amazonClient;

   /* @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        //this.amazonClient.uploadFile(file);
        
        
    	return "";
    }*/
}