package br.ufs.esii.toh.controller.administrador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GergestorController {

	@RequestMapping("administrador/gergestor")
	public String gergestor() {
		return "administrador/gergestor";
	}
}
