package com.examen.barcos.controller;

import com.examen.barcos.entity.Contenedor;
import com.examen.barcos.entity.Barco;
import com.examen.barcos.service.ContenedorService;
import com.examen.barcos.service.BarcoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contenedores")
public class ContenedorController {

    @Autowired
    private ContenedorService contenedorService;

    @Autowired
    private BarcoService barcoService;

    // Guardar contenedor con validación de capacidad
    @PostMapping("/guardar")
public String guardarContenedor(@ModelAttribute Contenedor contenedor, @RequestParam Long barcoId, Model model) {
    Barco barco = barcoService.obtenerPorId(barcoId);
    contenedor.setBarco(barco);  // <-- SIEMPRE asignar el barco

    List<Contenedor> existentes = contenedorService.listarPorBarco(barcoId);

    boolean esNuevo = (contenedor.getId() == null);

    if (esNuevo && existentes.size() >= barco.getCapacidad()) {
        model.addAttribute("errorCapacidad", "⚠️ El barco ya alcanzó su capacidad máxima (" + barco.getCapacidad() + ")");
        model.addAttribute("barcoSeleccionado", barco);
        model.addAttribute("contenedores", existentes);
        model.addAttribute("contenedorNuevo", new Contenedor());
        model.addAttribute("barcos", barcoService.listarTodos());
        model.addAttribute("barcoNuevo", new Barco());
        return "barcos";
    }

    contenedorService.guardar(contenedor);  // ya sea nuevo o actualizado
    return "redirect:/barcos/" + barcoId + "/contenedores";
}

    

    // Eliminar contenedor
    @GetMapping("/eliminar/{id}")
    public String eliminarContenedor(@PathVariable Long id) {
        Contenedor contenedor = contenedorService.obtenerPorId(id);
        Long barcoId = contenedor.getBarco().getId();
        contenedorService.eliminar(id);
        return "redirect:/barcos/" + barcoId + "/contenedores";
    }

    // Editar contenedor (muestra los datos en el formulario)
    @GetMapping("/editar/{id}")
    public String editarContenedor(@PathVariable Long id, Model model) {
        Contenedor contenedor = contenedorService.obtenerPorId(id);
        Barco barco = contenedor.getBarco();

        model.addAttribute("contenedorNuevo", contenedor); // carga el contenedor a editar
        model.addAttribute("barcoSeleccionado", barco);
        model.addAttribute("barcos", barcoService.listarTodos());
        model.addAttribute("barcoNuevo", new Barco());
        model.addAttribute("contenedores", contenedorService.listarPorBarco(barco.getId()));
        return "barcos";
    }
}
