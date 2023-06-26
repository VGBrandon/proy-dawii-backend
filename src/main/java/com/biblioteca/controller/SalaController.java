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

import com.biblioteca.entity.Sala;
import com.biblioteca.service.SalaService;
import com.biblioteca.util.AppSettings;

@RestController
@RequestMapping("/url/sala")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SalaController {

	@Autowired
	private SalaService salaService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Sala>> listaSala(){
		List<Sala> lista = salaService.listaSala();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public  ResponseEntity<?> insertaSala( @RequestBody Sala obj, Errors errors){
		Map<String, Object> salida = new HashMap<>();
		List<String> lstMensajes = new ArrayList<>();
		salida.put("errores", lstMensajes);
		obj.setFechaRegistro(new Date());
	    obj.setFechaActualizacion(new Date());
	    obj.setEstado(1);
	   
		Sala objSalida = salaService.insertaActualizaSala(obj);
	
		if (objSalida == null) {
			lstMensajes.add("Error en el registro");
		}else {
			lstMensajes.add("Se registró la sala de ID ==> " + objSalida.getIdSala());
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaSalaConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaSalaNumeroPisoTipo(
			@RequestParam(name = "numero", required = false, defaultValue = "") String numero,
			@RequestParam(name = "piso", required = false, defaultValue = "1") int piso,
			@RequestParam(name = "idSesde", required = false, defaultValue = "-1") int idSesde) {
		Map<String, Object> salida = new HashMap<>();
		
		try {
			List<Sala> lista = salaService.listaSalaPorNumeroPisoTipo("%"+numero+"%", piso, idSesde);
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
	
	//COMIENZO PC3 RICHARD
	
	@DeleteMapping("/eliminaSala/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaSala(@PathVariable("id") int idSala) {
		Map<String, Object> salida = new HashMap<>();
		try {
			salaService.eliminaSala(idSala);
			salida.put("mensaje", "Se elimino correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	@PutMapping("/actualizaSala")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaSala(@RequestBody Sala obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Sala objSalida =  salaService.insertaActualizarSala(obj);
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
	
	
	
	@GetMapping("/listaSalaPorNumeroLike/{num}")
	@ResponseBody
	public ResponseEntity<List<Sala>> listaSalaPorNumeroLike(@PathVariable("num") String num) {
		List<Sala> lista  = null;
		try {
			if (num.equals("todos")) {
				lista = salaService.listaSalaPorNumeroLike("%");
			}else {
				lista = salaService.listaSalaPorNumeroLike("%" + num + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
}
