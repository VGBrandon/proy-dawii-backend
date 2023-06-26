package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Alumno;
import com.biblioteca.repository.AlumnoRepository;

@Service
public class AlumnoServiceImp implements AlumnoService {

	@Autowired
	private AlumnoRepository repository;

	@Override
	public List<Alumno> listaTodos() {
		return repository.findAll();
	}

	@Override
	public Alumno insertaActualizaAlumno(Alumno obj) {
		return repository.save(obj);
	}
	
	@Override
	public List<Alumno> listaAlumnoPorNombrePaisModalidad(String nombres, int idPais, int modalidad) {
		return repository.listaAlumnoPorNombrePaisModalidad(nombres, idPais, modalidad);
		
	}
	
	@Override
	public void eliminaAlumno(int idAlumno) {
		repository.deleteById(idAlumno);
	}

	@Override
	public List<Alumno> listaAlumnoPorNombre(String nombre) {
		return repository.listaPorNombre(nombre);
	}
}
