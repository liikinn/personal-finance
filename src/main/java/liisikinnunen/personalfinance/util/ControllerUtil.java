package liisikinnunen.personalfinance.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.ControllerUtils;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ControllerUtil {
    @NonNull
    private RepositoryRestConfiguration config;

    public ResponseEntity<ResourceSupport> collectionToResponseEntity(Collection<?> collection, PersistentEntityResourceAssembler assembler) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Resources<?> resources = new Resources<>(toContent(collection, assembler));
        return ControllerUtils.toResponseEntity(HttpStatus.OK, headers, resources);
    }

    private Collection<?> toContent(Collection<?> collection, PersistentEntityResourceAssembler assembler) {
        if (assembler != null && collection != null) {
            return collection.stream().map(assembler::toFullResource).collect(Collectors.toList());
        } else {
            return collection;
        }
    }
}
