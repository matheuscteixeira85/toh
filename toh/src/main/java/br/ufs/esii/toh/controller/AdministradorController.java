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
import br.ufs.esii.toh.services.GestorService;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	AdministradorService administradorService;
	
	@Autowired
	GestorService gestorService;
	

	@RequestMapping("/")
	public String administrador(Model model) {
		String cpfadmin = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(cpfadmin.length());
		model.addAttribute("cpfadmin",cpfadmin);
		
		return "administrador";
	}
	
	@RequestMapping("/root/")
	public String root() {
		return "root";
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
	
	
	
	@PostMapping("/root/root/")
	@ResponseBody
	public ResponseEntity<Object> saveAdministrador(@RequestParam MultiValueMap<String, String> paramMap){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		
	
		if(administradorService.existsByCpf(paramMap.getFirst("cpf"))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Administrador com este CPF já cadastrado!");
		}
		List<Administrador> lista = administradorService.findAll();
		
		var administrador = new Administrador();
		administrador.setCpf(paramMap.getFirst("cpf"));
		administrador.setNome(paramMap.getFirst("nome"));
		administrador.setEndereco(paramMap.getFirst("endereco"));
		administrador.setTelefone(paramMap.getFirst("telefone"));
		administrador.setData_nascimento(paramMap.getFirst("data_nascimento"));
		administrador.setMatricula(lista.size());
		administrador.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		administrador.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		administrador.setLogin(paramMap.getFirst("cpf"));
		administrador.setSenha(new BCryptPasswordEncoder().encode(paramMap.getFirst("senha")));
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
/*	
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
*/	
}
