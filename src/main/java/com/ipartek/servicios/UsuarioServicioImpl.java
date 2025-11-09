package com.ipartek.servicios;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ipartek.modelo.Usuario;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	private final RestTemplate restTemplate = new RestTemplate();
	private final String URL = "http://localhost:9090/api/usuarios";

	@Override
	public void insertarUsuario(Usuario usu) {
		restTemplate.postForObject(URL, usu, Usuario.class);
	}

	@Override
	public void borrarUsuario(Integer id) {
		restTemplate.delete(URL + "/" + id);
	}

	@Override
	public void modificarUsuario(Usuario usu) {
		restTemplate.postForObject(URL, usu, Usuario.class);
	}

	@Override
	public Usuario obtenerUsuarioPorId(Integer id) {
		Usuario usu = restTemplate.getForObject(URL + "/" + id, Usuario.class);
		return usu;
	}

	@Override
	public List<Usuario> obtenerTodosUsuario() {
		Usuario[] usu = restTemplate.getForObject(URL, Usuario[].class);
		return Arrays.asList(usu);
	}


	@Override
	public Optional<Usuario> validarUsuario(String name, String pass) {
	    try {
	        Boolean valido = restTemplate.getForObject(URL + "/validar/" + name + "/" + pass, Boolean.class);

	        if (Boolean.TRUE.equals(valido)) {
	            // Si quieres devolver el usuario completo:
	            Usuario usuario = restTemplate.getForObject(URL + "/nombre/" + name, Usuario.class);
	            return Optional.ofNullable(usuario);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return Optional.empty();
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String name) {
	    try {
	        // Llama al endpoint REST que devuelve el usuario por nombre
	        return restTemplate.getForObject(URL + "/nombre/" + name, Usuario.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}



}
