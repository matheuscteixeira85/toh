package br.ufs.esii.toh.controller.administrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufs.esii.toh.model.Refeicao;
import br.ufs.esii.toh.services.RefeicaoService;

@Controller
@RequestMapping("administrador/relrefeicoes")
public class RelrefeicoesController {

	@Autowired
	RefeicaoService refeicaoService;
	
	@RequestMapping("/")
	public String relrefeicoes(Model model) {
		
		List<Refeicao> refeicoes = refeicaoService.findAll();
		model.addAttribute("refeicoes", refeicoes);
		
		return "administrador/relrefeicoes";
	}
	
}
