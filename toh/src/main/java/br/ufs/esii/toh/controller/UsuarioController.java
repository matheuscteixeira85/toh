package br.ufs.esii.toh.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

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

import br.ufs.esii.toh.dtos.UsuarioDTO;
import br.ufs.esii.toh.model.Usuario;
import br.ufs.esii.toh.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Object> saveusUario(@RequestBody @Valid UsuarioDTO usuarioDTO){
		//VERIFICAR REGISTROS UNICOS REPETIDOS
		if(usuarioService.existsByCpf(usuarioDTO.getCpf())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Usuario com este CPF já cadastrado!");
		}
		
		var usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDTO, usuario);
		usuario.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		usuario.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuarios(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") Long id){
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") Long id){
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
		}
		usuarioService.delete(usuarioOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateusuario(@PathVariable(value = "id") Long id,
											   @RequestBody @Valid UsuarioDTO usuarioDTO){
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
		}
		var  usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDTO, usuario);
		usuario.setUsuario_id(usuarioOptional.get().getUsuario_id());
		usuario.setData_cadastro(usuarioOptional.get().getData_cadastro());
		usuario.setData_alteracao(usuarioOptional.get().getData_alteracao());
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
	}
	
}
