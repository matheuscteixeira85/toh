package br.ufs.esii.toh.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufs.esii.toh.model.Administrador;
import br.ufs.esii.toh.model.Role;
import br.ufs.esii.toh.services.AdministradorService;
import br.ufs.esii.toh.services.RoleService;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	AdministradorService administradorService;
	
	@Autowired
	RoleService roleService;
		

	@RequestMapping("/")
	public String administrador(Model model) {
		
		model.addAttribute("cpfadmin",SecurityContextHolder.getContext().getAuthentication().getName());
		
		return "administrador";
	}
	
	@RequestMapping("/root/")
	public String root() {
		return "root";
	}
	
	
	
	
	@PostMapping("/root/root/")
	@ResponseBody
	public ResponseEntity<Object> saveAdministrador(@RequestParam MultiValueMap<String, String> paramMap){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
	
		if(roleService.findByNomeRole("ROLE_ADMIN").isEmpty()) {
			Role roleAdmin = new Role();
			roleAdmin.setNomeRole("ROLE_ADMIN");
			roleService.save(roleAdmin);
		}
		if(roleService.findByNomeRole("ROLE_GEST").isEmpty()) {
			Role roleAdmin = new Role();
			roleAdmin.setNomeRole("ROLE_GEST");
			roleService.save(roleAdmin);
		}
		if(roleService.findByNomeRole("ROLE_ATEN").isEmpty()) {
			Role roleAdmin = new Role();
			roleAdmin.setNomeRole("ROLE_ATEN");
			roleService.save(roleAdmin);
		}
		if(roleService.findByNomeRole("ROLE_ATEN").isEmpty()) {
			Role roleAdmin = new Role();
			roleAdmin.setNomeRole("ROLE_ADMIN");
			roleService.save(roleAdmin);
		}
		if(roleService.findByNomeRole("ROLE_TESTE").isEmpty()) {
			Role roleAdmin = new Role();
			roleAdmin.setNomeRole("ROLE_TESTE");
			roleService.save(roleAdmin);
		}
		
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
		administrador.setRoles(roleService.findByNomeRole("ROLE_ADMIN"));
		administradorService.save(administrador);
		return ResponseEntity.status(HttpStatus.CREATED).body("Novo ADMINISTRADOR cadastrado com sucesso");
		
	}
	
}
