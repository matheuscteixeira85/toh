package br.ufs.esii.toh.controller.gestor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@RequestMapping("/cadastrar/")
	@GetMapping
	public String gercardapiosCadastrar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfgestor", paramMap.getFirst("cpfgestor"));
		
		return "gestor/gercardapios/cadastrar";
	
	}
	
	@RequestMapping("/atualizar/")
	@GetMapping
	public String gercardapiosAtualizar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		model.addAttribute("cpfgestor", paramMap.getFirst("cpfgestor"));
		
		return "gestor/gercardapios/atualizar";
	
	}
	
	@RequestMapping("/consultar/")
	@GetMapping
	public String gercardapiosConsultar(@RequestParam MultiValueMap<String, String> paramMap, Model model) {
		
		Optional<Cardapio> cardapioOptional = cardapioService.findByDataTurno(paramMap.getFirst("data")+"|"+paramMap.getFirst("turno"));
		if(!cardapioOptional.isPresent()) {
			model.addAttribute("cardapio", cardapioNaoEncontrado());
		}
		else {
			model.addAttribute("cardapio", cardapioOptional.get());
		}
		return "gestor/gercardapios/consultar";
	
	}
	
	@PostMapping("/cadastrar")
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
		optional = gestorService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName());

		cardapio.setGestor(optional.get());
		
		cardapioService.save(cardapio);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Cardápio cadastrado com sucesso!");
	
	}
	
	@PostMapping("/atualizar")
	@ResponseBody
	public ResponseEntity<Object> updateCardapio(@RequestParam MultiValueMap<String, String> paramMap){
		if(!cardapioService.existsByDataTurno(paramMap.getFirst("data")+"|"+paramMap.getFirst("turno"))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado! Não existe um cardápio cadastrado nesta data e neste turno!");
		}
		
		Optional<Cardapio> cardapioOptional = cardapioService.findByDataTurno(paramMap.getFirst("data")+"|"+paramMap.getFirst("turno"));
		
		var cardapio = new Cardapio();
		
		cardapio = cardapioOptional.get();
		cardapio.setNome(paramMap.getFirst("nome"));
		cardapio.setDescricao(paramMap.getFirst("descricao"));
		cardapio.setData_alteracao(LocalDateTime.now(ZoneId.of("UTC")));

		Optional<Gestor> optional;
		optional = gestorService.findByCpf(SecurityContextHolder.getContext().getAuthentication().getName());

		cardapio.setGestor(optional.get());
		
		cardapioService.save(cardapio);
		
		return ResponseEntity.status(HttpStatus.OK).body("Cardápio atualizado com sucesso!");
	
	}
	
	public Cardapio cardapioNaoEncontrado() {
		
		Cardapio cardapio = new Cardapio();

		cardapio.setNome("Não encontrado");
		cardapio.setDescricao("");
		cardapio.setTurno("");
		cardapio.setData(LocalDate.of(1990, 1, 1));
		
		return cardapio;
		
	}
}
