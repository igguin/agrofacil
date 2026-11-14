
package br.com.agrofacil.validation;

import br.com.agrofacil.dto.PlantacaoRequestDTO;
import java.math.BigDecimal;

public class PlantacaoValidator {

    public static void validar(PlantacaoRequestDTO dados) {

        if (dados.cultura() == null || dados.cultura().isBlank()) {
            throw new IllegalArgumentException(
                    "A cultura é obrigatória.");
        }

        if (dados.areaHectares() == null ||
                dados.areaHectares().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "A área deve ser maior que zero.");
        }

        if (dados.dataPlantio() == null) {
            throw new IllegalArgumentException(
                    "A data do plantio é obrigatória.");
        }

        if (dados.agricultorId() == null) {
            throw new IllegalArgumentException(
                    "O agricultor é obrigatório.");
        }
    }
}
