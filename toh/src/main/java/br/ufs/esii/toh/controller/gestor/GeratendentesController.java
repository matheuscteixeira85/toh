package br.ufs.esii.toh.controller.gestor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("gestor/geratendentes")
public class GeratendentesController {

	@RequestMapping("/")
	public String geratendentes() {
		return "gestor/geratendentes";
	}
	
}
