package com.sambatech.projeto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * Index da aplicacao
 * @author Talita
 *
 */
@Controller
public class IndexController {

	@RequestMapping(value = {"/"})
	public String index() {
		return "index";
	}
	
}
