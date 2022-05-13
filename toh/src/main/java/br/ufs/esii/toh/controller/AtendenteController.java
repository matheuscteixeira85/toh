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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import br.ufs.esii.toh.dtos.AtendenteDTO;
import br.ufs.esii.toh.model.Atendente;
import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.model.Usuario;
import br.ufs.esii.toh.services.AtendenteService;
import br.ufs.esii.toh.services.GestorService;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/atendente")
public class AtendenteController {

	@Autowired
	AtendenteService atendenteService;
	
	@Autowired
	GestorService gestorService;
	
	@RequestMapping("/")
	public String atendente() {
		return "atendente";
	}
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public ResponseEntity<Object> saveAtendente(@RequestParam MultiValueMap<String, String> paramMap){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		if(atendenteService.existsByCpf(paramMap.getFirst("cpf"))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Atendente com este CPF já cadastrado!");
		}
		
		var atendente = new Atendente();
		atendente.setCpf(paramMap.getFirst("cpf"));
		atendente.setNome(paramMap.getFirst("nome"));
		atendente.setEndereco(paramMap.getFirst("endereco"));
		atendente.setTelefone(paramMap.getFirst("telefone"));
		atendente.setData_nascimento(paramMap.getFirst("data_nascimento"));
		atendente.setGenero(paramMap.getFirst("genero"));
		atendente.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		atendente.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		atendente.setSenha("123456");
		atendente.setTipo("atend");
		
		Optional<Gestor> optional;
		optional = gestorService.findByCpf("10101010101");
		
		atendente.setGestor_atendente(optional.get());
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
