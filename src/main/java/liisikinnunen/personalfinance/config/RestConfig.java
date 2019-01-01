package liisikinnunen.personalfinance.config;

import liisikinnunen.personalfinance.model.Ledger;
import liisikinnunen.personalfinance.model.User;
import liisikinnunen.personalfinance.repository.impl.ExtendedCrudRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.json.DomainObjectReader;
import org.springframework.data.rest.webmvc.mapping.Associations;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = "liisikinnunen.personalfinance.repository",
        repositoryBaseClass = ExtendedCrudRepositoryImpl.class)
public class RestConfig extends RepositoryRestConfigurerAdapter {
    @Bean
    @Primary
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("/");
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Ledger.class);
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
        config.setReturnBodyOnCreate(true);
    }

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        Validator validator = validator();
        validatingListener.addValidator("beforeCreate", validator);
        validatingListener.addValidator("beforeSave", validator);
    }

    @Bean
    public DomainObjectReader domainObjectReader(final PersistentEntities persistentEntities, final Associations associationLinks) {
        return new DomainObjectReader(persistentEntities, associationLinks);
    }
}
