package br.ufs.esii.toh.controller.atendente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("atendente/validarcpf")
public class ValidarcpfController {

	
	@RequestMapping("/")
	public String validarcof() {
		return "atendente/validarcpf";
	}
	
}
