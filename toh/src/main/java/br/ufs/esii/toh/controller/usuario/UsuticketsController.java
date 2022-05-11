package br.ufs.esii.toh.controller.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario/usutickets")
public class UsuticketsController {

	@RequestMapping("/")
	public String usutickets() {
		return "usuario/usutickets";
	}
	
}
