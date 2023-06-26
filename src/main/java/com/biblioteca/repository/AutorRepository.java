package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entity.Autor;



public interface AutorRepository  extends JpaRepository<Autor, Integer>{

	
	//JPQL
	//Query no con tablas sino con clases que tienen @Entity
	
	@Query("select x from Autor x where (x.nombres like ?1) and (?2 is -1 or x.pais.idPais = ?2)  and (x.estado = ?3)  ")       
	public List<Autor> listaDocentePorNombreDniUbigeo(String nombres, int idPais, int estado); 
	
	
	
	@Query("select x from Autor x where x.nombres like ?1")
	public List<Autor> listaPorNombreLike(String nombre);
	
	
}
