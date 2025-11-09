package com.ipartek.servicios;

import java.util.List;

import com.ipartek.modelo.Producto;

public interface ProductoServicio {

	

	public void insertarProducto(Producto produ);
	public void borrarProducto(Integer id);
	public void modificarProducto(Producto produ);
	
	public Producto obtenerProductoPorId(Integer id);
	public List<Producto> obtenerTodosProductos();
	
	public Producto obtenerProductoNombre(String nombre);
}
