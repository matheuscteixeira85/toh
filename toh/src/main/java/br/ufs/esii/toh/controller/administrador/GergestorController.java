package br.ufs.esii.toh.controller.administrador;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("administrador/gergestor")
public class GergestorController {


	@RequestMapping("/")
	@GetMapping
	public String gergestor(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfadmin", paramMap.getFirst("cpfadmin"));
		
		return "administrador/gergestor";
	
	}
	
}
