package com.biblioteca.service;

import java.util.List;

import com.biblioteca.entity.Opcion;
import com.biblioteca.entity.Rol;
import com.biblioteca.entity.Usuario;

public interface UsuarioService {

	public abstract Usuario login(Usuario bean);

	public abstract List<Opcion> traerEnlacesDeUsuario(int idUsuario);

	public abstract List<Rol> traerRolesDeUsuario(int idUsuario);

	public abstract Usuario buscaPorLogin(String login);
}
