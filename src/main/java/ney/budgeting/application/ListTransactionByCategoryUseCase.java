package ney.budgeting.application;

import ney.budgeting.application.output.TransactionOutput;
import ney.budgeting.domain.Category;
import ney.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTransactionByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public ListTransactionByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "list-transactions-by-categoty", description = "Persist a new financial transaction by category")
    public List<TransactionOutput> execute(@ToolParam(description = "Transaction category") Category category) {
        return transactionRepository.findAllByCategory(category)
                .stream()
                .map(TransactionOutput::from)
                .toList();
    }
}
