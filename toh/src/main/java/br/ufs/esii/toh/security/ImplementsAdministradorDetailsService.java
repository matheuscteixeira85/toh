package br.ufs.esii.toh.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Administrador;
import br.ufs.esii.toh.repositories.AdministradorRepositorie;

@Repository
@Transactional
public class ImplementsAdministradorDetailsService implements UserDetailsService{

	@Autowired
	private AdministradorRepositorie administradorRepositorie;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Administrador administrador = administradorRepositorie.findByLogin(login);
		if(administrador == null) {
			throw new UsernameNotFoundException("Login não encontrado!");
		}
		
		return new User(administrador.getUsername(), administrador.getPassword(), true, true, true, true, administrador.getAuthorities());
	}
	
}