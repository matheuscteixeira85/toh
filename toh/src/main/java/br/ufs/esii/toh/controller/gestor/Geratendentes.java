package br.ufs.esii.toh.controller.gestor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Geratendentes {

	@RequestMapping("gestor/geratendentes")
	public String geratendentes() {
		return "gestor/geratendentes";
	}
	
}
