package com.example.sacola.repositories;

import com.example.sacola.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByLojaId(Long lojaId);
}
