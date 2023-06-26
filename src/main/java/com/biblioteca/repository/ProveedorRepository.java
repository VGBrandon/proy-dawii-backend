package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.biblioteca.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor,Integer>{
	
	@Query("select x from Proveedor x where (x.razonsocial like ?1) and (-1 is ?2 or x.pais.idPais = ?2)  and (-1 is ?3 or x.estado = ?3)  ")       
	 public List<Proveedor> listaProveedorPorRazonPaisEstado(String razonsocial, int idPais, int estado); 
	
	
	@Query("select x from Proveedor x where x.razonsocial like ?1")
	public List<Proveedor> listaProveedorxNombre(String nombre);
}

