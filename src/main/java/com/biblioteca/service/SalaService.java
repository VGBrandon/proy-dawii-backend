package com.biblioteca.service;

import java.util.List;


import com.biblioteca.entity.Sala;


public interface SalaService {

	public Sala insertaActualizaSala(Sala obj);
	public List<Sala> listaSala();
	public List<Sala> listaSalaPorNumeroPisoTipo(String numero, int piso, int idSesde);
	public abstract void eliminaSala(int idSala);
	public abstract Sala insertaActualizarSala(Sala sala);
	public abstract List<Sala> listaSalaPorNumeroLike(String numero);

}
