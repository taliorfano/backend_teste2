package com.sambatech.projeto.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sambatech.projeto.model.AmazonClient;
import com.sambatech.projeto.model.Util;
import com.sambatech.projeto.model.Video;

/***
 * Controller do upload de video na Amazon s3
 * @author Talita
 *
 */
@Controller
public class UploadController {
	private AmazonClient amazonClient;
	
	// Caminho do arquivo original na Amazon
	private String pathInput="";
	
	// Caminho do arquivo convertido na Amazon
	private String pathOutput="";
	
	// Barra de progresso
	private boolean progress = true;
	
	@Autowired
	UploadController(AmazonClient amazonClient) {
	    this.amazonClient = amazonClient;
	}
	
	@RequestMapping(value = { "/upload"}, method=RequestMethod.GET)
	public String form() {
		this.pathInput="";
		this.pathOutput="";
		this.progress=false;
		return "UploadVideo";
	}
	
	@RequestMapping(value= {"/upload"}, method=RequestMethod.POST)
	public String form(
			@RequestParam("nome") String name,
			@RequestParam("fileVideo") MultipartFile file, 
			Map<String, Object> model) throws InterruptedException {

		// Ativar um carregando
		this.progress=true;
		model.put("progress", progress);
				
	    Video video = new Video(name, file);
	    String fileName = Util.GenerateFileName(video.getName());
	    	
	    this.amazonClient.uploadFile(video, fileName);
		
		this.pathInput="https://s3.amazonaws.com/inputs-videos/inputs/"+fileName;
		//this.pathOutput="https://s3.amazonaws.com/inputs-videos/outputs/"+fileName+"-hls-low_001.mp4";
		
		this.pathOutput="https://s3.amazonaws.com/inputs-videos/outputs/"+fileName+"-hls-low_001.mp4";
	    
	    model.put("input", this.pathInput);
	    model.put("output", this.pathOutput);
	    
		return "UploadVideo";
	}
}
