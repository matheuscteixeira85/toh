package br.ufs.esii.toh.controller.administrador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administrador/relatendentes")
public class RelatendentesController {

	@RequestMapping("/")
	public String relatendentes() {
		return "administrador/relatendentes";
	}
	
}
