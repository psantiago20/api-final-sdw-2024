package me.dio.domain.model;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount,
                             Long payerId,
                             Long payeeId
) {
}
