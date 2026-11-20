package biblioteca.com.biblioteca.controller;

import biblioteca.com.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final List<Emprestimo> emprestimos = new ArrayList<>();
    private Long proximoId = 1L;

    public EmprestimoController() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro("Dom Casmurro");
        emprestimo.setUsuario("Duda");
        emprestimo.setDataRetirada(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(7));
        emprestimo.setId(proximoId++);
        emprestimos.add(emprestimo);
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimos);
        return "emprestimos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        return "emprestimos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Emprestimo emprestimo = buscarPorId(id);
        if (emprestimo == null) {
            return "redirect:/emprestimos";
        }
        model.addAttribute("emprestimo", emprestimo);
        return "emprestimos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Emprestimo emprestimo) {
        if (emprestimo.getId() == null) {
            emprestimo.setId(proximoId++);
            emprestimos.add(emprestimo);
        } else {
            for (int i = 0; i < emprestimos.size(); i++) {
                if (emprestimos.get(i).getId().equals(emprestimo.getId())) {
                    emprestimos.set(i, emprestimo);
                    break;
                }
            }
        }
        return "redirect:/emprestimos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        emprestimos.removeIf(item -> item.getId().equals(id));
        return "redirect:/emprestimos";
    }

    private Emprestimo buscarPorId(Long id) {
        for (Emprestimo item : emprestimos) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}
