package biblioteca.com.biblioteca.controller;

import biblioteca.com.biblioteca.model.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final List<Categoria> categorias = new ArrayList<>();
    private Long proximoId = 1L;

    public CategoriaController() {
        Categoria categoria = new Categoria();
        categoria.setGenero("Romance");
        categoria.setId(proximoId++);
        categorias.add(categoria);
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categorias);
        return "categorias/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Categoria categoria = buscarPorId(id);
        if (categoria == null) {
            return "redirect:/categorias";
        }
        model.addAttribute("categoria", categoria);
        return "categorias/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(proximoId++);
            categorias.add(categoria);
        } else {
            for (int i = 0; i < categorias.size(); i++) {
                if (categorias.get(i).getId().equals(categoria.getId())) {
                    categorias.set(i, categoria);
                    break;
                }
            }
        }
        return "redirect:/categorias";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        categorias.removeIf(item -> item.getId().equals(id));
        return "redirect:/categorias";
    }

    private Categoria buscarPorId(Long id) {
        for (Categoria item : categorias) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}
