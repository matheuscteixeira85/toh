package br.ufs.esii.toh.controller.administrador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administrador/gergestor")
public class GergestorController {

	@RequestMapping("/")
	public String gergestor() {
		return "administrador/gergestor";
	}
}
