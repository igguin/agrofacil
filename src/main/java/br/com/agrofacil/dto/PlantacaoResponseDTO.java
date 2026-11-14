
package br.com.agrofacil.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PlantacaoResponseDTO(
        Long id,
        Long agricultorId,
        String cultura,
        BigDecimal areaHectares,
        LocalDate dataPlantio) {
}
