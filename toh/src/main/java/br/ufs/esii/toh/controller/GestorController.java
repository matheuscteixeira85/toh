package br.ufs.esii.toh.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}