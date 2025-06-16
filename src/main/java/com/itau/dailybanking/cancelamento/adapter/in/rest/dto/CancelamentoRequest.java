package com.itau.dailybanking.cancelamento.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record CancelamentoRequest(
        @NotBlank(message = "campo debitoId é obrigatório")
        String debitoId
) {
}
