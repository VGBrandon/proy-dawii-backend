package com.biblioteca.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.entity.Tesis;
import com.biblioteca.repository.TesisRepository;

@Service
public class TesisServiceImpl implements TesisService{

	@Autowired
	private TesisRepository repository;
	
	@Override
	public List<Tesis> listaTesisporTituloTemaAlumno(String titulo, String tema, int idAlumno) {
		return repository.listaTesisporTituloTemaAlumno(titulo, tema, idAlumno);
	}

	@Override
	public List<Tesis> listaTodos() {
		return repository.findAll();
	}

	@Override
	public Tesis insertaActualizaTesis(Tesis obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminaTesis(int idTesis) {
		repository.deleteById(idTesis);
		
	}

	@Override
	public List<Tesis> listaTesisPorTitulo(String nombre) {
		return repository.listaPorTitulo(nombre);
	}

}
