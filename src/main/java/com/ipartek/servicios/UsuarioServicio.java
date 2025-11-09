package com.ipartek.servicios;

import java.util.List;
import java.util.Optional;

import com.ipartek.modelo.Usuario;

public interface UsuarioServicio {
	
	public void insertarUsuario(Usuario usu);
	public void borrarUsuario(Integer id);
	public void modificarUsuario(Usuario usu);
	
	public Usuario obtenerUsuarioPorId(Integer id);
	public List<Usuario> obtenerTodosUsuario();

	Optional<Usuario> validarUsuario(String name, String pass);
	public Usuario obtenerUsuarioPorNombre(String name);
	
	
}
