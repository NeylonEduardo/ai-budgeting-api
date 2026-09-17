package ney.budgeting.application.input;

import ney.budgeting.domain.Category;
import org.springframework.ai.tool.annotation.ToolParam;

public record PersistTransactionInput(
        @ToolParam(description = "Spend description") String description,
        @ToolParam(description = "Value spend in cents") long amount,
        @ToolParam(description = "Transaction category") Category category) {
}
