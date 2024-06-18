package com.example.sacola.service;

import com.example.sacola.models.Loja;
import com.example.sacola.models.Usuario;
import com.example.sacola.repositories.LojaRepository;
import com.example.sacola.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LojaRepository lojaRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public Loja criarLoja(Loja loja, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        loja.setUsuario(usuario);
        return lojaRepository.save(loja);
    }
    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }
    public Usuario buscarUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
    }
    public void deletarUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        // Remover todas as lojas associadas a este usuário
        lojaRepository.deleteByUsuario(usuario);
        // Deletar o usuário
        usuarioRepository.delete(usuario);
    }
    public Usuario editarUsuario(Long usuarioId, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        // Atualize outros campos conforme necessário

        return usuarioRepository.save(usuarioExistente);
    }
    @Transactional
    public void deletarLoja(Long lojaId) {
        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new NoSuchElementException("Loja não encontrada"));

        lojaRepository.delete(loja);
    }
}