package br.ufs.esii.toh.controller.gestor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("gestor/gerrefeicoes")
public class GerrefeicoesController {

	@RequestMapping("/")
	public String gerrefeicoes() {
		return "gestor/gerrefeicoes";
	}
	
}
