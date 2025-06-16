package com.itau.dailybanking.cancelamento.domain.model;

import java.time.Instant;

public record Cancelamento(
        String debitoId,
        Instant timestamp
) {
}
