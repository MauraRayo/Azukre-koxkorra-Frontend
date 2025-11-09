package com.ipartek.controlador;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ipartek.modelo.Categoria;
import com.ipartek.modelo.Comentario;
import com.ipartek.modelo.Favorito;
import com.ipartek.modelo.Producto;
import com.ipartek.modelo.Usuario;
import com.ipartek.servicios.CategoriaServicio;
import com.ipartek.servicios.ComentarioServicio;
import com.ipartek.servicios.FavoritoServicio;
import com.ipartek.servicios.LocalidadServicio;
import com.ipartek.servicios.ProductoServicio;

import jakarta.servlet.http.HttpSession;


@Controller
public class MenuControlador {

	@Autowired
	private ProductoServicio productoServ;

	@Autowired
	private LocalidadServicio localidadServ;

	@Autowired
	private CategoriaServicio categoriaServ;

	@Autowired
	private ComentarioServicio comentarioServ;

	@Autowired
	private FavoritoServicio favoritoServ;

	@GetMapping({ "/", "/Home" })
	public String home(Model model, HttpSession session) {

		model.addAttribute("obj_usuario", new Usuario());
		model.addAttribute("obj_producto", new Producto());

		List<Producto> produ = productoServ.obtenerTodosProductos();
		List<Comentario> comen = comentarioServ.obtenerTodosComentarios();

		// Mostrar solo los ultimos 5
		List<Producto> ultimosCinco = produ.stream().sorted(Comparator.comparing(Producto::getId).reversed()).limit(5)
				.collect(Collectors.toList());

		model.addAttribute("listaProductos", ultimosCinco);
		model.addAttribute("listaComentarios", comen);

		// Recuperar rol desde la sesión
		String rolUsuario = (String) session.getAttribute("rol"); // "USER" o "ADMIN"
		model.addAttribute("rolUsuario", rolUsuario);

		return "home";
	}

	/*----------------------------------------------------*/
	@GetMapping("/Login")
	public String login(Model model) {
		model.addAttribute("obj_usuario", new Usuario());
		return "login";
	}

	/*----------------------------------------------------*/
	@GetMapping("/MenuProductos")
	public String menuProductos(Model model) {

		model.addAttribute("listaProductos", productoServ.obtenerTodosProductos());
		return "productos";
	}

	/*----------------------------------------------------*/
	@GetMapping("/MenuFavoritos")
	public String menuFavorito(Model model, HttpSession session) {
	    String nombreUsuario = (String) session.getAttribute("name"); // nombre del usuario logueado
	    
	    if (nombreUsuario != null) {
	        List<Favorito> favoritos = favoritoServ.obtenerFavoritosPorUsuario(nombreUsuario);
	        model.addAttribute("listaFavoritos", favoritos);
	    } else {
	        model.addAttribute("listaFavoritos", List.of());
	    }
	    return "favoritos";
	}
	
	
	/*----------------------------------------------------*/
	@GetMapping("/MenuComentarios")
	public String menuComentarios(Model model, HttpSession session) {
	    String nombreUsuario = (String) session.getAttribute("name"); // nombre del usuario logueado
	    model.addAttribute("obj_comentario", new Comentario());
	    
	    if (nombreUsuario != null) {
	        List<Comentario> comentarios = comentarioServ.obtenerComentariosPorUsuario(nombreUsuario);
	        model.addAttribute("listaComentarios", comentarios);
	    } else {
	        model.addAttribute("listaComentarios", List.of());
	    }
	    return "comentarios";
	}
	
	@GetMapping("/CrearComentario")
	public String mostrarFormularioComentario(Model model, HttpSession session) {
		
		String nombreUsuario = (String) session.getAttribute("name");
		
		List<Producto> listaProductos = productoServ.obtenerTodosProductos();
		 comentarioServ.obtenerComentariosPorUsuario(nombreUsuario);
		
	    model.addAttribute("obj_comentario", new Comentario());
	    model.addAttribute("listaProductos", listaProductos);
	    
	    
	    return "crearComentario"; // o el nombre del HTML que muestre el formulario
	}


	/*----------------------------------------------------*/
	@GetMapping("/MenuContacto")
	public String menuContacto(Model model) {
		
		return "contacto";
	}

	/*------------------ADMINISTRADOR---------------------------------*/

	@GetMapping("/MenuProductosAdmin")
	public String productosAdmin(Model model, HttpSession session) {
		// Solo ADMIN puede acceder
		String rol = (String) session.getAttribute("rol");
		if (!"ADMIN".equals(rol)) {
			return "redirect:/Home"; // o mostrar página de acceso denegado
		}

		model.addAttribute("obj_producto", new Producto());
		model.addAttribute("listaProductos", productoServ.obtenerTodosProductos());
		model.addAttribute("listaLocalidades", localidadServ.obtenerTodasLocalidades());
		model.addAttribute("listaCategorias", categoriaServ.obtenerTodasCategorias());

		return "adminProductos";
	}
	
	

	@GetMapping("/MenuCategoriasAdmin")
	public String categoriasAdmin(Model model, HttpSession session) {
		// Solo ADMIN puede acceder
		String rol = (String) session.getAttribute("rol");
		if (!"ADMIN".equals(rol)) {
			return "redirect:/Home"; // o mostrar página de acceso denegado
		}

		model.addAttribute("obj_categoria", new Categoria());
		model.addAttribute("listaCategorias", categoriaServ.obtenerTodasCategorias());

		return "adminCategorias";
	}

}
