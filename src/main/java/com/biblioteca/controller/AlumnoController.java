package com.biblioteca.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;

/**
 * @@author Villegas Galvan Brandon
 */

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

import com.biblioteca.entity.Alumno;
import com.biblioteca.service.AlumnoService;
import com.biblioteca.util.AppSettings;

@RestController
@RequestMapping("/url/alumno")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AlumnoController {
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Alumno>> listaAlumno(){
		List<Alumno> lista = alumnoService.listaTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listaAlumnoConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaAlumnoNombrePaisModalidad(
			@RequestParam(name = "nombres", required = false, defaultValue = "") String nombres,
			@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "idModalidad", required = true, defaultValue = "-1") int modalidad) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Alumno> lista = alumnoService.listaAlumnoPorNombrePaisModalidad("%"+nombres+"%", idPais, modalidad);
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
	
	//CRUD
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> insertaAlumno(@RequestBody Alumno obj, Errors errors){
		Map<String, Object> salida = new HashMap<>();

		obj.setFechaRegistro(new Date());
		obj.setFechaActualizacion(new Date());
		obj.setEstado(1);
		
		Alumno objSalida = alumnoService.insertaActualizaAlumno(obj);
		if(objSalida == null) {
			salida.put("mensaje","Error en el registro");
		}else {
			salida.put("mensaje","Se registró el alumno con el ID ==> " + objSalida.getIdAlumno());
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaAlumnoPorNombre/{nombre}")
	@ResponseBody
	public ResponseEntity<List<Alumno>> listaAlumnoPorNombre(@PathVariable("nombre") String nombre) {
		List<Alumno> lista  = null;
		try {
			if (nombre.equals("todos")) {
				lista = alumnoService.listaAlumnoPorNombre("%");
			}else {
				lista = alumnoService.listaAlumnoPorNombre("%" + nombre + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping("/actualizaAlumno")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaAlumno(@RequestBody Alumno obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Alumno objSalida =  alumnoService.insertaActualizaAlumno(obj);
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
	
	@DeleteMapping("/eliminaAlumno/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaAlumno(@PathVariable("id") int idAlumno) {
		Map<String, Object> salida = new HashMap<>();
		try {
			alumnoService.eliminaAlumno(idAlumno);
			salida.put("mensaje", "Se eliminó al Alumno correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se eliminó al Alumno, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
}
