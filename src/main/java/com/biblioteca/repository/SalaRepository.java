package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer>{
	
	//JPQL
	//@Entity
	@Query("select x from Sala x where (?1 is '' or x.numero like ?1) and ( x.piso = ?2) and (?3 is -1 or x.sede.idDataCatalogo = ?3)")
	public List<Sala> listaSalaPorNumeroPisoTipo(String numero, int piso, int idSesde);

	@Query("select x from Sala x where x.numero like ?1")
	public List<Sala> listaPorNumeroLike(String numero);
	
}
