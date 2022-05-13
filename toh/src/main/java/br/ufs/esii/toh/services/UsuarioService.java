package br.ufs.esii.toh.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufs.esii.toh.model.Usuario;
import br.ufs.esii.toh.repositories.UsuarioRepositorie;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepositorie usuarioRepositorie;
	
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioRepositorie.save(usuario);
	}

	public boolean existsByCpf(String cpf) {
		// TODO Auto-generated method stub
		return usuarioRepositorie.existsByCpf(cpf);
	}

	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepositorie.findAll();
	}

	public Optional<Usuario> findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepositorie.findById(id);
	}

	public Optional<Usuario> findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return usuarioRepositorie.findByCpf(cpf);
	}

	@Transactional
	public void delete(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepositorie.delete(usuario);
	}
}
