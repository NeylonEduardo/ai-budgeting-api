package ney.budgeting.application;

import ney.budgeting.application.input.PersistTransactionInput;
import ney.budgeting.application.output.TransactionOutput;
import ney.budgeting.domain.Transaction;
import ney.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service

public class PersistTransactionUseCase {
    private final TransactionRepository repository;

    public PersistTransactionUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    @Tool(name = "persist-transaction", description = "Persist a new financial transaction")
    public TransactionOutput execute(PersistTransactionInput input) {
        var transaction = repository.save(new Transaction(input.description(), input.amount(), input.category()));

        return TransactionOutput.from(transaction);
    }
}