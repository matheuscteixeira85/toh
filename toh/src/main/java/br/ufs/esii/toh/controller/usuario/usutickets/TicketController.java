package br.ufs.esii.toh.controller.usuario.usutickets;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufs.esii.toh.model.Refeicao;
import br.ufs.esii.toh.services.RefeicaoService;



@Controller
@RequestMapping("usuario/usutickets/ticket")
public class TicketController {

	@Autowired
	RefeicaoService refeicaoService;
	
	@RequestMapping("/")
	@GetMapping
	public String ticket(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		Optional<Refeicao> optional = refeicaoService.findById(Integer.parseInt(paramMap.getFirst("id_refeicao")));
		
		model.addAttribute("cpfusuario", paramMap.getFirst("cpfusuario"));
		model.addAttribute("refeicao", optional.get());
		
		return "usuario/usutickets/ticket";
	}
	
}
