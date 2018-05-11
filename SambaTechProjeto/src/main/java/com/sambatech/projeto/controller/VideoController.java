package com.sambatech.projeto.controller;
import org.springframework.stereotype.Controller;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.web.bind.annotation.RequestMapping;

/****
 * Controller de videos: armazena e le video
 * @author Talita
 *
 */
@Controller
public class VideoController {

	@RequestMapping("/videos")
	public String listar() {
		return "Video/ListaVideos";
	}
	
	@RequestMapping("/upload")
	public String upload() {
		
		return "Video/UploadVideo";
	}
}
