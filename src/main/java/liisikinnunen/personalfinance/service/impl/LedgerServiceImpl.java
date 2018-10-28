package liisikinnunen.personalfinance.service.impl;

import liisikinnunen.personalfinance.repository.LedgerRepository;
import liisikinnunen.personalfinance.service.LedgerService;
import org.springframework.stereotype.Service;

@Service("ledgerService")
public class LedgerServiceImpl implements LedgerService {
    private final LedgerRepository ledgerRepository;
}
