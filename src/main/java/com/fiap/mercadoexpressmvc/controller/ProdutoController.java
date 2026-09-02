package com.fiap.mercadoexpressmvc.controller;

import com.fiap.mercadoexpressmvc.model.Produto;
import com.fiap.mercadoexpressmvc.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produtos/list";
    }

    @GetMapping("/{id}")
    public String detalhar(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoService.buscarPorId(id));
        return "produtos/detalhe";
    }

    @GetMapping("/novo")
    public String novoFormulario(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("modoEdicao", false);
        return "produtos/form";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("produto") Produto produto,
                         BindingResult resultado,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (resultado.hasErrors()) {
            model.addAttribute("modoEdicao", false);
            return "produtos/form";
        }
        produtoService.criar(produto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso.");
        return "redirect:/produtos";
    }

    @GetMapping("/{id}/editar")
    public String editarFormulario(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoService.buscarPorId(id));
        model.addAttribute("modoEdicao", true);
        return "produtos/form";
    }

    @PostMapping("/{id}/editar")
    public String atualizar(@PathVariable Long id,
                             @Valid @ModelAttribute("produto") Produto produto,
                             BindingResult resultado,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (resultado.hasErrors()) {
            model.addAttribute("modoEdicao", true);
            return "produtos/form";
        }
        produtoService.atualizar(id, produto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto atualizado com sucesso.");
        return "redirect:/produtos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        produtoService.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", "Produto removido com sucesso.");
        return "redirect:/produtos";
    }

}
