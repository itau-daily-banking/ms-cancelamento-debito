package com.itau.dailybanking.cancelamento.adapter.in.rest;

import com.itau.dailybanking.cancelamento.application.port.in.CancelamentoUseCase;
import com.itau.dailybanking.cancelamento.adapter.in.rest.dto.CancelamentoRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cancelamentos")
@Validated
public class CancelamentoController {

    private final CancelamentoUseCase service;

    public CancelamentoController(CancelamentoUseCase service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> cancelar(@RequestBody @Valid CancelamentoRequest request) {
        service.cancelarDebito(request.debitoId());
        return ResponseEntity.ok().body("ok");
    }
}
