package liisikinnunen.personalfinance.service;

import liisikinnunen.personalfinance.model.Ledger;

import java.util.Optional;

public interface LedgerService {
    Optional<Ledger> findById(Long id);
}
