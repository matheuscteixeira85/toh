package br.ufs.esii.toh.controller.usuario.usucardapio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario/usucardapio/cardapio")
public class CardapiosController {

	@RequestMapping("/")
	public String cardapio() {
		return "usuario/usucardapio/cardapio";
	}
	
}
