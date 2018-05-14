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
 * Index da aplicacao
 * @author Talita
 *
 */
@Controller
public class IndexController {
	private AmazonClient amazonClient;
	
	@Autowired
	IndexController(AmazonClient amazonClient) {
	    this.amazonClient = amazonClient;
	}
		
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String form() {
		return "index";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String form(@RequestParam("nome") String name,
			@RequestParam("fileVideo") MultipartFile file) {
		
	    Video video = new Video(name, file);

	    // Ativar um carregando	    
		String url = this.amazonClient.uploadFile(video);
	    // Desativar um carregando
		
		// Retornar com uma mensagem de sucesso!
		
		return "index";
	}
}
