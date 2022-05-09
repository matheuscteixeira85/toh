package br.ufs.esii.toh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GestController {
	
	@RequestMapping("/gest")
	public String index() {
		return "gestor";
	}
}
