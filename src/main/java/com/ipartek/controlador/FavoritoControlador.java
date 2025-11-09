package com.ipartek.controlador;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipartek.modelo.Comentario;
import com.ipartek.modelo.Favorito;
import com.ipartek.modelo.Producto;
import com.ipartek.modelo.Usuario;
import com.ipartek.servicios.FavoritoServicio;
import com.ipartek.servicios.ProductoServicio;
import com.ipartek.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
public class FavoritoControlador {
	
	@Autowired
	private FavoritoServicio favoritoServ;
	
	@Autowired
	private ProductoServicio productoServ;
	
	@Autowired
	private UsuarioServicio usuarioServ;


	@PostMapping("/InsertarFavorito")
	public String insertarFavorito(@ModelAttribute Favorito obj_favorito, HttpSession session) {
		String nombreUsuario = (String) session.getAttribute("name");

		// Traer usuario completo desde backend
		Usuario usuario = usuarioServ.obtenerUsuarioPorNombre(nombreUsuario);
	
		if (usuario == null) {
			return "redirect:/Login"; // o mostrar error
		}

		obj_favorito.setUsuario(usuario);

		favoritoServ.insertarFavorito(obj_favorito);

		return "redirect:/MenuFavorito";
	}
	
	

	
	@GetMapping("/FavoritoBorrar/{id}")
	public String borrarFavorito(@PathVariable Integer id, HttpSession session) {

	    // Opcional: solo permitir borrar si el favorito pertenece al usuario logueado
	    String username = (String) session.getAttribute("name");
	    if (username != null) {
	        Favorito f = favoritoServ.obtenerFavoritoPorId(id);
	        if (f.getUsuario() != null && username.equals(f.getUsuario().getName())) {
	            favoritoServ.borrarFavorito(id);
	        }
	    }

	    // Redirige de vuelta a la página de favoritos
	    return "redirect:/MenuFavoritos";
	}

	
	
}
