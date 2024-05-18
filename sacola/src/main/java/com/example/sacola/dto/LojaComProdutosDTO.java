package com.example.sacola.dto;

import com.example.sacola.models.Loja;
import com.example.sacola.models.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class LojaComProdutosDTO {
    private Long id;
    private String nome;
    private List<Produto> produtos;

    public LojaComProdutosDTO(Loja loja) {
        this.id = loja.getId();
        this.nome = loja.getNome();
        this.produtos = loja.getProdutos();
    }
}
