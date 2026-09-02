package com.fiap.mercadoexpressmvc.repository;

import com.fiap.mercadoexpressmvc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
