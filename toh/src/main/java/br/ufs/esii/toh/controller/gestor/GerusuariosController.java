package br.ufs.esii.toh.controller.gestor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("gestor/gerusuarios")
public class GerusuariosController {
	
	@RequestMapping("/")
	public String gerusuarios() {
		return "gestor/gerusuarios";
	}

}
