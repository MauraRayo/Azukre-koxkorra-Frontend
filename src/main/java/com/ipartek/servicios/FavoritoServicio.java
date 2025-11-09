package com.ipartek.servicios;

import java.util.List;

import com.ipartek.modelo.Favorito;
import com.ipartek.modelo.Usuario;

public interface FavoritoServicio {
	
	public void insertarFavorito(Favorito favori);
	public void borrarFavorito(Integer id);
	public void modificarFavorito(Favorito favori);
	
	public Favorito obtenerFavoritoPorId(Integer id);
	public List<Favorito> obtenerTodosFavoritos();
	
	List<Favorito> obtenerFavoritosPorUsuario(String nombreUsuario);
	
	

	


}
