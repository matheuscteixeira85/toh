package br.ufs.esii.toh.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.model.Usuario;
import br.ufs.esii.toh.services.GestorService;
import br.ufs.esii.toh.services.UsuarioService;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	GestorService gestorService;

	@RequestMapping("/")
	public String usuario(Model model) {

		model.addAttribute("cpfusuario",SecurityContextHolder.getContext().getAuthentication().getName());
		
		return "usuario";
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	@ResponseBody
	public ResponseEntity<Object> saveUsuario(@RequestParam MultiValueMap<String, String> paramMap){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		if(usuarioService.existsByCpf(paramMap.getFirst("cpf"))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Usuario com este CPF já cadastrado!");
		}
		
		var usuario = new Usuario();
		usuario.setCpf(paramMap.getFirst("cpf"));
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
		
		Optional<Gestor> optional;
		optional = gestorService.findByCpf(paramMap.getFirst("cpfgestor"));
		
		usuario.setGestor_usuario(optional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Usuario>> getAllUsuarios(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") Long id){
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") Long id){
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
		}
		usuarioService.delete(usuarioOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
	}
}
