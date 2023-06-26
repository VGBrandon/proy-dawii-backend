package com.biblioteca.service;

import java.util.List;

import com.biblioteca.entity.Alumno;

public interface AlumnoService {

	public abstract List<Alumno> listaTodos();

	public Alumno insertaActualizaAlumno(Alumno obj);
	public abstract List<Alumno> listaAlumnoPorNombrePaisModalidad(String nombres, int idPais, int estado);
	public abstract void eliminaAlumno(int idAlumno);
	public abstract List<Alumno> listaAlumnoPorNombre(String nombre);
}
