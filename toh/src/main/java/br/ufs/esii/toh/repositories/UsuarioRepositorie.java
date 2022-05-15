package br.ufs.esii.toh.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Usuario;

@Repository
public interface UsuarioRepositorie extends JpaRepository<Usuario, Long>{
	boolean existsByCpf(String cpf);
	Optional<Usuario> findByCpf(String cpf);
	Usuario findByLogin(String login);
}
