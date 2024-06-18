package com.example.sacola.controllers;

import com.example.sacola.models.Produto;
import com.example.sacola.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081"})
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/{lojaId}/adicionar")
    public ResponseEntity<?> adicionarProduto(@PathVariable Long lojaId, @RequestBody Produto produto) {
        try {
            Produto novoProduto = produtoService.adicionarProduto(lojaId, produto);
            return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao adicionar produto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{lojaId}/listar")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Long lojaId) {
        try {
            List<Produto> produtos = produtoService.listarProdutos(lojaId);
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{produtoId}/deletar")
    public ResponseEntity<?> deletarProduto(@PathVariable Long produtoId) {
        try {
            produtoService.deletarProduto(produtoId);
            return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar produto: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{produtoId}/atualizar")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long produtoId, @RequestBody Produto produto) {
        try {
            Produto produtoAtualizado = produtoService.atualizarProduto(produtoId, produto);
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar produto: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}


