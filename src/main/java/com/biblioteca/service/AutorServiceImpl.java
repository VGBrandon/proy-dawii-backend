package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Autor;
import com.biblioteca.repository.AutorRepository;



@Service
public class AutorServiceImpl implements AutorService{

	@Autowired
	private AutorRepository repositorio;
	
	
	@Override
	public Autor insertaActualizaModalidad(Autor obj) {
		return repositorio.save(obj);
	}


	@Override
	public List<Autor> listaAutorPorNombrePaisEstado(String nombres, int idPais, int estado) {
		return repositorio.listaDocentePorNombreDniUbigeo(nombres, idPais, estado);
	}

	@Override
	public void eliminaAutor(int idAutor) {
		repositorio.deleteById(idAutor);
	}

	
	@Override
	public Autor insertaActualizaAutor(Autor autor) {
		return repositorio.save(autor);
	}
	
	
	
	
	@Override
	public List<Autor> listaAutorPorNombreLike(String nombre) {
		return repositorio.listaPorNombreLike(nombre);
	}
	
	
	
	
	
	
	
	
}
