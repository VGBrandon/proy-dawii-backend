package com.biblioteca.service;

import java.util.List;


import com.biblioteca.entity.Proveedor;

public interface ProveedorService {
	public Proveedor insertaActualizaProveedor (Proveedor obj);
	public List<Proveedor> listaProveedor();
	public abstract List<Proveedor> listaProveedorPorRazonPaisEstado(String razonsocial, int idPais, int estado); 
	
	//crud
		public abstract Proveedor actualizaproveedor(Proveedor proveedor);
		public abstract List<Proveedor> listaProveedorPorNombre(String nombre);
		public abstract void eliminaProveedor(int idProveedor);
}
