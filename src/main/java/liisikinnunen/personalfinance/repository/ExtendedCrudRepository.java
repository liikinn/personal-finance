package liisikinnunen.personalfinance.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ExtendedCrudRepository<T, ID extends Serializable> extends CrudRepository<T, ID>, JpaSpecificationExecutor<T> {
    @Override
    @Transactional(readOnly = true)
    List<T> findAll(Specification<T> spec);
}
