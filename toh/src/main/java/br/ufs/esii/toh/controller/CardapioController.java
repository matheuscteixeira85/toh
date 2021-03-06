package br.ufs.esii.toh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufs.esii.toh.model.Cardapio;
import br.ufs.esii.toh.services.CardapioService;
import br.ufs.esii.toh.services.GestorService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cardapio")
public class CardapioController {

	
	@Autowired
	CardapioService cardapioService;
	
	
	@Autowired
	GestorService gestorService;
	
	
	
	
	
	@GetMapping
	public ResponseEntity<List<Cardapio>> getAllCardapios(){
		return ResponseEntity.status(HttpStatus.OK).body(cardapioService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneCardapio(@PathVariable(value = "id") Integer id){
		Optional<Cardapio> cardapioOptional = cardapioService.findById(id);
		if(!cardapioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cardapio não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cardapioOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCardapio(@PathVariable(value = "id") Integer id){
		Optional<Cardapio> cardapioOptional = cardapioService.findById(id);
		if(!cardapioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cardapio não encontrado!");
		}
		cardapioService.delete(cardapioOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cardapio deletado com sucesso!");
	}
/*
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCardapio(@PathVariable(value = "id") Long id,
											   @RequestBody @Valid CardapioDTO cardapioDTO){
		Optional<Cardapio> cardapioOptional = cardapioService.findById(id);
		if(!cardapioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cardapio não encontrado!");
		}
		var  cardapio = new Cardapio();
		BeanUtils.copyProperties(cardapioDTO, cardapio);
		cardapio.setId_cardapio(cardapioOptional.get().getId_cardapio());
		cardapio.setData_cadastro(cardapioOptional.get().getData_cadastro());
		cardapio.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(cardapioService.save(cardapio));
	}
	*/
}
