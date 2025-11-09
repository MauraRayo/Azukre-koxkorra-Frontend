package com.ipartek.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ipartek.modelo.Comentario;
import com.ipartek.modelo.Usuario;
import com.ipartek.servicios.ComentarioServicio;
import com.ipartek.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
public class ComentarioControlador {

	@Autowired
	private ComentarioServicio comentarioServ;

	@Autowired
	private UsuarioServicio usuarioServ;

	@PostMapping("/InsertarComentario")
	public String crearComentario(@ModelAttribute Comentario obj_comentario, HttpSession session) {
		String nombreUsuario = (String) session.getAttribute("name");

		// Traer usuario completo desde backend
		Usuario usuario = usuarioServ.obtenerUsuarioPorNombre(nombreUsuario);
		if (usuario == null) {
			return "redirect:/Login"; // o mostrar error
		}

		obj_comentario.setUsuario(usuario);
		comentarioServ.insertarComentario(obj_comentario);

		return "redirect:/MenuComentarios";
	}

	@GetMapping("/BorrarComentario/{id}")
	public String borrarComentario(@PathVariable Integer id, HttpSession session) {
		String username = (String) session.getAttribute("name");

		if (username != null) {
			Comentario comen = comentarioServ.obtenerComentarioPorId(id);

			if (comen.getUsuario() != null && username.equals(comen.getUsuario().getName())) {
				comentarioServ.borrarComentario(id);
			}

		}

		return "redirect:/MenuComentarios";
	}

}
