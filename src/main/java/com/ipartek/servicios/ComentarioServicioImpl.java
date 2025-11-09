package com.ipartek.servicios;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ipartek.modelo.Comentario;
import com.ipartek.modelo.Favorito;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {

	private final RestTemplate restTemplate = new RestTemplate();
	private final String URL = "http://localhost:9090/api/comentarios";

	@Override
	public void insertarComentario(Comentario comen) {
		restTemplate.postForEntity(URL, comen, Comentario.class);
	}

	@Override
	public void borrarComentario(Integer id) {
		restTemplate.delete(URL + "/" + id);
	}

	@Override
	public void modificarComentario(Comentario comen) {
		restTemplate.put(URL, comen, Comentario.class);
	}

	@Override
	public Comentario obtenerComentarioPorId(Integer id) {
		Comentario comen = restTemplate.getForObject(URL + "/" + id, Comentario.class);
		return comen;
	}

	@Override
	public List<Comentario> obtenerTodosComentarios() {
		Comentario[] comen = restTemplate.getForObject(URL, Comentario[].class);
		return Arrays.asList(comen);
	}

	@Override
	public List<Comentario> obtenerComentariosPorUsuario(String nombreUsuario) {
		try {
			// Traemos todos los comentarios y filtramos por nombre de usuario
			Comentario[] comentarios = restTemplate.getForObject(URL, Comentario[].class);
			return Arrays.stream(comentarios)
					.filter(f -> f.getUsuario() != null && nombreUsuario.equals(f.getUsuario().getName())).toList();
		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}

}
