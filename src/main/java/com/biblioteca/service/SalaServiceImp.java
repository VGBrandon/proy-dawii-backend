package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Sala;
import com.biblioteca.repository.SalaRepository;

@Service
public class SalaServiceImp implements SalaService {

	@Autowired
	private SalaRepository repositorio;

	@Override
	public Sala insertaActualizaSala(Sala obj) {
		return repositorio.save(obj);
	}

	@Override
	public List<Sala> listaSala() {
		return repositorio.findAll();
	}

	@Override
	public List<Sala> listaSalaPorNumeroPisoTipo(String numero, int piso, int idSesde) {
		return repositorio.listaSalaPorNumeroPisoTipo(numero, piso, idSesde);
	}

	@Override
	public void eliminaSala(int idSala) {
		repositorio.deleteById(idSala);
	}

	@Override
	public Sala insertaActualizarSala(Sala sala) {
		return repositorio.save(sala);
	}

	@Override
	public List<Sala> listaSalaPorNumeroLike(String numero) {
		return repositorio.listaPorNumeroLike(numero);
	}
	
}
