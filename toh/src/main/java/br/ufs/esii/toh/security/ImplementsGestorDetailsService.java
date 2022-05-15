package br.ufs.esii.toh.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Gestor;
import br.ufs.esii.toh.repositories.GestorRepositorie;

@Repository
@Transactional
public class ImplementsGestorDetailsService implements UserDetailsService{

	@Autowired
	private GestorRepositorie gestorRepositorie;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Gestor gestor = gestorRepositorie.findByLogin(login);
		if(gestor == null) {
			throw new UsernameNotFoundException("Login não encontrado!");
		}
		
		return new User(gestor.getUsername(), gestor.getPassword(), true, true, true, true, gestor.getAuthorities());
	}
	
}