package br.ufs.esii.toh.controller.atendente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Validarcpf {

	
	@RequestMapping("atendente/validarcpf")
	public String validarcof() {
		return "atendente/validarcpf";
	}
	
}
