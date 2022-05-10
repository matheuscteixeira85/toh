package br.ufs.esii.toh.controller.gestor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Gercardapios {

	@RequestMapping("gestor/gercardapios")
	public String gercardapios() {
		return "gestor/gercardapios";
	}
	
}
