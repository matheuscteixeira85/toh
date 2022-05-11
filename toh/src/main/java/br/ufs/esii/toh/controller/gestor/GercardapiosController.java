package br.ufs.esii.toh.controller.gestor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("gestor/gercardapios")
public class GercardapiosController {

	@RequestMapping("/")
	public String gercardapios() {
		return "gestor/gercardapios";
	}
	
}
