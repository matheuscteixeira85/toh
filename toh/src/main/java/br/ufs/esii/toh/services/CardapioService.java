package br.ufs.esii.toh.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufs.esii.toh.model.Cardapio;
import br.ufs.esii.toh.repositories.CardapioRepositorie;

@Service
public class CardapioService {

	@Autowired
	CardapioRepositorie cardapioRepositorie;
	
	@Transactional
	public Cardapio save(Cardapio cardapio) {
		return cardapioRepositorie.save(cardapio);
	}

	public List<Cardapio> findAll() {
		// TODO Auto-generated method stub
		return cardapioRepositorie.findAll();
	}

	public Optional<Cardapio> findById(Long id) {
		// TODO Auto-generated method stub
		return cardapioRepositorie.findById(id);
	}

	@Transactional
	public void delete(Cardapio cardapio) {
		// TODO Auto-generated method stub
		cardapioRepositorie.delete(cardapio);
	}
}
