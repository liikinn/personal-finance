package liisikinnunen.personalfinance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import liisikinnunen.personalfinance.model.Ledger;
import liisikinnunen.personalfinance.service.LedgerService;
import liisikinnunen.personalfinance.util.ControllerUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.json.DomainObjectReader;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.NoSuchElementException;

import static liisikinnunen.personalfinance.util.PathAndRelMappings.PATH_LEDGERS;

@BasePathAwareController
@RequiredArgsConstructor
public class LedgerController {
    @NonNull
    private final DomainObjectReader domainObjectReader;
    @NonNull
    private final LocalValidatorFactoryBean beanValidator;
    @NonNull
    private final LedgerService ledgerService;
    @NonNull
    private ControllerUtil controllerUtil;
    @NonNull
    private final ObjectMapper mapper;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(beanValidator);
    }

    @GetMapping(PATH_LEDGERS + "/{id}")
    public ResponseEntity<ResourceSupport> getLedger(@PathVariable() Long id, PersistentEntityResourceAssembler resourceAssembler) {
        Ledger ledger = ledgerService.findById(id).orElseThrow(NoSuchElementException::new);
        return controllerUtil.returnResponseEntity(resourceAssembler, ledger, HttpStatus.OK, true);
    }
}
