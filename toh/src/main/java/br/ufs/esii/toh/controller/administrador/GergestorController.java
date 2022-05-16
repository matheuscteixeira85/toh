package br.ufs.esii.toh.controller.administrador;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufs.esii.toh.model.Administrador;
import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.model.Role;
import br.ufs.esii.toh.services.AdministradorService;
import br.ufs.esii.toh.services.GestorService;
import br.ufs.esii.toh.services.RoleService;

@Controller
@RequestMapping("administrador/gergestor")
public class GergestorController {

	@Autowired
	AdministradorService administradorService;
	
	@Autowired
	GestorService gestorService;
	
	@Autowired
	RoleService roleService;

	@RequestMapping("/")
	@GetMapping
	public String gergestor(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfadmin", paramMap.getFirst("cpfadmin"));
		
		return "administrador/gergestor";
	
	}
	
	@RequestMapping("/cadastrar/")
	@GetMapping
	public String gergestorCadastrar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfadmin", paramMap.getFirst("cpfadmin"));
		
		return "administrador/gergestor/cadastrar";
	
	}
	
	@RequestMapping("/consultar/")
	@GetMapping
	public String gergestorConsultar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		Optional<Gestor> gestorOptional = gestorService.findByCpf(paramMap.getFirst("cpf"));
		if(!gestorOptional.isPresent()) {
			model.addAttribute("gestor", gestorNaoEncontrado());
		}
		else {
			model.addAttribute("gestor", gestorOptional.get());
		}
		return "administrador/gergestor/consultar";
	
	}
	
	@RequestMapping("/atualizar/")
	@GetMapping
	public String gergestorAtualizar() {
		
		return "administrador/gergestor/atualizar";
	
	}
	
	@PostMapping("/cadastrar")
	@ResponseBody
	public ResponseEntity<Object> saveGestor(@RequestParam MultiValueMap<String, String> paramMap){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		if(gestorService.existsByCpf(paramMap.getFirst("cpf"))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Gestor com este CPF já cadastrado!");
		}
		List<Gestor> lista = gestorService.findAll();
		
		List<Role> listaRoles = roleService.findByNomeRole("ROLE_GEST");
		
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
		gestor.setRoles(listaRoles);
		
		Optional<Administrador> optional;
		optional = administradorService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName());
		
		gestor.setAdministrador_gestor(optional.get());
		gestorService.save(gestor);
		return ResponseEntity.status(HttpStatus.OK).body("Gestor salvo com sucesso!");
		
	}
	
	@GetMapping("/consultar")
	@ResponseBody
	public ResponseEntity<Object> getOneGestor(@RequestParam MultiValueMap<String, String> paramMap, Model model){
		Optional<Gestor> gestorOptional = gestorService.findByCpf(paramMap.getFirst("cpf"));
		if(!gestorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gestor não encontrado!");
		}
		model.addAttribute("gestor", gestorOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Encontrado!");
	}
	
	@PostMapping("/atualizar")
	@ResponseBody
	public ResponseEntity<Object> updateGestor(@RequestParam MultiValueMap<String, String> paramMap){

		Optional<Gestor> gestorOptional = gestorService.findByCpf(paramMap.getFirst("cpf"));
		if(!gestorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado: Gestor com este CPF não encontrado!");
		}
		
		var gestor = new Gestor();
		gestor = gestorOptional.get();
		gestor.setNome(paramMap.getFirst("nome"));
		gestor.setEndereco(paramMap.getFirst("endereco"));
		gestor.setTelefone(paramMap.getFirst("telefone"));
		gestor.setData_nascimento(paramMap.getFirst("data_nascimento"));
		gestor.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		gestor.setSenha(new BCryptPasswordEncoder().encode(paramMap.getFirst("senha")));
		
		Optional<Administrador> optionalAdministrador;
		optionalAdministrador = administradorService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName());
		
		gestor.setAdministrador_gestor(optionalAdministrador.get());
		gestorService.save(gestor);
		return ResponseEntity.status(HttpStatus.OK).body("Dados atualizados com sucesso");
		
	}
	
	public Gestor gestorNaoEncontrado() {
		
		Gestor gestor = new Gestor();

		gestor.setNome("Não encontrado");
		gestor.setCpf("");
		gestor.setEndereco("");
		gestor.setData_nascimento("");
		gestor.setSenha("");
		gestor.setTelefone("");
		
		return gestor;
		
	}
}
