package br.ufs.esii.toh.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufs.esii.toh.model.Refeicao;
import br.ufs.esii.toh.repositories.RefeicaoRepositorie;

@Service
public class RefeicaoService {

	@Autowired
	RefeicaoRepositorie refeicaoRepositorie;
	
	@Transactional
	public Refeicao save(Refeicao refeicao) {
		return refeicaoRepositorie.save(refeicao);
	}

	public List<Refeicao> findAll() {
		// TODO Auto-generated method stub
		return refeicaoRepositorie.findAll();
	}

	public Optional<Refeicao> findById(Long id) {
		// TODO Auto-generated method stub
		return refeicaoRepositorie.findById(id);
	}

	@Transactional
	public void delete(Refeicao refeicao) {
		// TODO Auto-generated method stub
		refeicaoRepositorie.delete(refeicao);
	}
}
