package br.ufs.esii.toh.controller.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Usucardapio {

	@RequestMapping("usuario/usucardapio")
	public String usuusucardapio() {
		return "usuario/usucardapio";
	}
	
}
