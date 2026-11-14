
package br.com.agrofacil.repository;

import br.com.agrofacil.model.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgricultorRepository
        extends JpaRepository<Agricultor, Long> {
}
