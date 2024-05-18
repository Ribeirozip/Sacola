package com.example.sacola.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
@Setter
@Data
@Table(name = "lojas")
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "loja")
    private List<Produto> produtos;

}
