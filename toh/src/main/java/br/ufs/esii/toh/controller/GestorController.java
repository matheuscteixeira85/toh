package br.ufs.esii.toh.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufs.esii.toh.model.Administrador;
import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.services.AdministradorService;
import br.ufs.esii.toh.services.AtendenteService;
import br.ufs.esii.toh.services.GestorService;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/gestor")
public class GestorController {

	@Autowired
	GestorService gestorService;
	
	@Autowired
	AtendenteService atendenteService;
	
	@Autowired
	AdministradorService administradorService;
	
	@RequestMapping("/")
	public String gestor(Model model) {

		model.addAttribute("cpfgestor",SecurityContextHolder.getContext().getAuthentication().getName());
		
		return "gestor";
	}
	
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Object> saveGestor(@RequestParam MultiValueMap<String, String> paramMap){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		if(gestorService.existsByCpf(paramMap.getFirst("cpf"))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Gestor com este CPF já cadastrado!");
		}
		List<Gestor> lista = gestorService.findAll();
		
		var gestor = new Gestor();
		gestor.setCpf(paramMap.getFirst("cpf"));
		gestor.setNome(paramMap.getFirst("nome"));
		gestor.setEndereco(paramMap.getFirst("endereco"));
		gestor.setTelefone(paramMap.getFirst("telefone"));
		gestor.setData_nascimento(paramMap.getFirst("data_nascimento"));
		gestor.setSenha(new BCryptPasswordEncoder().encode(paramMap.getFirst("senha")));
		gestor.setMatricula(lista.size());
		gestor.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		gestor.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		gestor.setLogin(paramMap.getFirst("cpf"));
		gestor.setSenha(new BCryptPasswordEncoder().encode(paramMap.getFirst("senha")));
		gestor.setTipo("gest");
		
		Optional<Administrador> optional;
		optional = administradorService.findByCpf(paramMap.getFirst("cpfadmin"));
		
		gestor.setAdministrador_gestor(optional.get());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(gestorService.save(gestor));
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Gestor>> getAllGestores(){
		return ResponseEntity.status(HttpStatus.OK).body(gestorService.findAll());
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> getOneGestor(@PathVariable(value = "id") UUID id){
		Optional<Gestor> gestorOptional = gestorService.findById(id);
		if(!gestorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gestor não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(gestorOptional.get());
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> deleteGestor(@PathVariable(value = "id") UUID id){
		Optional<Gestor> gestorOptional = gestorService.findById(id);
		if(!gestorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gestor não encontrado!");
		}
		gestorService.delete(gestorOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Gestor deletado com sucesso!");
	}
	/*
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> updateGestor(@PathVariable(value = "id") UUID id,
											   @RequestBody @Valid GestorDTO gestorDTO){
		Optional<Gestor> gestorOptional = gestorService.findById(id);
		if(!gestorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gestor não encontrado!");
		}
		var  gestor = new Gestor();
		BeanUtils.copyProperties(gestorDTO, gestor);
		gestor.setGestor_id(gestorOptional.get().getGestor_id());
		gestor.setData_cadastro(gestorOptional.get().getData_cadastro());
		gestor.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(gestorService.save(gestor));
	}
	*/
}