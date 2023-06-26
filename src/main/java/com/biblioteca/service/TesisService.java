package com.biblioteca.service;

import java.util.List;

import com.biblioteca.entity.Tesis;

public interface TesisService {
	public abstract List<Tesis> listaTodos();
	
	public List<Tesis> listaTesisporTituloTemaAlumno(String titulo, String tema, int idAlumno);
	public Tesis insertaActualizaTesis(Tesis obj);
	public abstract void eliminaTesis(int idTesis);
	public abstract List<Tesis> listaTesisPorTitulo(String nombre);
}
