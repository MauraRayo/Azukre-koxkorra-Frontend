package com.ipartek.servicios;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ipartek.modelo.Localidad;

@Service
public class LocalidadServicioImpl implements LocalidadServicio {
	
	private final RestTemplate restTemplate = new RestTemplate();
	private final String URL ="http://localhost:9090/api/localidades";

	
	@Override
	public void insertarLocalidad(Localidad locali) {
		 restTemplate.postForEntity(URL, locali, Localidad.class);
	}

	@Override
	public void borrarLocalidad(Integer id) {
		restTemplate.delete(URL+"/"+id);			
	}

	@Override
	public void modificarLocalidad(Localidad locali) {
		 restTemplate.put(URL, locali, Localidad.class);
	}

	@Override
	public Localidad obtenerLocalidadPorId(Integer id) {
		Localidad locali = restTemplate.getForObject(URL+"/"+id, Localidad.class);
		return locali;
	}

	@Override
	public List<Localidad> obtenerTodasLocalidades() {
		Localidad[] locali =  restTemplate.getForObject(URL, Localidad[].class);
		return Arrays.asList(locali);
	}


}
