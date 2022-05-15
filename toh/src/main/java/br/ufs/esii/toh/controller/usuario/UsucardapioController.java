package br.ufs.esii.toh.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufs.esii.toh.services.CardapioService;

@Controller
@RequestMapping("usuario/usucardapio")
public class UsucardapioController {

	@Autowired
	CardapioService cardapioService;
	
	@RequestMapping("/")
	public String usucardapio(Model model) {
		
		//List<Cardapio> cardapios = cardapioService.findByData(LocalDateTime.now().g);
		
		return "usuario/usucardapio";
	}
	
}
