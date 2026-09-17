package ney.budgeting.infrastructure.persistence.http.response;

import ney.budgeting.application.output.TransactionOutput;

public record TransactionResponse(
        String id,
        String category,
        String description,
        double amount
) {
    public static TransactionResponse from(TransactionOutput output) {
        return new TransactionResponse(
                output.id(),
                output.category(),
                output.description(),
                output.value()
        );
    }
}
