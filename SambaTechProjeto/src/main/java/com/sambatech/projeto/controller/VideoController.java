package com.sambatech.projeto.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VideoController {

	@RequestMapping("/videos")
	public String listar() {
		return "ListaVideos";
	}
}
