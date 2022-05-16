package br.ufs.esii.toh.controller.usuario;

import java.time.LocalDate;
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
@RequestMapping("usuario/usutickets")
public class UsuticketsController {

	@Autowired
	RefeicaoService refeicaoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping("/")
	@GetMapping
	public String usutickets(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		Optional<Usuario> optional= usuarioService.findByCpf(paramMap.getFirst("cpfusuario"));
		List<Refeicao> tickets = refeicaoService.findByUsuario(optional.get());
		model.addAttribute("tickets", filtrarHoje(tickets));
		
		return "usuario/usutickets";
	}
	
	public List<Refeicao> filtrarHoje(List<Refeicao> list){
		if(list == null) {
            return  list;
        }
        
        list.removeIf(refeicao -> !refeicao.getCardapio().getData().equals(LocalDate.now()));
        
        return list;


        
	}
	
	
}
