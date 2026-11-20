package biblioteca.com.biblioteca.controller;

import biblioteca.com.biblioteca.model.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final List<Autor> autores = new ArrayList<>();

    public AutorController() {
        Autor autor = new Autor();
        autor.setNome("Machado de Assis");
        autor.setNacionalidade("Brasileira");
        autor.setObrasPublicadas("Dom Casmurro, Memórias Póstumas de Brás Cubas");
        autor.setId(proximoId++);
        autores.add(autor);
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autores", autores);
        return "autores/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Autor autor = buscarPorId(id);
        if (autor == null) {
            return "redirect:/autores";
        }
        model.addAttribute("autor", autor);
        return "autores/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Autor autor) {
        if (autor.getId() == null) {
            autor.setId(proximoId++);
            autores.add(autor);
        } else {
            for (int i = 0; i < autores.size(); i++) {
                if (autores.get(i).getId().equals(autor.getId())) {
                    autores.set(i, autor);
                    break;
                }
            }
        }
        return "redirect:/autores";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        autores.removeIf(item -> item.getId().equals(id));
        return "redirect:/autores";
    }

    private Autor buscarPorId(Long id) {
        for (Autor item : autores) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}
