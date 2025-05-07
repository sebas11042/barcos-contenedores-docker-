package com.examen.barcos.controller;

import com.examen.barcos.entity.Barco;
import com.examen.barcos.service.BarcoService;
import com.examen.barcos.service.ContenedorService;
import com.examen.barcos.entity.Contenedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/barcos")
public class BarcoController {

    @Autowired
    private BarcoService barcoService;

    @Autowired
    private ContenedorService contenedorService;

    @GetMapping
    public String listarBarcos(Model model) {
        List<Barco> barcos = barcoService.listarTodos();
        model.addAttribute("barcos", barcos);
        model.addAttribute("barcoNuevo", new Barco());
        return "barcos"; 
    }

    @PostMapping("/guardar")
    public String guardarBarco(@ModelAttribute Barco barco) {
        barcoService.guardar(barco);
        return "redirect:/barcos";
    }

    @GetMapping("/editar/{id}")
    public String editarBarco(@PathVariable Long id, Model model) {
        Barco barco = barcoService.obtenerPorId(id);
        model.addAttribute("barcos", barcoService.listarTodos());
        model.addAttribute("barcoNuevo", barco);
        return "barcos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarBarco(@PathVariable Long id) {
        barcoService.eliminar(id);
        return "redirect:/barcos";
    }

    @GetMapping("/{id}/contenedores")
    public String mostrarContenedores(@PathVariable Long id, Model model) {
        Barco barco = barcoService.obtenerPorId(id);
        List<Contenedor> contenedores = contenedorService.listarPorBarco(id);
        model.addAttribute("barcoSeleccionado", barco);
        model.addAttribute("contenedores", contenedores);
        model.addAttribute("contenedorNuevo", new Contenedor());
        model.addAttribute("barcos", barcoService.listarTodos());
        model.addAttribute("barcoNuevo", new Barco());
        return "barcos";
    }
}
