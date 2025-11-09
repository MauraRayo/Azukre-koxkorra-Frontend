package com.ipartek.servicios;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ipartek.modelo.Categoria;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

	
	private final RestTemplate restTemplate = new RestTemplate();
	private final String URL ="http://localhost:9090/api/categorias";

	@Override
	public void insertarCategoria(Categoria catego) {
		 restTemplate.postForEntity(URL, catego, Categoria.class);
	}

	@Override
	public void borrarCategoria(Integer id) {
		restTemplate.delete(URL+"/"+id);	
	}

	@Override
	public void modificarCategoria(Categoria catego) {
		 restTemplate.put(URL, catego, Categoria.class);
		
	}

	@Override
	public Categoria obtenerCategoriaPorId(Integer id) {
		Categoria catego =  restTemplate.getForObject(URL+"/"+id, Categoria.class);
		return catego;
	}

	@Override
	public List<Categoria> obtenerTodasCategorias() {
		Categoria[] catego = restTemplate.getForObject(URL, Categoria[].class);
		return Arrays.asList(catego);
	}


}
