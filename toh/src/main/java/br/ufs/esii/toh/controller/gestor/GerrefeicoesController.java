package br.ufs.esii.toh.controller.gestor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufs.esii.toh.model.Cardapio;
import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.model.Refeicao;
import br.ufs.esii.toh.model.Usuario;
import br.ufs.esii.toh.services.CardapioService;
import br.ufs.esii.toh.services.GestorService;
import br.ufs.esii.toh.services.RefeicaoService;
import br.ufs.esii.toh.services.UsuarioService;

@Controller
@RequestMapping("/gestor/gerrefeicoes")
public class GerrefeicoesController {

	@Autowired
	CardapioService cardapioService;
	
	@Autowired
	RefeicaoService refeicaoService;
	
	@Autowired
	GestorService gestorService;
	
	@Autowired
	UsuarioService usuarioService;
	 
	@RequestMapping("/")
	@GetMapping
	public String gerrefeicoes(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfgestor", paramMap.getFirst("cpfgestor"));
		
		return "gestor/gerrefeicoes";
	
	}
	
	@RequestMapping("/cadastrar/")
	@GetMapping
	public String gerrefeicoesCadastrar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfgestor", paramMap.getFirst("cpfgestor"));
		
		return "gestor/gerrefeicoes/cadastrar";
	
	}
	
	@RequestMapping("/consultar/")
	@GetMapping
	public String gerrefeicoesConsultar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		Optional<Usuario> usuarioOptional = usuarioService.findByCpf(paramMap.getFirst("cpf"));
		
		if(!usuarioOptional.isEmpty()) {
			List<Refeicao> listaRefeicoes = refeicaoService.findByUsuario(usuarioOptional.get());
	
			Optional<Cardapio> cardapioOptional = cardapioService.findByDataTurno(paramMap.getFirst("data")+"|"+paramMap.getFirst("turno"));
			
			Refeicao refeicao = consumirRefeicao(listaRefeicoes, cardapioOptional.get());
			
			if(refeicao == null) {
				model.addAttribute("saida", "TICKET não encontrado!");
			}
			else {
				model.addAttribute("saida", "TICKET encontrado!");
			}
			return "gestor/gerrefeicoes/consultar";
		}
		else {
			model.addAttribute("saida", "TICKET não encontrado!");
			return "gestor/gerrefeicoes/consultar";
		}
	}

	@PostMapping("/cadastrar")
	@ResponseBody
	public ResponseEntity<Object> saveRefeicao(@RequestParam MultiValueMap<String, String> paramMap){
		if(!usuarioService.existsByCpf(paramMap.getFirst("cpf"))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Encontrado! Não existe usuário cadastrado com este CPF!");
		}
		if(!cardapioService.existsByDataTurno(paramMap.getFirst("data")+"|"+paramMap.getFirst("turno"))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Encontrado! Não existe cardápio nesta data e neste turno!");
		}
		
		Optional<Cardapio> optionalCardapio = cardapioService.findByDataTurno(paramMap.getFirst("data")+"|"+paramMap.getFirst("turno"));
		Optional<Usuario> optionalUsuario = usuarioService.findByCpf(paramMap.getFirst("cpf"));
		Optional<Gestor> optionalGestor = gestorService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName());
		
		var refeicao = new Refeicao();

		refeicao.setConsumida(false);
		refeicao.setCardapio(optionalCardapio.get());
		refeicao.setUsuario(optionalUsuario.get());
		refeicao.setGestor(optionalGestor.get());
		
		refeicao.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		refeicao.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		
		refeicaoService.save(refeicao);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("TICKET cadastrado com sucesso");
	}
	
	public Refeicao consumirRefeicao(List<Refeicao> refeicoes, Cardapio cardapio) {
		if(refeicoes.isEmpty())
			return null;
		List<Refeicao> lista = refeicoes;
		lista.removeIf(refeicao -> !refeicao.getCardapio().equals(cardapio));
		if(lista.isEmpty())
			return null;
		return lista.get(0);
	}
}
