package br.ufs.esii.toh.controller.atendente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufs.esii.toh.model.Cardapio;
import br.ufs.esii.toh.model.Refeicao;
import br.ufs.esii.toh.model.Usuario;
import br.ufs.esii.toh.services.CardapioService;
import br.ufs.esii.toh.services.RefeicaoService;
import br.ufs.esii.toh.services.UsuarioService;

@Controller
@RequestMapping("atendente/validarcpf")
public class ValidarcpfController {

	@Autowired
	RefeicaoService refeicaoService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	CardapioService cardapioService;
	
	@RequestMapping("/")
	public String validarcof() {
		return "atendente/validarcpf";
	}
	
	@PostMapping
	public ResponseEntity<Object> updateRefeicao(@RequestParam MultiValueMap<String, String> paramMap){
		
		Optional<Usuario> usuarioOptional = usuarioService.findByCpf(paramMap.getFirst("cpf"));
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
		}
		List<Refeicao> listaRefeicoes = refeicaoService.findByUsuario(usuarioOptional.get());
		
		for(Refeicao refeicao:listaRefeicoes)
			System.out.println(refeicao.getCardapio().getNome());
		
		if(listaRefeicoes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Refeicao não encontrada!");
		}
		Optional<Cardapio> cardapioOptional = cardapioService.findByDataTurno(LocalDate.now()+"|"+paramMap.getFirst("turno"));
		
		var  refeicao = new Refeicao();
		refeicao = consumirRefeicao(listaRefeicoes, cardapioOptional.get());
		refeicao.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		refeicao.setConsumida(true);
		return ResponseEntity.status(HttpStatus.OK).body(refeicaoService.save(refeicao));
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
