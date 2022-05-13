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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufs.esii.toh.dtos.AdministradorDTO;
import br.ufs.esii.toh.dtos.PessoaDTO;
import br.ufs.esii.toh.model.Administrador;
import br.ufs.esii.toh.services.AdministradorService;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	AdministradorService administradorService;
	

	@RequestMapping("/")
	public String administrador() {
		return "administrador";
	}
	
	@RequestMapping("/root/")
	public String root() {
		return "root";
	}
	
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public ResponseEntity<Object> saveAdministrador(@RequestParam MultiValueMap<String, String> paramMap){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		
		if(administradorService.existsByCpf(paramMap.getFirst("cpf"))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Administrador com este CPF já cadastrado!");
		}
		List<Administrador> lista = administradorService.findAll();
//		if(administradorService.existsByMatricula(administradorDTO.getMatricula())) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Administrador com esta matricula já cadastrado!");
//		}
		var administrador = new Administrador();
		administrador.setCpf(paramMap.getFirst("cpf"));
		administrador.setNome(paramMap.getFirst("nome"));
		administrador.setEndereco(paramMap.getFirst("endereco"));
		administrador.setTelefone(paramMap.getFirst("telefone"));
		administrador.setData_nascimento(paramMap.getFirst("data_nascimento"));
		administrador.setGenero(paramMap.getFirst("genero"));
		administrador.setMatricula(lista.size());
		administrador.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		administrador.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		administrador.setSenha("123456");
		administrador.setTipo("admin");
		return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.save(administrador));
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Administrador>> getAllAdministradores(){
		return ResponseEntity.status(HttpStatus.OK).body(administradorService.findAll());
	}
	
	@GetMapping("/{id_administrador}")
	@ResponseBody
	public ResponseEntity<Object> getOneAdministrador(@PathVariable(value = "id_administrador") UUID id){
		Optional<Administrador> administradorOptional = administradorService.findById(id);
		if(!administradorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(administradorOptional.get());
	}
	
	@DeleteMapping("/{id_administrador}")
	@ResponseBody
	public ResponseEntity<Object> deleteAdministrador(@PathVariable(value = "id_administrador") UUID id){
		Optional<Administrador> administradorOptional = administradorService.findById(id);
		if(!administradorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado!");
		}
		administradorService.delete(administradorOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Administrador deletado com sucesso!");
	}
	
	@PutMapping("/{id_administrador}")
	@ResponseBody
	public ResponseEntity<Object> updateAdministrador(@PathVariable(value = "id_administrador") UUID id,
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
