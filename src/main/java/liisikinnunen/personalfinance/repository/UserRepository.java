package liisikinnunen.personalfinance.repository;

import liisikinnunen.personalfinance.model.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import static liisikinnunen.personalfinance.util.PathAndRelMappings.PATH_USERS;
import static liisikinnunen.personalfinance.util.PathAndRelMappings.REL_USER_COLLECTION_RESOURCE;
import static liisikinnunen.personalfinance.util.PathAndRelMappings.REL_USER_ITEM_RESOURCE;

@RepositoryRestResource(
        path = PATH_USERS,
        itemResourceRel = REL_USER_ITEM_RESOURCE,
        collectionResourceRel = REL_USER_COLLECTION_RESOURCE)
public interface UserRepository extends ExtendedCrudRepository<User, Long> {
}
