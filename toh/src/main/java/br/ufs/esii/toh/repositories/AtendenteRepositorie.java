package br.ufs.esii.toh.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Atendente;

@Repository
public interface AtendenteRepositorie extends JpaRepository<Atendente, UUID>{
	boolean existsByCpf(String cpf);
	Atendente findByLogin(String login);
	Optional<Atendente> findByCpf(String cpf);
}
