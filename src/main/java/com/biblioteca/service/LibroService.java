package com.biblioteca.service;


import java.util.List;

import com.biblioteca.entity.Libro;

public interface LibroService {
	
	public Libro insertaActualizaLibro(Libro obj);
	public List<Libro> listaLibro();
	public abstract List<Libro> consultaLibroPorNombreTipoCategoriaEstado(String titulo, int categoriaLibro, int tipoLibro);

	//crud
	public abstract Libro actualizalibro(Libro libro);
	public abstract List<Libro> listaLibroPorNombre(String nombre);
	public abstract void eliminaLibro(int idLibro);
}
