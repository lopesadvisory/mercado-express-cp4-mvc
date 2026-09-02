package com.fiap.mercadoexpressmvc.service;

import com.fiap.mercadoexpressmvc.exception.ProdutoNaoEncontradoException;
import com.fiap.mercadoexpressmvc.model.Produto;
import com.fiap.mercadoexpressmvc.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto criar(Produto produto) {
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto dados) {
        Produto produtoExistente = buscarPorId(id);

        produtoExistente.setNome(dados.getNome());
        produtoExistente.setTipo(dados.getTipo());
        produtoExistente.setSetor(dados.getSetor());
        produtoExistente.setTamanho(dados.getTamanho());
        produtoExistente.setPreco(dados.getPreco());

        return produtoRepository.save(produtoExistente);
    }

    public void deletar(Long id) {
        Produto produtoExistente = buscarPorId(id);
        produtoRepository.delete(produtoExistente);
    }

}
