package br.ufs.esii.toh.controller.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Usuhistorico {

	@RequestMapping("usuario/usuhistorico")
	public String usuhistorico() {
		return "usuario/usuhistorico";
	}
	
}
