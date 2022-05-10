package br.ufs.esii.toh.controller.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Usutickets {

	@RequestMapping("usuario/usutickets")
	public String usutickets() {
		return "usuario/usutickets";
	}
	
}
