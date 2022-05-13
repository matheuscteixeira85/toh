package br.ufs.esii.toh.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.repositories.GestorRepositorie;

@Service
public class GestorService {

	@Autowired
	GestorRepositorie gestorRepositorie;
	
	@Transactional
	public Gestor save(Gestor gestor) {
		return gestorRepositorie.save(gestor);
	}

	public boolean existsByCpf(String cpf) {
		// TODO Auto-generated method stub
		return gestorRepositorie.existsByCpf(cpf);
	}

	public List<Gestor> findAll() {
		// TODO Auto-generated method stub
		return gestorRepositorie.findAll();
	}

	public Optional<Gestor> findById(UUID id) {
		// TODO Auto-generated method stub
		return gestorRepositorie.findById(id);
	}

	public Optional<Gestor> findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return gestorRepositorie.findByCpf(cpf);
	}

	@Transactional
	public void delete(Gestor gestor) {
		// TODO Auto-generated method stub
		gestorRepositorie.delete(gestor);
	}
}
