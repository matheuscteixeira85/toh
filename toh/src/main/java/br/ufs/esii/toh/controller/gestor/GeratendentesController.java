package br.ufs.esii.toh.controller.gestor;

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
	
	@RequestMapping("/cadastrar/")
	@GetMapping
	public String geratendentesCadastrar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfgestor", paramMap.getFirst("cpfgestor"));
		
		return "gestor/geratendentes/cadastrar";
	
	}
	
	@RequestMapping("/consultar/")
	@GetMapping
	public String geratendentesConsultar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		Optional<Atendente> atendenteOptional = atendenteService.findByCpf(paramMap.getFirst("cpf"));
		if(!atendenteOptional.isPresent()) {
			model.addAttribute("atendente", atendenteNaoEncontrado());
		}
		else {
			model.addAttribute("atendente", atendenteOptional.get());
		}
		return "gestor/geratendentes/consultar";
	
	}

	@RequestMapping("/atualizar/")
	@GetMapping
	public String geratendenteAtualizar() {
		
		return "gestor/geratendentes/atualizar";
	
	}
	
	@PostMapping("/cadastrar")
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
		optional = gestorService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName());
		
		atendente.setGestor_atendente(optional.get());

		atendenteService.save(atendente);
		
		return ResponseEntity.status(HttpStatus.OK).body("Atendente cadastrado com sucesso!");
	}

	@PostMapping("/atualizar")
	@ResponseBody
	public ResponseEntity<Object> updateAtendente(@RequestParam MultiValueMap<String, String> paramMap){

		Optional<Atendente> atendenteOptional = atendenteService.findByCpf(paramMap.getFirst("cpf"));
		if(!atendenteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado: Atendente com este CPF não encontrado!");
		}
		
		var atendente = new Atendente();
		atendente = atendenteOptional.get();
		atendente.setNome(paramMap.getFirst("nome"));
		atendente.setEndereco(paramMap.getFirst("endereco"));
		atendente.setTelefone(paramMap.getFirst("telefone"));
		atendente.setData_nascimento(paramMap.getFirst("data_nascimento"));
		atendente.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		atendente.setSenha(new BCryptPasswordEncoder().encode(paramMap.getFirst("senha")));
		
		Optional<Gestor> optionalGestor;
		optionalGestor = gestorService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName());
		
		atendente.setGestor_atendente(optionalGestor.get());
		atendenteService.save(atendente);
		return ResponseEntity.status(HttpStatus.OK).body("Dados atualizados com sucesso");
		
	}
	
	public Atendente atendenteNaoEncontrado() {
		
		Atendente atendente = new Atendente();

		atendente.setNome("Não encontrado");
		atendente.setCpf("");
		atendente.setEndereco("");
		atendente.setData_nascimento("");
		atendente.setSenha("");
		atendente.setTelefone("");
		
		return atendente;
		
	}
}
