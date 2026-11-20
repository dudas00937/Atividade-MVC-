package biblioteca.com.biblioteca.controller;

import biblioteca.com.biblioteca.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final List<Usuario> usuarios = new ArrayList<>();
    private Long proximoId = 1L;

    public UsuarioController() {
        Usuario usuario = new Usuario();
        usuario.setNome("Duda");
        usuario.setIdade(19);
        usuario.setCidade("Londrina");
        usuario.setId(proximoId++);
        usuarios.add(usuario);
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarios);
        return "usuarios/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = buscarPorId(id);
        if (usuario == null) {
            return "redirect:/usuarios";
        }
        model.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId(proximoId++);
            usuarios.add(usuario);
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getId().equals(usuario.getId())) {
                    usuarios.set(i, usuario);
                    break;
                }
            }
        }
        return "redirect:/usuarios";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        usuarios.removeIf(item -> item.getId().equals(id));
        return "redirect:/usuarios";
    }

    private Usuario buscarPorId(Long id) {
        for (Usuario item : usuarios) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}
