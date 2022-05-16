package br.ufs.esii.toh.controller.usuario.usucardapio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufs.esii.toh.model.Cardapio;
import br.ufs.esii.toh.services.CardapioService;



@Controller
@RequestMapping("usuario/usucardapio/cardapio")
public class CardapiosController {

	@Autowired
	CardapioService cardapioService;
	
	@RequestMapping("/")
	@GetMapping
	public String cardapio(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		Optional<Cardapio> optional = cardapioService.findById(Integer.parseInt(paramMap.getFirst("id_cardapio")));
		
		model.addAttribute("cardapio", optional.get());
		
		return "usuario/usucardapio/cardapio";
	}
	
}
