package br.ufs.esii.toh.controller.administrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufs.esii.toh.model.Atendente;
import br.ufs.esii.toh.services.AtendenteService;

@Controller
@RequestMapping("administrador/relatendentes")
public class RelatendentesController {

	@Autowired
	AtendenteService atendenteService;
	
	@RequestMapping("/")
	public String relatendentes(Model model) {
		
		List<Atendente> atendentes = atendenteService.findAll();
		model.addAttribute("atendentes", atendentes);
		
		return "administrador/relatendentes";
	}
	
}
