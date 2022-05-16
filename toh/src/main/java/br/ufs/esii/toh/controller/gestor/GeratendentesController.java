package br.ufs.esii.toh.controller.gestor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufs.esii.toh.model.Atendente;
import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.model.Role;
import br.ufs.esii.toh.services.AtendenteService;
import br.ufs.esii.toh.services.GestorService;
import br.ufs.esii.toh.services.RoleService;

@Controller
@RequestMapping("/gestor/geratendentes")
public class GeratendentesController {

	@Autowired
	AtendenteService atendenteService;
	
	@Autowired
	GestorService gestorService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/")
	@GetMapping
	public String geratendentes(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfgestor", paramMap.getFirst("cpfgestor"));
		
		return "gestor/geratendentes";
	
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Object> saveAtendente(@RequestParam MultiValueMap<String, String> paramMap){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		if(atendenteService.existsByCpf(paramMap.getFirst("cpf"))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Atendente com este CPF já cadastrado!");
		}
		
		List<Atendente> lista = atendenteService.findAll(); 
		
		List<Role> listaRoles = roleService.findByNomeRole("ROLE_ATEN");
		
		var atendente = new Atendente();
		atendente.setCpf(paramMap.getFirst("cpf"));
		atendente.setNome(paramMap.getFirst("nome"));
		atendente.setEndereco(paramMap.getFirst("endereco"));
		atendente.setTelefone(paramMap.getFirst("telefone"));
		atendente.setData_nascimento(paramMap.getFirst("data_nascimento"));
		atendente.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		atendente.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		atendente.setLogin(paramMap.getFirst("cpf"));
		atendente.setSenha(new BCryptPasswordEncoder().encode(paramMap.getFirst("senha")));
		atendente.setTipo("atend");
		atendente.setMatricula(lista.size());
		atendente.setRoles(listaRoles);
		
		Optional<Gestor> optional;
		optional = gestorService.findByCpf(paramMap.getFirst("cpfgestor"));
		
		atendente.setGestor_atendente(optional.get());

		return ResponseEntity.status(HttpStatus.CREATED).body(atendenteService.save(atendente));
	}
}
