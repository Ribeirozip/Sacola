package com.example.sacola.service;

import com.example.sacola.models.Loja;
import com.example.sacola.models.Produto;
import com.example.sacola.repositories.LojaRepository;
import com.example.sacola.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    public Produto adicionarProduto(Long lojaId, Produto produto) {
        try {
            Loja loja = lojaRepository.findById(lojaId).orElseThrow(() -> new NoSuchElementException("Loja não encontrada"));
            produto.setLoja(loja);
            return produtoRepository.save(produto);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Loja não encontrada");
        }
    }
    public List<Produto> listarProdutos(Long lojaId) {
        try {
            System.out.println("Iniciando listagem de produtos para a loja com ID: " + lojaId);
            List<Produto> produtos = produtoRepository.findByLojaId(lojaId);
            System.out.println("Produtos encontrados: " + produtos);
            return produtos;
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
            throw e; // ou faça outro tratamento adequado aqui
        }
    }
    public List<Produto> listarProdutosPorLoja(Long lojaId) {
        return produtoRepository.findByLojaId(lojaId);
    }
    public Produto criarProduto(Long lojaId, Produto produto) {
        Loja loja = lojaRepository.findById(lojaId).orElseThrow();
        produto.setLoja(loja);
        return produtoRepository.save(produto);
    }
    public void deletarProduto(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }
    public Produto atualizarProduto(Long produtoId, Produto produtoAtualizado) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        return produtoRepository.save(produto);
    }
    public void deletarProdutosPorLoja(Long lojaId) {
        List<Produto> produtos = produtoRepository.findByLojaId(lojaId);
        produtoRepository.deleteAll(produtos);
    }

}