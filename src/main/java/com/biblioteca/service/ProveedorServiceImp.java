package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.entity.Proveedor;
import com.biblioteca.repository.ProveedorRepository;

@Service
public class ProveedorServiceImp implements ProveedorService{

	@Autowired
	private ProveedorRepository repository;
	
	@Override
	public Proveedor insertaActualizaProveedor(Proveedor obj) {
		
		return repository.save(obj);
	}

	@Override
	public List<Proveedor> listaProveedor() {
		return repository.findAll();
	}

	@Override
	public List<Proveedor> listaProveedorPorRazonPaisEstado(String razonsocial, int idPais, int estado) {
			return repository.listaProveedorPorRazonPaisEstado(razonsocial, idPais, estado);
			
	}

	@Override
	public Proveedor actualizaproveedor(Proveedor proveedor) {
		return repository.save(proveedor);
	}

	@Override
	public List<Proveedor> listaProveedorPorNombre(String nombre) {
		return repository.listaProveedorxNombre(nombre);
	}

	@Override
	public void eliminaProveedor(int idProveedor) {
		repository.deleteById(idProveedor);
		
	}



}
