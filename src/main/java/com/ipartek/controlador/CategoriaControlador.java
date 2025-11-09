package com.ipartek.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.ipartek.modelo.Categoria;
import com.ipartek.servicios.CategoriaServicio;



@Controller
public class CategoriaControlador {
	
	
	@Autowired
	private CategoriaServicio categoriaServ;
	
	@PostMapping("/InsertarCategoria")
	public String insertarCategoria(@ModelAttribute Categoria obj_categoria) {

		categoriaServ.insertarCategoria(obj_categoria);

		return "redirect:/MenuProductosAdmin";
	}
	
	

}
