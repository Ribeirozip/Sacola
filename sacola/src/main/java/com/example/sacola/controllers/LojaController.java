package com.example.sacola.controllers;

import com.example.sacola.dto.LojaComProdutosDTO;
import com.example.sacola.models.Loja;
import com.example.sacola.models.Produto;
import com.example.sacola.models.Usuario;
import com.example.sacola.service.LojaService;
import com.example.sacola.service.ProdutoService;
import com.example.sacola.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lojas")
public class LojaController {
    @Autowired
    private LojaService lojaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProdutoService produtoService;

    //listar lojas
    @GetMapping("/listar")
    public List<Loja> listarLojas() {
        return lojaService.listarLojas();
    }

    //criar produtos
    @PostMapping("/{lojaId}/produtos")
    public ResponseEntity<?> criarProduto(@PathVariable Long lojaId, @RequestBody Produto produto) {
        try {
            Produto novoProduto = produtoService.criarProduto(lojaId, produto);
            return ResponseEntity.ok(novoProduto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar produto: " + e.getMessage());
        }
    }
    @GetMapping("/com-produtos")
    public ResponseEntity<List<LojaComProdutosDTO>> listarLojasComProdutos() {
        List<Loja> lojas = lojaService.listarLojasComProdutos();
        List<LojaComProdutosDTO> lojasComProdutosDTOs = lojas.stream()
                .map(LojaComProdutosDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lojasComProdutosDTOs);
    }
    // Editar loja
    @PutMapping("/{lojaId}")
    public ResponseEntity<?> editarLoja(@PathVariable Long lojaId, @RequestBody Loja lojaAtualizada) {
        try {
            Loja lojaEditada = lojaService.editarLoja(lojaId, lojaAtualizada);
            return ResponseEntity.ok(lojaEditada);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Loja não encontrada.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao editar loja: " + e.getMessage());
        }
    }

    // Deletar loja end point nao ta funcionando
    @DeleteMapping("/{lojaId}")
    public ResponseEntity<?> deletarLoja(@PathVariable Long lojaId) {
        try {
            lojaService.deletarLoja(lojaId);
            return ResponseEntity.ok("Loja deletada com sucesso.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Loja não encontrada.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar loja: " + e.getMessage());
        }
    }
}
