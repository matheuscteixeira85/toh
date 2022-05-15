package br.ufs.esii.toh.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Usuario;
import br.ufs.esii.toh.repositories.UsuarioRepositorie;

@Repository
@Transactional
public class ImplementsUsuarioDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepositorie usuarioRepositorie;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorie.findByLogin(login);
		if(usuario == null) {
			throw new UsernameNotFoundException("Login não encontrado!");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}
	
}
