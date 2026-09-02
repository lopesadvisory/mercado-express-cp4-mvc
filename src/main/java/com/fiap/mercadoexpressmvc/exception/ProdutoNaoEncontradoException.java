package com.fiap.mercadoexpressmvc.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(Long id) {
        super("Produto não encontrado para o id: " + id);
    }

}
