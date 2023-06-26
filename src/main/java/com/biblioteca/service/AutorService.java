package com.biblioteca.service;

import java.util.List;

import com.biblioteca.entity.Autor;




public interface AutorService {


	public Autor insertaActualizaModalidad(Autor obj);
	public abstract List<Autor> listaAutorPorNombrePaisEstado(String nombres, int idPais, int estado);
	public abstract void eliminaAutor(int idDocente);
	public abstract Autor insertaActualizaAutor(Autor autor);
	public abstract List<Autor> listaAutorPorNombreLike(String nombre);
}
