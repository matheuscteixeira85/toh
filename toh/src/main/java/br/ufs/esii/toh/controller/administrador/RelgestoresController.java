package br.ufs.esii.toh.controller.administrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.services.GestorService;

@Controller
@RequestMapping("administrador/relgestores")
public class RelgestoresController {

	@Autowired
	GestorService gestorService;
	
	@RequestMapping("/")
	public String relgestores(Model model) {
		
		List<Gestor> gestores = gestorService.findAll();
		model.addAttribute("gestores", gestores);
		
		return "administrador/relgestores";
	}
	
}
