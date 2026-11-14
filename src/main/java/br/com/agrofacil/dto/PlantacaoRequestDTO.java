
package br.com.agrofacil.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PlantacaoRequestDTO(
        Long agricultorId,
        String cultura,
        BigDecimal areaHectares,
        LocalDate dataPlantio) {
}
