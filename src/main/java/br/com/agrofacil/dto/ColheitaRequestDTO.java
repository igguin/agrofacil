
package br.com.agrofacil.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ColheitaRequestDTO(
        Long plantacaoId,
        BigDecimal quantidadeKg,
        LocalDate dataColheita) {
}
