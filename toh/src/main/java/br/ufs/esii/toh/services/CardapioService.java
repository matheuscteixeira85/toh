package br.ufs.esii.toh.services;

import java.time.LocalDate;
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

	public Optional<Cardapio> findById(int id) {
		// TODO Auto-generated method stub
		return cardapioRepositorie.findById(id);
	}

	public Optional<Cardapio> findByDataTurno(String dataTurno) {
		// TODO Auto-generated method stub
		return cardapioRepositorie.findByDataTurno(dataTurno);
	}

	public List<Cardapio> findByData(LocalDate data) {
		// TODO Auto-generated method stub
		return cardapioRepositorie.findByData(data);
	}
	
	public boolean existsByDataTurno(String dataTurno) {
		return cardapioRepositorie.existsByDataTurno(dataTurno);
	}

	@Transactional
	public void delete(Cardapio cardapio) {
		// TODO Auto-generated method stub
		cardapioRepositorie.delete(cardapio);
	}
}
