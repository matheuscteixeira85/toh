package br.ufs.esii.toh.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Atendente;
import br.ufs.esii.toh.repositories.AtendenteRepositorie;

@Repository
@Transactional
public class ImplementsAtendenteDetailsService implements UserDetailsService{

	@Autowired
	private AtendenteRepositorie atendenteRepositorie;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Atendente atendente = atendenteRepositorie.findByLogin(login);
		if(atendente == null) {
			throw new UsernameNotFoundException("Login não encontrado!");
		}
		
		return new User(atendente.getUsername(), atendente.getPassword(), true, true, true, true, atendente.getAuthorities());
	}
	
}