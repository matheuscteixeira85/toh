package br.ufs.esii.toh.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufs.esii.toh.model.Role;
import br.ufs.esii.toh.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	
	@Transactional
	public Role save(Role role) {
		return roleRepository.save(role);
	}
	
	public List<Role> findAll(){
		return roleRepository.findAll();
	}
	
	public List<Role> findByNomeRole(String nomeRole){
		return roleRepository.findByNomeRole(nomeRole);
	}
	
	@Transactional
	public void delete(Role role) {
		roleRepository.delete(role);
	}
}
