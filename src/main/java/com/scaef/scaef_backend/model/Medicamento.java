package com.scaef.scaef_backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/*Projeto Lombok*/
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@Table(name="medicamento")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Medicamento")
    private Long id;
 
    @Column(name = "Nome", nullable = false, unique = true)
    @NotBlank(message = "O preenchimento deste campo é obrigatório")
    private String nome;
 
    @Column(name = "CID", nullable = false)
    @NotBlank(message = "O preenchimento deste campo é obrigatório")
    private String cid;

    @Column(name = "Categoria_Regulatória", nullable = false)
    @NotBlank(message = "O preenchimento deste campo é obrigatório")
    private String catReg;

    @Column(name = "PCDT", nullable = false)
    @NotBlank(message = "O preenchimento deste campo é obrigatório")
    private String pcdt;

    @Column(name = "Dosagem", nullable = false)
    @NotBlank(message = "O preenchimento deste campo é obrigatório")
    private String dose;

    @Column(name = "Forma_Farmacêutica", nullable = false)
    @NotBlank(message = "O preenchimento deste campo é obrigatório")
    private String forma;

    @Column(name = "Via_de_Administração", nullable = false)
    @NotBlank(message = "O preenchimento deste campo é obrigatório")
    private String via;

    @Column(name = "Princípio_Ativo", nullable = false)
    @NotBlank(message = "O preenchimento deste campo é obrigatório")
    private String priA;

    @Column(name = "Classe_Terapêutica", nullable = false)
    @NotBlank(message = "O preenchimento deste campo é obrigatório")
    private String classeT;
}