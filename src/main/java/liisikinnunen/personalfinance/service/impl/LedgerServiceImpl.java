package liisikinnunen.personalfinance.service.impl;

import liisikinnunen.personalfinance.model.Ledger;
import liisikinnunen.personalfinance.repository.LedgerRepository;
import liisikinnunen.personalfinance.service.LedgerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("ledgerService")
@RequiredArgsConstructor
public class LedgerServiceImpl implements LedgerService {
    @NonNull
    private final LedgerRepository ledgerRepository;

    @Override
    public Optional<Ledger> findById(Long id) {
        return ledgerRepository.findById(id);
    }
}
