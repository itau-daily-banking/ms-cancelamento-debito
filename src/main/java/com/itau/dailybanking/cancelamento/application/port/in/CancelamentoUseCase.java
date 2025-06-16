package com.itau.dailybanking.cancelamento.application.port.in;

public interface CancelamentoUseCase {
    void cancelarDebito(String debitoId);
}
