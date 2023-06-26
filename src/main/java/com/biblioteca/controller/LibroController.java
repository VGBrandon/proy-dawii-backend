package com.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.biblioteca.service.LibroService;
import com.biblioteca.util.AppSettings;
import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Libro;

/** 
 * 
 * @author Baygorrea
 *
 */
@RestController
@RequestMapping("/url/libro")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Libro>> listaAlumno(){
		List<Libro> lista = libroService.listaLibro();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> inserta(@RequestBody Libro obj, Error errors){
		HashMap<String, Object> salida = new HashMap<>();
		
		obj.setFechaRegistro(new Date());
		obj.setFechaActualizacion(new Date());
		obj.setEstado(1);
		Libro objSalida = libroService.insertaActualizaLibro(obj);
		if (objSalida == null) {
			salida.put("mensaje","Error en el registro");
		}else {
			salida.put("mensaje","Se registró el Libro con el ID ==> " + objSalida.getIdLibro());
		} 
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaLibroConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> consultarlibro(
			@RequestParam(name = "titulo", required = false, defaultValue = "") String titulo,
			@RequestParam(name = "idCategoriaLibro", required = false, defaultValue = "-1") int categoriaLibro,
			@RequestParam(name = "idTipoLibro", required = false, defaultValue = "-1") int tipoLibro) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Libro> lista = libroService.consultaLibroPorNombreTipoCategoriaEstado("%"+titulo+"%", categoriaLibro, tipoLibro);
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
	
	//crud
	//lista por nombre
	@GetMapping("/listaLibroPorNombre/{nom}")
	@ResponseBody
	public ResponseEntity<List<Libro>> listaLibroPorNombreLike(@PathVariable("nom") String nom) {
		List<Libro> lista  = null;
		try {
			if (nom.equals("todos")) {
				lista = libroService.listaLibroPorNombre("%");
			}else {
				lista = libroService.listaLibroPorNombre("%" + nom + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	
	//actualizar
	@PutMapping("/actualizaLibro")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaLibro(@RequestBody Libro obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Libro objSalida =  libroService.actualizalibro(obj);
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
	@DeleteMapping("/eliminaLibro/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaLibro(@PathVariable("id") int idLibro) {
		Map<String, Object> salida = new HashMap<>();
		try {
			libroService.eliminaLibro(idLibro);
			salida.put("mensaje", "Se elimino correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
}






