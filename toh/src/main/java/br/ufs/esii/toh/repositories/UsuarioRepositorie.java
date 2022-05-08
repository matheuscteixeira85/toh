package br.ufs.esii.toh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Usuario;

@Repository
public interface UsuarioRepositorie extends JpaRepository<Usuario, Long>{
	boolean existsByCpf(String cpf);
}
