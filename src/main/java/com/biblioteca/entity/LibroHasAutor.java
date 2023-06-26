package com.biblioteca.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "libro_tiene_autor")
public class LibroHasAutor {

	
	@EmbeddedId
	private LibroHasAutorPK libroHasAutorPK;

	@ManyToOne
	@JoinColumn(name = "idLibro", nullable = false, insertable = false, updatable = false)
	private Libro  libro;
	
	@ManyToOne
	@JoinColumn(name = "idAutor", nullable = false, insertable = false, updatable = false)
	private Autor autor;

	
	
}
