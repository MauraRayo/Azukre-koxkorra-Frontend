package com.ipartek.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ipartek.componentes.JwtUtil;
import com.ipartek.modelo.Usuario;
import com.ipartek.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginControlador {

	 @Autowired
	    private UsuarioServicio usuarioServ;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @PostMapping("/Login")
	    public String validarUsuario(@ModelAttribute Usuario obj_usuario, Model model, HttpSession session) {

	        var usuarioOpt = usuarioServ.validarUsuario(obj_usuario.getName(), obj_usuario.getPass());

	        if (usuarioOpt.isPresent()) {
	            Usuario usuario = usuarioOpt.get();

	            // Generar token JWT
	            String token = jwtUtil.generateToken(usuario.getName(), usuario.getRol());

	            // Guardar en sesión
	            session.setAttribute("jwt_token", token);
	            session.setAttribute("name", usuario.getName());
	            session.setAttribute("rol", usuario.getRol());

	            System.out.println("Login exitoso. Redirigiendo a menu");
	            return "redirect:/Home";
	        } else {
	            model.addAttribute("error", "Credenciales inválidas");
	            model.addAttribute("obj_usuario", new Usuario());
	            System.out.println("Login no funcionooo. Redirigiendo a menu");
	            
	            return "login";
	        }
	    }
	    
	    @GetMapping("/Logout")
	    public String logout(HttpSession session) {
	        session.invalidate(); // elimina toda la sesión
	        return "redirect:/Home"; // redirige a Home
	    }
	    
	}