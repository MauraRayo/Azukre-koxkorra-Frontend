package com.ipartek.servicios;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.ipartek.modelo.Comentario;
import com.ipartek.modelo.Favorito;
import com.ipartek.modelo.Producto;
import com.ipartek.modelo.Usuario;

import jakarta.servlet.http.HttpSession;

@Service
public class FavoritoServicioImpl implements FavoritoServicio {
	
	private final RestTemplate restTemplate = new RestTemplate();
	private final String URL ="http://localhost:9090/api/favoritos";


	 @Override
	    public void insertarFavorito(Favorito favori) {

	        // Enviamos al endpoint REST
	        restTemplate.postForEntity(URL, favori, Favorito.class);
	    }	 
	 
	@Override
	public void borrarFavorito(Integer id) {
		restTemplate.delete(URL+"/"+id);	
	}

	@Override
	public void modificarFavorito(Favorito favori) {
		restTemplate.put(URL, favori, Favorito.class);
	}

	@Override
	public Favorito obtenerFavoritoPorId(Integer id) {
		Favorito favori =  restTemplate.getForObject(URL+"/"+id, Favorito.class);
		return favori;
	}

	@Override
	public List<Favorito> obtenerTodosFavoritos() {
		Favorito[] favori = restTemplate.getForObject(URL, Favorito[].class);
		return Arrays.asList(favori);
	}

	@Override
	public List<Favorito> obtenerFavoritosPorUsuario(String nombreUsuario) {
	    try {
	        // Traemos todos los favoritos y filtramos por nombre de usuario
	        Favorito[] favoritos = restTemplate.getForObject(URL, Favorito[].class);
	        return Arrays.stream(favoritos)
	                     .filter(f -> f.getUsuario() != null && nombreUsuario.equals(f.getUsuario().getName()))
	                     .toList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return List.of();
	    }
	}



}
