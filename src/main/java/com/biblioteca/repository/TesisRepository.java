package com.biblioteca.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.biblioteca.entity.Tesis;

public interface TesisRepository  extends JpaRepository<Tesis, Integer>{
	//JPQL(puedes ejecurtar con cualquier motor de bd) query con clases con @Entity
	@Query("select t from Tesis t where (?1 is '' or t.titulo like ?1) and (?2 is '' or t.tema like ?2) and (?3 is -1 or t.alumno.idAlumno = ?3)")
	public List<Tesis> listaTesisporTituloTemaAlumno(String titulo, String tema, int idAlumno);

	@Query("select t from Tesis t where t.titulo like ?1")
	public List<Tesis> listaPorTitulo(String titulo);

}
