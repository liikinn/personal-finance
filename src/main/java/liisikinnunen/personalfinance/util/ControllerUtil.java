package liisikinnunen.personalfinance.util;

import liisikinnunen.personalfinance.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.ControllerUtils;
import org.springframework.data.rest.webmvc.HttpHeadersPreparer;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.core.DummyInvocationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
@RequiredArgsConstructor
public class ControllerUtil {
    @NonNull
    private RepositoryRestConfiguration config;
    @NonNull
    private final HttpHeadersPreparer headersPreparer;

    public ResponseEntity<ResourceSupport> returnResponseEntity(PersistentEntityResourceAssembler resourceAssembler,
                                                                Object source, HttpStatus httpStatus, boolean addLocation) {
        PersistentEntityResource resource = resourceAssembler.toFullResource(source);
        HttpHeaders headers = headersPreparer.prepareHeaders(Optional.of(resource));
        if (addLocation) {
            addLocationHeader(headers, resourceAssembler, source);
        }

        switch (httpStatus) {
            case NO_CONTENT:
                return ControllerUtils.toEmptyResponse(httpStatus, headers);
            case CREATED:
                return ControllerUtils.toResponseEntity(httpStatus, headers, resource);
            default:
                return ControllerUtils.toResponseEntity(HttpStatus.OK, headers, resource);
        }
    }

    private void addLocationHeader(HttpHeaders headers, PersistentEntityResourceAssembler assembler, Object source) {
        String selfLink = assembler.getSelfLinkFor(source).getHref();
        headers.setLocation(new UriTemplate(selfLink).expand());
    }

    public ResponseEntity<ResourceSupport> collectionToResponseEntity(Collection<?> collection, Object selfLinkTarget, PersistentEntityResourceAssembler assembler) {
        Link selfLink = toBasePathAwareLink(selfLinkTarget).withSelfRel();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Resources<?> resources = new Resources<>(toContent(collection, assembler), selfLink);
        return ControllerUtils.toResponseEntity(HttpStatus.OK, headers, resources);
    }

    private Link toBasePathAwareLink(Object selfLinkTarget) {
        Assert.isInstanceOf(DummyInvocationUtils.LastInvocationAware.class, selfLinkTarget);
        return fixLinkTo((DummyInvocationUtils.LastInvocationAware) selfLinkTarget);
    }

    @SneakyThrows
    private Link fixLinkTo(DummyInvocationUtils.LastInvocationAware invocationValue) {
        UriComponentsBuilder uriComponentsBuilder = linkTo(invocationValue).toUriComponentsBuilder();
        URL url = new URL(uriComponentsBuilder.toUriString());
        uriComponentsBuilder.replacePath(config.getBasePath() + url.getPath());
        return new Link(uriComponentsBuilder.toUriString());
    }

    private Collection<?> toContent(Collection<?> collection, PersistentEntityResourceAssembler assembler) {
        if (assembler != null && collection != null) {
            return collection.stream().map(assembler::toFullResource).collect(Collectors.toList());
        } else {
            return collection;
        }
    }
}
