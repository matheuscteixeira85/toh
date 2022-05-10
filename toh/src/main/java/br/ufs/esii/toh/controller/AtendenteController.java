package br.ufs.esii.toh.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufs.esii.toh.dtos.AtendenteDTO;
import br.ufs.esii.toh.model.Atendente;
import br.ufs.esii.toh.services.AtendenteService;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/atendente")
public class AtendenteController {

	@Autowired
	AtendenteService atendenteService;
	
	@RequestMapping("/")
	public String atendente() {
		return "atendente";
	}
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Object> saveAtendente(@RequestBody @Valid AtendenteDTO atendenteDTO){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		if(atendenteService.existsByCpf(atendenteDTO.getCpf())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Atendente com este CPF já cadastrado!");
		}
		
		var atendente = new Atendente();
		BeanUtils.copyProperties(atendenteDTO, atendente);
		atendente.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		atendente.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(atendenteService.save(atendente));
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Atendente>> getAllAtendentes(){
		return ResponseEntity.status(HttpStatus.OK).body(atendenteService.findAll());
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> getOneAtendente(@PathVariable(value = "id") UUID id){
		Optional<Atendente> atendenteOptional = atendenteService.findById(id);
		if(!atendenteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atendente não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(atendenteOptional.get());
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> deleteAtendente(@PathVariable(value = "id") UUID id){
		Optional<Atendente> atendenteOptional = atendenteService.findById(id);
		if(!atendenteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atendente não encontrado!");
		}
		atendenteService.delete(atendenteOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Atendente deletado com sucesso!");
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> updateatendente(@PathVariable(value = "id") UUID id,
											   @RequestBody @Valid AtendenteDTO atendenteDTO){
		Optional<Atendente> atendenteOptional = atendenteService.findById(id);
		if(!atendenteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atendente não encontrado!");
		}
		var  atendente = new Atendente();
		BeanUtils.copyProperties(atendenteDTO, atendente);
		atendente.setAtendente_id(atendenteOptional.get().getAtendente_id());
		atendente.setData_cadastro(atendenteOptional.get().getData_cadastro());
		atendente.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(atendenteService.save(atendente));
	}
}
