package br.ufs.esii.toh.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufs.esii.toh.model.Atendente;
import br.ufs.esii.toh.repositories.AtendenteRepositorie;

@Service
public class AtendenteService {

	@Autowired
	AtendenteRepositorie atendenteRepositorie;
	
	@Transactional
	public Atendente save(Atendente atendente) {
		return atendenteRepositorie.save(atendente);
	}

	public boolean existsByCpf(String cpf) {
		// TODO Auto-generated method stub
		return atendenteRepositorie.existsByCpf(cpf);
	}

	public List<Atendente> findAll() {
		// TODO Auto-generated method stub
		return atendenteRepositorie.findAll();
	}

	public Optional<Atendente> findById(UUID id) {
		// TODO Auto-generated method stub
		return atendenteRepositorie.findById(id);
	}

	public Optional<Atendente> findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return atendenteRepositorie.findByCpf(cpf);
	}

	@Transactional
	public void delete(Atendente atendente) {
		// TODO Auto-generated method stub
		atendenteRepositorie.delete(atendente);
	}
}
