package com.ipartek.servicios;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ipartek.modelo.Producto;
import com.ipartek.modelo.Usuario;


@Service
public class ProductoServicioImpl implements ProductoServicio {
	
	private final RestTemplate restTemplate = new RestTemplate();
	private final String URL ="http://localhost:9090/api/productos";
	@Override
	public void insertarProducto(Producto produ) {
		restTemplate.postForObject(URL, produ, Producto.class);		
	}
	
	@Override
	public void borrarProducto(Integer id) {
		restTemplate.delete(URL+"/"+id);
		
	}
	@Override
	public void modificarProducto(Producto produ) {
		restTemplate.put(URL, produ, Producto.class);
		
	}
	@Override
	public Producto obtenerProductoPorId(Integer id) {
		Producto produ = restTemplate.getForObject(URL+"/"+id, Producto.class);
		return produ;
	}
	@Override
	public List<Producto> obtenerTodosProductos() {
		Producto[] produ = restTemplate.getForObject(URL, Producto[].class);
		return Arrays.asList(produ);
	}

	@Override
	public Producto obtenerProductoNombre(String nombre) {
		  try {
		        // Llama al endpoint REST que devuelve el usuario por nombre
		        return restTemplate.getForObject(URL + "/nombre/" + nombre, Producto.class);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
		}

}
