package com.ipartek.servicios;

import java.util.List;

import com.ipartek.modelo.Comentario;

public interface ComentarioServicio {
	
	public void insertarComentario(Comentario comen);
	public void borrarComentario(Integer id);
	public void modificarComentario(Comentario comen);
	
	public Comentario obtenerComentarioPorId(Integer id);
	public List<Comentario> obtenerTodosComentarios();
	
	public List<Comentario> obtenerComentariosPorUsuario(String nombreUsuario);

}
