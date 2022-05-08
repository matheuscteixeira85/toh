package br.ufs.esii.toh.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufs.esii.toh.dtos.RefeicaoDTO;
import br.ufs.esii.toh.model.Refeicao;
import br.ufs.esii.toh.services.RefeicaoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/refeicao")
public class RefeicaoController {

	@Autowired
	RefeicaoService refeicaoService;
	
	@PostMapping
	public ResponseEntity<Object> saveRefeicao(@RequestBody @Valid RefeicaoDTO refeicaoDTO){
		var refeicao = new Refeicao();
		BeanUtils.copyProperties(refeicaoDTO, refeicao);
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
