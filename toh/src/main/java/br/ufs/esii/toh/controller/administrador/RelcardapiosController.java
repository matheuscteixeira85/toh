package br.ufs.esii.toh.controller.administrador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administrador/relrefeicoes")
public class RelcardapiosController {

	@RequestMapping("/")
	public String relrefeicoes() {
		return "administrador/relrefeicoes";
	}
	
}
