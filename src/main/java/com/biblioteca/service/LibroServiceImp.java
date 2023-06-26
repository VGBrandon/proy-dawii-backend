package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Libro;
import com.biblioteca.repository.LibroRepository;

@Service
public class LibroServiceImp implements LibroService {
	
	

	@Autowired
	private LibroRepository repository;

	@Override
	public Libro insertaActualizaLibro(Libro obj) {
		return repository.save(obj);
	}

	@Override
	public List<Libro> listaLibro() {
		return repository.findAll();
	}
	@Override
	public List<Libro> consultaLibroPorNombreTipoCategoriaEstado(String titulo, int categoriaLibro, int tipoLibro) {
		return repository.listaLibroPorNombreTipoCategoriaEstado(titulo, categoriaLibro,tipoLibro);
	}
	
	@Override
	public void eliminaLibro(int idLibro) {
		repository.deleteById(idLibro);
	}

	
	@Override
	public Libro actualizalibro(Libro libro) {
		return repository.save(libro);
	}
	
	
	
	
	@Override
	public List<Libro> listaLibroPorNombre(String nombre) {
		return repository.listaLibroxNombre(nombre);
	}

}
