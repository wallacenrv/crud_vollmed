package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

   Page<Medico> findAllByAtivoTrue(Pageable paginacao);

  // Optional<Object> findAllById(Pageable paginacao);
}

// tipo da entidade e tipo do atributo da chave primaria
// com isso nao precisa do entity manager
// nem classes DAO
// repository é o avanco