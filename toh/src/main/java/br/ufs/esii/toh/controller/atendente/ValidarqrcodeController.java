package br.ufs.esii.toh.controller.atendente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("atendente/validarqrcode")
public class ValidarqrcodeController {

	@RequestMapping("/")
	public String validarqrcode() {
		return "atendente/validarqrcode";
	}
}
