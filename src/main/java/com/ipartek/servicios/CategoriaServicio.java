package com.ipartek.servicios;

import java.util.List;

import com.ipartek.modelo.Categoria;


public interface CategoriaServicio {

	public void insertarCategoria(Categoria catego);
	public void borrarCategoria(Integer id);
	public void modificarCategoria(Categoria catego);

	public Categoria obtenerCategoriaPorId(Integer id);
	public List<Categoria> obtenerTodasCategorias();
}
