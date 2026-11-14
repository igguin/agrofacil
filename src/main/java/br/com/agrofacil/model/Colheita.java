
package br.com.agrofacil.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "colheitas")
public class Colheita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plantacao_id", nullable = false)
    private Plantacao plantacao;

    @Column(name = "quantidade_kg", nullable = false, precision = 12, scale = 2)
    private BigDecimal quantidadeKg;

    @Column(name = "data_colheita", nullable = false)
    private LocalDate dataColheita;

    public Colheita() {
    }

    public Long getId() {
        return id;
    }

    public Plantacao getPlantacao() {
        return plantacao;
    }

    public void setPlantacao(Plantacao plantacao) {
        this.plantacao = plantacao;
    }

    public BigDecimal getQuantidadeKg() {
        return quantidadeKg;
    }

    public void setQuantidadeKg(BigDecimal quantidadeKg) {
        this.quantidadeKg = quantidadeKg;
    }

    public LocalDate getDataColheita() {
        return dataColheita;
    }

    public void setDataColheita(LocalDate dataColheita) {
        this.dataColheita = dataColheita;
    }
}
