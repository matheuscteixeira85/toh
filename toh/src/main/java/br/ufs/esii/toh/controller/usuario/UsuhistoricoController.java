package br.ufs.esii.toh.controller.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufs.esii.toh.model.Refeicao;
import br.ufs.esii.toh.model.Usuario;
import br.ufs.esii.toh.services.RefeicaoService;
import br.ufs.esii.toh.services.UsuarioService;

@Controller
@RequestMapping("usuario/usuhistorico")
public class UsuhistoricoController {

	@Autowired
	RefeicaoService refeicaoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping("/")
	@GetMapping
	public String usuhistorico(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		Optional<Usuario> optional = usuarioService.findByCpf(paramMap.getFirst("cpfusuario"));
		List<Refeicao> refeicoes = refeicaoService.findByUsuario(optional.get());
		
		model.addAttribute("tickets", refeicoes);
		
		return "usuario/usuhistorico";
	}
	
}
