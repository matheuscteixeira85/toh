package br.ufs.esii.toh.controller.gestor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Gerusuarios {
	
	@RequestMapping("gestor/gerusuarios")
	public String gerusuarios() {
		return "gestor/gerusuarios";
	}

}
