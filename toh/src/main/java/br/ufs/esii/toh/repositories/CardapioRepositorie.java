package br.ufs.esii.toh.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufs.esii.toh.model.Cardapio;

@Repository
public interface CardapioRepositorie extends JpaRepository<Cardapio, Integer>{
	Optional<Cardapio> findByDataTurno(String dataTurno);
	boolean existsByDataTurno(String dataTurno);
	List<Cardapio> findByData(LocalDate data);

}
