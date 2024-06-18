package com.example.sacola.service;

import com.example.sacola.models.Loja;
import com.example.sacola.models.Produto;
import com.example.sacola.models.Usuario;
import com.example.sacola.repositories.LojaRepository;
import com.example.sacola.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LojaService {
    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Loja> listarLojas() {
        return lojaRepository.findAll();
    }
    public List<Loja> listarLojasComProdutos() {
        return lojaRepository.findAll();
    }
    public Loja editarLoja(Long lojaId, Loja lojaAtualizada) {
        Loja lojaExistente = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new NoSuchElementException("Loja não encontrada"));

        lojaExistente.setNome(lojaAtualizada.getNome());
        // Atualize outros campos conforme necessário

        return lojaRepository.save(lojaExistente);
    }

    @Transactional
    public void deletarLoja(Long lojaId) {
        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new NoSuchElementException("Loja não encontrada"));

        // Excluir todos os produtos da loja
        produtoService.deletarProdutosPorLoja(lojaId);

        // Excluir a loja
        lojaRepository.delete(loja);
    }

}
