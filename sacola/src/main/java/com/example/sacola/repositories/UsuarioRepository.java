package com.example.sacola.repositories;

import com.example.sacola.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   //voltar e refazer o login
    Usuario findByEmail(String email);

}
