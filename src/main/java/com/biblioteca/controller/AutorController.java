package com.biblioteca.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
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

import com.biblioteca.entity.Autor;
import com.biblioteca.service.AutorService;
import com.biblioteca.util.AppSettings;






@RestController
@RequestMapping("/url/autor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AutorController {
	/**
	 * ROODY RAMIREZ CALLA ====>> Registro Autor
	 */
	
	
	@Autowired
	private AutorService autorService;


	@PostMapping
	@ResponseBody
	public  ResponseEntity<?> insertaModalidad( @RequestBody Autor obj, Errors errors){
		Map<String, Object> salida = new HashMap<>();
		List<String> lstMensajes = new ArrayList<>();
		
		salida.put("errores", lstMensajes);
		
		obj.setFechaRegistro(new Date());
		obj.setFechaActualizacion(new Date());
		obj.setEstado(1);
		

		Autor objSalida = autorService.insertaActualizaModalidad(obj);
		if (objSalida == null) {
			lstMensajes.add("Error en el registro");
		}else {
			lstMensajes.add("Se registró el Autor de ID ==> " + objSalida.getIdAutor());
		}
		
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/listaAutorConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaAutorNombrePaisGrado(
			@RequestParam(name = "nombres", required = false, defaultValue = "") String nombres,
			@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Autor> lista = autorService.listaAutorPorNombrePaisEstado("%"+nombres+"%", idPais, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró, consulte con el administrador.");
			//salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	
	@DeleteMapping("/eliminaAutor/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaDocente(@PathVariable("id") int idAutor) {
		Map<String, Object> salida = new HashMap<>();
		try {
			autorService.eliminaAutor(idAutor);
			salida.put("mensaje", "Se elimino correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	@PutMapping("/actualizaAutor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaDocente(@RequestBody Autor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Autor objSalida =  autorService.insertaActualizaAutor(obj);
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
	
	
	
	@GetMapping("/listaAutorPorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Autor>> listaDocentePorNombreLike(@PathVariable("nom") String nom) {
		List<Autor> lista  = null;
		try {
			if (nom.equals("todos")) {
				lista = autorService.listaAutorPorNombreLike("%");
			}else {
				lista = autorService.listaAutorPorNombreLike("%" + nom + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	
	
	
	
}
