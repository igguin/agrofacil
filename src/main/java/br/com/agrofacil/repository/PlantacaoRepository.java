
package br.com.agrofacil.repository;

import br.com.agrofacil.model.Plantacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantacaoRepository
        extends JpaRepository<Plantacao, Long> {

    List<Plantacao> findByAgricultorId(Long agricultorId);
}
