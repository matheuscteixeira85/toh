package br.ufs.esii.toh.controller.administrador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administrador/relcardapios")
public class RelcrefeicoesController {

	@RequestMapping("/")
	public String relcardapios() {
		return "administrador/relcardapios";
	}
	
}
