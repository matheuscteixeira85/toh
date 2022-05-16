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

import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.model.Role;
import br.ufs.esii.toh.model.Usuario;
import br.ufs.esii.toh.services.GestorService;
import br.ufs.esii.toh.services.RoleService;
import br.ufs.esii.toh.services.UsuarioService;

@Controller
@RequestMapping("/gestor/gerusuarios")
public class GerusuariosController {
	
	@Autowired
	GestorService gestorService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/")
	@GetMapping
	public String gerusuarios(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfgestor", paramMap.getFirst("cpfgestor"));
		
		return "gestor/gerusuarios";
	
	}
	
	@RequestMapping("/cadastrar/")
	@GetMapping
	public String gerusuariosCadastrar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfgestor", paramMap.getFirst("cpfgestor"));
		
		return "gestor/gerusuarios/cadastrar";
	
	}
	
	@RequestMapping("/atualizar/")
	@GetMapping
	public String gerusuariosAtualizar() {
		
		return "gestor/gerusuarios/atualizar";
	
	}
	
	@RequestMapping("/consultar/")
	@GetMapping
	public String gerusuariosConsultar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		Optional<Usuario> usuarioOptional = usuarioService.findByCpf(paramMap.getFirst("cpf"));
		if(!usuarioOptional.isPresent()) {
			model.addAttribute("usuario", usuarioNaoEncontrado());
		}
		else {
			model.addAttribute("usuario", usuarioOptional.get());
		}
		return "gestor/gerusuarios/consultar";
	
	}
	
	@PostMapping("/cadastrar")
	@ResponseBody
	public ResponseEntity<Object> saveUsuario(@RequestParam MultiValueMap<String, String> paramMap){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		if(usuarioService.existsByCpf(paramMap.getFirst("cpf"))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Usuario com este CPF já cadastrado!");
		}
		
		List<Role> listRoles = roleService.findByNomeRole("ROLE_USER");
		
		var usuario = new Usuario();
		usuario.setCpf(paramMap.getFirst("cpf"));
		usuario.setLogin(paramMap.getFirst("cpf"));
		usuario.setNome(paramMap.getFirst("nome"));
		usuario.setEndereco(paramMap.getFirst("endereco"));
		usuario.setTelefone(paramMap.getFirst("telefone"));
		usuario.setData_nascimento(paramMap.getFirst("data_nascimento"));
		usuario.setSenha(new BCryptPasswordEncoder().encode(paramMap.getFirst("senha")));
		usuario.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		usuario.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		usuario.setCpf(paramMap.getFirst("cpf"));
		usuario.setSenha(new BCryptPasswordEncoder().encode(paramMap.getFirst("senha")));
		usuario.setTipo_usuario(paramMap.getFirst("tipo_usuario"));
		usuario.setRoles(listRoles);
		
		Optional<Gestor> optional;
		optional = gestorService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName());
		
		usuario.setGestor_usuario(optional.get());
		System.out.println(usuario.getGestor_usuario().getNome());
		
		usuarioService.save(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
	}

	@PostMapping("/atualizar")
	@ResponseBody
	public ResponseEntity<Object> updateUsuario(@RequestParam MultiValueMap<String, String> paramMap){

		Optional<Usuario> usuarioOptional = usuarioService.findByCpf(paramMap.getFirst("cpf"));
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado: Usuario com este CPF não encontrado!");
		}
		
		var usuario = new Usuario();
		usuario = usuarioOptional.get();
		usuario.setNome(paramMap.getFirst("nome"));
		usuario.setEndereco(paramMap.getFirst("endereco"));
		usuario.setTelefone(paramMap.getFirst("telefone"));
		usuario.setData_nascimento(paramMap.getFirst("data_nascimento"));
		usuario.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		usuario.setSenha(new BCryptPasswordEncoder().encode(paramMap.getFirst("senha")));
		
		Optional<Gestor> optionalGestor;
		optionalGestor = gestorService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName());
		
		usuario.setGestor_usuario(optionalGestor.get());
		usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.OK).body("Dados atualizados com sucesso");
		
	}


	public Usuario usuarioNaoEncontrado() {
		
		Usuario usuario = new Usuario();

		usuario.setNome("Não encontrado");
		usuario.setCpf("");
		usuario.setEndereco("");
		usuario.setData_nascimento("");
		usuario.setSenha("");
		usuario.setTelefone("");
		
		return usuario;
		
	}
}
