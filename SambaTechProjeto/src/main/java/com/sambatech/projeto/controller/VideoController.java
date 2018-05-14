package com.sambatech.projeto.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.sambatech.projeto.model.AmazonClient;

import org.springframework.web.bind.annotation.RequestMapping;

/****
 * Controller de visualizacao de videos
 * @author Talita
 *
 */
@Controller
public class VideoController {

    private AmazonClient amazonClient;
    @Autowired
    VideoController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
    
	@RequestMapping("/videos")
	public String listar() {
		amazonClient.readFile();
		return "ListaVideos";
	}
}
