package br.ufs.esii.toh.controller.gestor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Gerrefeicoes {

	@RequestMapping("gestor/gerrefeicoes")
	public String gerrefeicoes() {
		return "gestor/gerrefeicoes";
	}
	
}
