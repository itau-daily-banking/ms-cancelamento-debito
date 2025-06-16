package com.itau.dailybanking.cancelamento.application.port.out;

import com.itau.dailybanking.cancelamento.domain.model.Cancelamento;

public interface CancelamentoEventPublisher {
    void publish(Cancelamento evento);
}
