package com.biblioteca.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.biblioteca.entity.Proveedor;
import com.biblioteca.service.ProveedorService;
import com.biblioteca.util.AppSettings;

@RestController
@RequestMapping("/url/proveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProveedorController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Proveedor>> listaProveedor(){
		List<Proveedor> lista = proveedorService.listaProveedor();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> inserta(@RequestBody Proveedor obj, Error errors){
		HashMap<String, Object> salida = new HashMap<>();
		
		obj.setFechaRegistro(new Date());
		obj.setFechaActualizacion(new Date());
		obj.setEstado(1);
		Proveedor objSalida = proveedorService.insertaActualizaProveedor(obj);
		if (objSalida == null) {
			salida.put("mensaje","Error en el registro ");
		}else {
			 salida.put("mensaje","Se registró el Proveedor con el ID ==> " + objSalida.getIdProveedor());
		} 
		return ResponseEntity.ok(salida);
	}

	@GetMapping("/listaProveedorConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>>listaProveedorRazonPaisEstado(

			@RequestParam(name = "razonsocial", required = false, defaultValue = "") String razonsocial,
			@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Proveedor> lista = proveedorService.listaProveedorPorRazonPaisEstado("%"+razonsocial+"%", idPais, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	//crud
		//lista por nombre
		@GetMapping("/listaProveedorPorNombre/{nom}")
		@ResponseBody
		public ResponseEntity<List<Proveedor>> listaProveedorPorNombreLike(@PathVariable("nom") String nom) {
			List<Proveedor> lista  = null;
			try {
				if (nom.equals("todos")) {
					lista = proveedorService.listaProveedorPorNombre("%");
				}else {
					lista = proveedorService.listaProveedorPorNombre("%" + nom + "%");	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok(lista);
		}
		
		
		//actualizar
		@PutMapping("/actualizaProveedor")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> actualizaProveedor(@RequestBody Proveedor obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				Proveedor objSalida =  proveedorService.actualizaproveedor(obj);
				if (objSalida == null) {
					salida.put("mensaje", "No se actualizó, consulte con el administrador.");
				} else {
					salida.put("mensaje", "Se actualizó correctamente.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje",  "No se actualizó, consulte con el administrador.");
			}
			return ResponseEntity.ok(salida);
		}
		//elimnar
		@DeleteMapping("/eliminaProveedor/{id}")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> eliminaProveedor(@PathVariable("id") int idProveedor) {
			Map<String, Object> salida = new HashMap<>();
			try {
				proveedorService.eliminaProveedor(idProveedor);
				salida.put("mensaje", "Se elimino correctamente");
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", "No se registró, consulte con el administrador.");
			}
			return ResponseEntity.ok(salida);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
}
