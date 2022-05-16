package br.ufs.esii.toh.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufs.esii.toh.model.Cardapio;
import br.ufs.esii.toh.services.CardapioService;

@Controller
@RequestMapping
public class IndexController {

	@Autowired
	CardapioService cardapioService;
	
	@RequestMapping("/")
	public String index() {

		return "index";
	}
	
	@GetMapping("/cardapiosdodia")
	public String cardapiosdodia(Model model) {
		
		List<Cardapio> cardapios = cardapioService.findByData(LocalDate.now());
		model.addAttribute("cardapios", cardapios);
		
		return "cardapiosdodia";
	}
	
}
