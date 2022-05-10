package br.ufs.esii.toh.controller.atendente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Validarqrcode {

	@RequestMapping("atendente/validarqrcode")
	public String validarqrcode() {
		return "atendente/validarqrcode";
	}
}
