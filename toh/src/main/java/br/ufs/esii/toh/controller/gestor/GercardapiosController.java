package br.ufs.esii.toh.controller.gestor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufs.esii.toh.model.Cardapio;
import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.services.CardapioService;
import br.ufs.esii.toh.services.GestorService;

@Controller
@RequestMapping("gestor/gercardapios")
public class GercardapiosController {

	@Autowired
	GestorService gestorService;
	
	@Autowired
	CardapioService cardapioService;
	
	@RequestMapping("/")
	@GetMapping
	public String gercardapios(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfgestor", paramMap.getFirst("cpfgestor"));
		
		return "gestor/gercardapios";
	
	}
	
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Object> saveCardapio(@RequestParam MultiValueMap<String, String> paramMap){
		if(cardapioService.existsByDataTurno(paramMap.getFirst("data")+"|"+paramMap.getFirst("turno"))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito! Já exite um cardápio cadastrado nesta data e neste turno!");
		}
		
		var cardapio = new Cardapio();
		cardapio.setNome(paramMap.getFirst("nome"));
		cardapio.setDescricao(paramMap.getFirst("descricao"));
		cardapio.setData(LocalDate.parse(paramMap.getFirst("data")));
		cardapio.setTurno(paramMap.getFirst("turno"));
		cardapio.setDataTurno(paramMap.getFirst("data")+"|"+paramMap.getFirst("turno"));
		cardapio.setData_cadastro(LocalDateTime.now(ZoneId.of("UTC")));
		cardapio.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));

		System.out.println(paramMap.getFirst("cpfgestor"));
		Optional<Gestor> optional;
		optional = gestorService.findByCpf(paramMap.getFirst("cpfgestor"));

		cardapio.setGestor(optional.get());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cardapioService.save(cardapio));
	
	}
}
