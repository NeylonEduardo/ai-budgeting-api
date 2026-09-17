package ney.budgeting.infrastructure.persistence.http.request;

import ney.budgeting.application.input.PersistTransactionInput;
import ney.budgeting.domain.Category;

public record TransactionRequest(
        String description,
        Category category,
        long amount) {

    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description(), amount(), category());
    }
}
