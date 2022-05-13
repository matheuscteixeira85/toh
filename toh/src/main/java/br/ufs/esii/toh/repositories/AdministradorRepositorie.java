package br.ufs.esii.toh.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Administrador;

@Repository
public interface AdministradorRepositorie extends JpaRepository<Administrador, UUID>{
	boolean existsByCpf(String cpf);
	boolean existsByMatricula(long matricula);
	Optional<Administrador> findByCpf(String cpf);
}
