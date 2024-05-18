package com.example.sacola.repositories;

import com.example.sacola.models.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
