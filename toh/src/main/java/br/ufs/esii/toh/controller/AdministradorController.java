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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufs.esii.toh.dtos.AdministradorDTO;
import br.ufs.esii.toh.model.Administrador;
import br.ufs.esii.toh.services.AdministradorService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	AdministradorService administradorService;
	
	@PostMapping
	public ResponseEntity<Object> saveAdministrador(@RequestBody @Valid AdministradorDTO administradorDTO){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		if(administradorService.existsByCpf(administradorDTO.getCpf())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Administrador com este CPF já cadastrado!");
		}

		if(administradorService.existsByMatricula(administradorDTO.getMatricula())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Administrador com esta matricula já cadastrado!");
		}
		
		var administrador = new Administrador();
		BeanUtils.copyProperties(administradorDTO, administrador);
		administrador.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		administrador.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.save(administrador));
	}
	
	@GetMapping
	public ResponseEntity<List<Administrador>> getAllAdministradores(){
		return ResponseEntity.status(HttpStatus.OK).body(administradorService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneAdministrador(@PathVariable(value = "id") UUID id){
		Optional<Administrador> administradorOptional = administradorService.findById(id);
		if(!administradorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(administradorOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAdministrador(@PathVariable(value = "id") UUID id){
		Optional<Administrador> administradorOptional = administradorService.findById(id);
		if(!administradorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado!");
		}
		administradorService.delete(administradorOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Administrador deletado com sucesso!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAdministrador(@PathVariable(value = "id") UUID id,
											   @RequestBody @Valid AdministradorDTO administradorDTO){
		Optional<Administrador> administradorOptional = administradorService.findById(id);
		if(!administradorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado!");
		}
		var  administrador = new Administrador();
		BeanUtils.copyProperties(administradorDTO, administrador);
		administrador.setAdministrador_id(administradorOptional.get().getAdministrador_id());
		administrador.setData_cadastro(administradorOptional.get().getData_cadastro());
		administrador.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(administradorService.save(administrador));
	}
}
