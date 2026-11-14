
package br.com.agrofacil.repository;

import br.com.agrofacil.model.Colheita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColheitaRepository
        extends JpaRepository<Colheita, Long> {
}
