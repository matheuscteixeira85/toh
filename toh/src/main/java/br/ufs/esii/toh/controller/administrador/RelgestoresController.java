package br.ufs.esii.toh.controller.administrador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administrador/relgestores")
public class RelgestoresController {

	@RequestMapping("/")
	public String relgestores() {
		return "administrador/relgestores";
	}
	
}
