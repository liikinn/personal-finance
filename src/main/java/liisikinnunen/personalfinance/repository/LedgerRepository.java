package liisikinnunen.personalfinance.repository;

import liisikinnunen.personalfinance.model.Ledger;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import static liisikinnunen.personalfinance.util.PathAndRelMappings.*;

@RepositoryRestResource(
        path = PATH_LEDGERS,
        itemResourceRel = REL_LEDGER_ITEM_RESOURCE,
        collectionResourceRel = REL_LEDGER_COLLECTION_RESOURCE)
public interface LedgerRepository extends ExtendedCrudRepository<Ledger, Long> {
}