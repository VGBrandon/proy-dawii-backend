package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
	
	@Query("select x from Alumno x where (x.nombres like ?1) and (?2 is -1 or x.pais.idPais = ?2)  and (?3 is -1 or x.modalidad.idDataCatalogo = ?3)  ")       
	public List<Alumno> listaAlumnoPorNombrePaisModalidad(String nombres, int idPais, int modalidad); 
	@Query("select x from Alumno x where x.nombres like ?1")
	public List<Alumno> listaPorNombre(String nombre);
}
