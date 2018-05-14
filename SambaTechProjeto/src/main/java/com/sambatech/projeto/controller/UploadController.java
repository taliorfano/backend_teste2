package com.sambatech.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sambatech.projeto.model.AmazonClient;
import com.sambatech.projeto.model.Video;

/***
 * Conroller do upload de video
 * @author Talita
 *
 */
@Controller
public class UploadController {
	private AmazonClient amazonClient;
	
	@Autowired
	UploadController(AmazonClient amazonClient) {
	    this.amazonClient = amazonClient;
	}
	
	@RequestMapping(value = { "/upload"}, method=RequestMethod.GET)
	public String form() {
		return "UploadVideo";
	}
	
	@RequestMapping(value= {"/upload"}, method=RequestMethod.POST)
	public String form(@RequestParam("nome") String name,
			@RequestParam("fileVideo") MultipartFile file) {
		
	    Video video = new Video(name, file);

	    //this.amazonClient.readFile();
	    
	    // Ativar um carregando	    
		String url = this.amazonClient.uploadFile(video);
	    // Desativar um carregando
		
		// Retornar com uma mensagem de sucesso!
		
		return "UploadVideo";
	}
}
