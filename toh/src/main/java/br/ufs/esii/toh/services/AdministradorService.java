package br.ufs.esii.toh.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufs.esii.toh.model.Administrador;
import br.ufs.esii.toh.repositories.AdministradorRepositorie;

@Service
public class AdministradorService {

	@Autowired
	AdministradorRepositorie administradorRepositorie;
	
	@Transactional
	public Administrador save(Administrador administrador) {
		return administradorRepositorie.save(administrador);
	}

	public boolean existsByCpf(String cpf) {
		// TODO Auto-generated method stub
		return administradorRepositorie.existsByCpf(cpf);
	}

	public boolean existsByMatricula(long matricula) {
		// TODO Auto-generated method stub
		return administradorRepositorie.existsByMatricula(matricula);
	}

	public List<Administrador> findAll() {
		// TODO Auto-generated method stub
		return administradorRepositorie.findAll();
	}

	public Optional<Administrador> findById(UUID id) {
		// TODO Auto-generated method stub
		return administradorRepositorie.findById(id);
	}

	public Optional<Administrador> findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return administradorRepositorie.findByCpf(cpf);
	}

	@Transactional
	public void delete(Administrador administrador) {
		// TODO Auto-generated method stub
		administradorRepositorie.delete(administrador);
	}
}
