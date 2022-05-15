package br.ufs.esii.toh.controller.administrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufs.esii.toh.model.Cardapio;
import br.ufs.esii.toh.services.CardapioService;

@Controller
@RequestMapping("administrador/relcardapios")
public class RelcardapiosController {

	@Autowired
	CardapioService cardapioService;
	
	@RequestMapping("/")
	public String relcardapios(Model model) {
		
		List<Cardapio> cardapios = cardapioService.findAll();
		
		model.addAttribute("cardapios", cardapios);
		
		return "administrador/relcardapios";
	}
	
}
