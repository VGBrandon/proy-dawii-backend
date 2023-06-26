package com.biblioteca.controller;
/*
 *   Riveros
 */

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
import com.biblioteca.entity.Tesis;
import com.biblioteca.service.TesisService;
import com.biblioteca.util.AppSettings;

@RestController
@RequestMapping("/url/tesis")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TesisController {

	@Autowired
	private TesisService tesisService;
	

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Tesis>> listaTesis(){
		List<Tesis> lista = tesisService.listaTodos();
		return ResponseEntity.ok(lista);
	}
	@GetMapping("/listaTesisConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaTesisTituloTemaAlumno(
			@RequestParam(name = "titulo", required = false, defaultValue = "") String titulo,
			@RequestParam(name = "tema", required = false, defaultValue = "") String tema,
			@RequestParam(name = "idAlumno", required = false, defaultValue = "-1") int idAlumno) {
		Map<String, Object> salida = new HashMap<>();
		
		try {
			//listaSalaPorNumeroPisoTipo("%"+numero+"%", piso, idSesde);
			List<Tesis> lista = tesisService.listaTesisporTituloTemaAlumno("%"+titulo+"%","%"+tema+"%", idAlumno);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el sistema");
		}
		return ResponseEntity.ok(salida);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> insertaTesis(@RequestBody Tesis obj, Errors errors){
		Map<String, Object> salida = new HashMap<>();

		obj.setFechaCreacion(new Date());
		obj.setFechaRegistro(new Date());
		obj.setEstado(1);
		
		Tesis objSalida = tesisService.insertaActualizaTesis(obj);
		if(objSalida == null) {
			salida.put("mensaje","Error en el registro");
		}else {
			salida.put("mensaje","Se registró la tesis con el ID ==> " + objSalida.getIdTesis());
		}
		return ResponseEntity.ok(salida);
	}
	@GetMapping("/listaTesisPorTitulo/{titulo}")
	@ResponseBody
	public ResponseEntity<List<Tesis>> listaTesisPorTitulo(@PathVariable("titulo") String titulo) {
		List<Tesis> lista  = null;
		try {
			if (titulo.equals("todos")) {
				lista = tesisService.listaTesisPorTitulo("%");
			}else {
				lista = tesisService.listaTesisPorTitulo("%" + titulo + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	

	@PutMapping("/actualizaTesis")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaTesis(@RequestBody Tesis obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Tesis objSalida =  tesisService.insertaActualizaTesis(obj);
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
	
	@DeleteMapping("/eliminaTesis/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaTesis(@PathVariable("id") int idTesis) {
		Map<String, Object> salida = new HashMap<>();
		try {
			tesisService.eliminaTesis(idTesis);
			salida.put("mensaje", "Se eliminó al Alumno correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se eliminó la tesis, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
}
