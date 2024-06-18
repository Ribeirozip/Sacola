package com.example.sacola.repositories;

import com.example.sacola.models.Loja;
import com.example.sacola.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LojaRepository extends JpaRepository<Loja, Long> {
    @Query("SELECT l FROM Loja l WHERE l.usuario.id = :userId")
    List<Loja> findLojasByUserId(@Param("userId") Long userId);
    void deleteByUsuario(Usuario usuario);
}