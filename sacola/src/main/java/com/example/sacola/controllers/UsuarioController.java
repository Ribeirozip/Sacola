package com.example.sacola.controllers;

import com.example.sacola.models.Loja;
import com.example.sacola.models.Usuario;
import com.example.sacola.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.registrarUsuario(usuario);
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao registrar usuário: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Usuario usuarioLogado = usuarioService.login(usuario.getEmail(), usuario.getSenha());
        if (usuarioLogado != null) {
            return new ResponseEntity<>(usuarioLogado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuário não encontrado ou senha incorreta", HttpStatus.UNAUTHORIZED);
        }
    }
    //criacao de lojas
    @PostMapping("/{usuarioId}/lojas")
    public Loja criarLoja(@PathVariable Long usuarioId, @RequestBody Loja loja) {
        return usuarioService.criarLoja(loja, usuarioId);
    }
    //delete user
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long usuarioId) {
        try {
            usuarioService.deletarUsuario(usuarioId);
            return new ResponseEntity<>("Usuário deletado com sucesso.", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar usuário: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // Editar usuário
    @PutMapping("/{usuarioId}")
    public ResponseEntity<?> editarUsuario(@PathVariable Long usuarioId, @RequestBody Usuario usuarioAtualizado) {
        try {
            Usuario usuarioEditado = usuarioService.editarUsuario(usuarioId, usuarioAtualizado);
            return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao editar usuário: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
