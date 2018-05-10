package com.sambatech.projeto.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/****
 * Controller de videos armazenados
 * @author Talita
 *
 */
@Controller
public class VideoController {

	@RequestMapping("/videos")
	public String listar() {
		return "ListaVideos";
	}
	
	@RequestMapping("/upload")
	public String upload() {
		return "UploadVideo";
	}
}
