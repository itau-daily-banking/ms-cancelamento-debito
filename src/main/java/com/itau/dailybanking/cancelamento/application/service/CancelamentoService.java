package com.itau.dailybanking.cancelamento.application.service;

import com.itau.dailybanking.cancelamento.application.port.in.CancelamentoUseCase;
import com.itau.dailybanking.cancelamento.application.port.out.CancelamentoEventPublisher;
import com.itau.dailybanking.cancelamento.domain.model.Cancelamento;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CancelamentoService implements CancelamentoUseCase {

    private final CancelamentoEventPublisher publisher;

    public CancelamentoService(CancelamentoEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void cancelarDebito(String debitoId) {
        var evento = new Cancelamento(debitoId, Instant.now());
        publisher.publish(evento);
    }
}
