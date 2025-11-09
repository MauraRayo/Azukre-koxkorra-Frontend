package com.ipartek.servicios;

import java.util.List;

import com.ipartek.modelo.Localidad;

public interface LocalidadServicio {
	
	public void insertarLocalidad(Localidad locali);
	public void borrarLocalidad(Integer id);
	public void modificarLocalidad(Localidad locali);
	
	public Localidad obtenerLocalidadPorId(Integer id);
	public List<Localidad> obtenerTodasLocalidades();
	

}
