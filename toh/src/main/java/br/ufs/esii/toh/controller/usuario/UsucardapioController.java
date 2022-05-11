package br.ufs.esii.toh.controller.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario/usucardapio")
public class UsucardapioController {

	@RequestMapping("/")
	public String usucardapio() {
		return "usuario/usucardapio";
	}
	
}
