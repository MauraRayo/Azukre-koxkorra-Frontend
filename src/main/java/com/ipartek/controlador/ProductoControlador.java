package com.ipartek.controlador;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ipartek.modelo.Producto;
import com.ipartek.servicios.ProductoServicio;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductoControlador {
	@Value("${ruta.productos.img}")
	private String rutaImagenes;

	@Autowired
	private ProductoServicio productoServ;

	@GetMapping("/InsertarProducto")
	public String mostrarInsertarProducto(HttpSession session) {
		String rol = (String) session.getAttribute("rol");
		if (!"ADMIN".equals(rol)) {
			return "redirect:/Home";
		}
		return "redirect:/MenuProductosAdmin";
	}

	@PostMapping("/InsertarProducto")
	public String insertarProducto(@ModelAttribute Producto obj_producto, @RequestParam("foto2") MultipartFile foto2) {

		String rutaDestino = rutaImagenes;
		String nombreFoto = "0.png";

		// Si el archivo no está vacío, lo guardamos
		if (!foto2.isEmpty()) {
			try {
				nombreFoto = foto2.getOriginalFilename();
				File destino = new File(rutaDestino, nombreFoto);

				foto2.transferTo(destino); // guarda la imagen físicamente

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		obj_producto.setImg(nombreFoto);
		productoServ.insertarProducto(obj_producto);

		return "redirect:/MenuProductosAdmin";
	}

	@GetMapping("/BorrarProducto")
	public String borrarProducto(@RequestParam Integer id) {
		productoServ.borrarProducto(id);
		return "redirect:/MenuProductosAdmin";
	}

	@GetMapping("/ModificarProducto")
	public String ModificarProducto(Model model, @RequestParam Integer id, @ModelAttribute Producto obj_producto) {
		Producto p = productoServ.obtenerProductoPorId(id);
		model.addAttribute("obj_producto", p);
		return "frmProductoModificar";
	}

	@PostMapping("/FrmModificarProducto")
	public String frmModificarPrenda(@ModelAttribute Producto obj_producto) {
		productoServ.modificarProducto(obj_producto);
		return "redirect:/MenuProductosAdmin";
	}

	public Producto obtenerProductoPorId(Integer id) {
		if (id == null) {
	        return null; // o new Producto() si prefieres devolver un objeto vacío
	    }
	    return productoServ.obtenerProductoPorId(id);
	}

	
}
