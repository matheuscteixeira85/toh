package br.ufs.esii.toh.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufs.esii.toh.dtos.RefeicaoDTO;
import br.ufs.esii.toh.model.Cardapio;
import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.model.Refeicao;
import br.ufs.esii.toh.model.Usuario;
import br.ufs.esii.toh.services.AtendenteService;
import br.ufs.esii.toh.services.CardapioService;
import br.ufs.esii.toh.services.GestorService;
import br.ufs.esii.toh.services.RefeicaoService;
import br.ufs.esii.toh.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/refeicao")
public class RefeicaoController {

	@Autowired
	RefeicaoService refeicaoService;
	
	@Autowired
	CardapioService cardapioService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	GestorService gestorService;
	
	@Autowired
	AtendenteService atendenteService;
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public ResponseEntity<Object> saveRefeicao(@RequestParam MultiValueMap<String, String> paramMap){
		if(!cardapioService.existsByDataTurno(paramMap.getFirst("data")+"T00:00:00L"+paramMap.getFirst("turno"))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Encontrado! Não existe cardápio nesta data e neste turno!");
		}
		
		Optional<Cardapio> optionalCardapio = cardapioService.findByDataTurno(paramMap.getFirst("data")+"T00:00:00L"+paramMap.getFirst("turno"));
		Optional<Usuario> optionalUsuario = usuarioService.findByCpf(paramMap.getFirst("cpf"));
		Optional<Gestor> optionalGestor = gestorService.findByCpf("01010101010");
		
		var refeicao = new Refeicao();

		refeicao.setConsumida(false);
		refeicao.setCardapio_refeicao(optionalCardapio.get());
		refeicao.setUsuario_refeicao(optionalUsuario.get());
		refeicao.setGestor_refeicao(optionalGestor.get());
		
		refeicao.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		refeicao.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(refeicaoService.save(refeicao));
	}
	
	@GetMapping
	public ResponseEntity<List<Refeicao>> getAllrefeicaos(){
		return ResponseEntity.status(HttpStatus.OK).body(refeicaoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneRefeicao(@PathVariable(value = "id") Long id){
		Optional<Refeicao> refeicaoOptional = refeicaoService.findById(id);
		if(!refeicaoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Refeicao não encontrada!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(refeicaoOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteRefeicao(@PathVariable(value = "id") Long id){
		Optional<Refeicao> refeicaoOptional = refeicaoService.findById(id);
		if(!refeicaoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Refeicao não encontrada!");
		}
		refeicaoService.delete(refeicaoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Refeicao deletada com sucesso!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateRefeicao(@PathVariable(value = "id") Long id,
											   @RequestBody @Valid RefeicaoDTO refeicaoDTO){
		Optional<Refeicao> refeicaoOptional = refeicaoService.findById(id);
		if(!refeicaoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Refeicao não encontrada!");
		}
		var  refeicao = new Refeicao();
		BeanUtils.copyProperties(refeicaoDTO, refeicao);
		refeicao.setId_refeicao(refeicaoOptional.get().getId_refeicao());
		refeicao.setData_cadastro(refeicaoOptional.get().getData_cadastro());
		refeicao.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(refeicaoService.save(refeicao));
	}
	
}
