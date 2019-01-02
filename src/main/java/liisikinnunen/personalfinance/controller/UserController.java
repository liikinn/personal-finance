package liisikinnunen.personalfinance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import liisikinnunen.personalfinance.model.User;
import liisikinnunen.personalfinance.service.UserService;
import liisikinnunen.personalfinance.util.ControllerUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.*;
import org.springframework.data.rest.webmvc.json.DomainObjectReader;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

import static liisikinnunen.personalfinance.util.PathAndRelMappings.PATH_USERS;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@BasePathAwareController
@RequiredArgsConstructor
public class UserController {
    @NonNull
    private final DomainObjectReader domainObjectReader;
    @NonNull
    private final LocalValidatorFactoryBean beanValidator;
    @NonNull
    private final UserService userService;
    @NonNull
    private ControllerUtil controllerUtil;
    @NonNull
    private final ObjectMapper mapper;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(beanValidator);
    }

    @GetMapping(PATH_USERS)
    public ResponseEntity<ResourceSupport> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return controllerUtil.collectionToResponseEntity(users, methodOn(this.getClass()).getAllUsers(), null);
    }
}
