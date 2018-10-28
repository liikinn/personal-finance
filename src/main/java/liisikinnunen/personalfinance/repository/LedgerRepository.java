package liisikinnunen.personalfinance.repository;

import liisikinnunen.personalfinance.model.Ledger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import static liisikinnunen.personalfinance.util.PathAndRelMappings.PATH_LEDGERS;

@RepositoryRestResource(
        path = PATH_LEDGERS
)
public interface LedgerRepository extends CrudRepository<Ledger, Long> {
}