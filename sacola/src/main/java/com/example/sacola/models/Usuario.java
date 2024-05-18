package com.example.sacola.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Data
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Loja loja;


}
