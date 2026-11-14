
package br.com.agrofacil.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "plantacoes")
public class Plantacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agricultor_id", nullable = false)
    private Agricultor agricultor;

    @Column(nullable = false, length = 100)
    private String cultura;

    @Column(name = "area_hectares", nullable = false, precision = 10, scale = 2)
    private BigDecimal areaHectares;

    @Column(name = "data_plantio", nullable = false)
    private LocalDate dataPlantio;

    public Plantacao() {
    }

    public Long getId() {
        return id;
    }

    public Agricultor getAgricultor() {
        return agricultor;
    }

    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
    }

    public String getCultura() {
        return cultura;
    }

    public void setCultura(String cultura) {
        this.cultura = cultura;
    }

    public BigDecimal getAreaHectares() {
        return areaHectares;
    }

    public void setAreaHectares(BigDecimal areaHectares) {
        this.areaHectares = areaHectares;
    }

    public LocalDate getDataPlantio() {
        return dataPlantio;
    }

    public void setDataPlantio(LocalDate dataPlantio) {
        this.dataPlantio = dataPlantio;
    }
}
