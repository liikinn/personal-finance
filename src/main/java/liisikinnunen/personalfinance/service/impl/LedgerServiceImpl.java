package liisikinnunen.personalfinance.service.impl;

import liisikinnunen.personalfinance.repository.LedgerRepository;
import liisikinnunen.personalfinance.service.LedgerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("ledgerService")
@RequiredArgsConstructor
public class LedgerServiceImpl implements LedgerService {
    @NonNull
    private final LedgerRepository ledgerRepository;
}
