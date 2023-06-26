package com.biblioteca.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entity.Prueba;
import com.biblioteca.service.PruebaService;
import com.biblioteca.util.AppSettings;


@RestController
@RequestMapping("/url/prueba")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class PruebaController {

	@Autowired
	private PruebaService pruebaService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Prueba>> listaAlumno(){
		List<Prueba> lista = pruebaService.listaPrueba();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> inserta(@RequestBody Prueba obj, Errors errors){
		HashMap<String, Object> salida = new HashMap<>();
		
		Prueba objSalida = pruebaService.insertaActualizaPrueba(obj);
		if (objSalida == null) {
			salida.put("mensaje","Error en el registro");
		}else {
			salida.put("mensaje","Se registró la prueba con el ID ==> " + objSalida.getIdPrueba());
		}
		return ResponseEntity.ok(salida);
	}
}
