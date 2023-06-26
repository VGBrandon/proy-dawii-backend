package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer>{

	@Query("select x from Libro x where (x.titulo like ?1) and (?2 is -1 or x.categoriaLibro.idDataCatalogo = ?2) and (?3 is -1 or x.tipoLibro.idDataCatalogo = ?3)    ")       
	public List<Libro> listaLibroPorNombreTipoCategoriaEstado(String titulo, int categoriaLibro, int tipoLibro); 
	
	@Query("select x from Libro x where x.titulo like ?1")
	public List<Libro> listaLibroxNombre(String nombre);
}

