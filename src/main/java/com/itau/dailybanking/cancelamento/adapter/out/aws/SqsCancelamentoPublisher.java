package com.itau.dailybanking.cancelamento.adapter.out.aws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.dailybanking.cancelamento.application.port.out.CancelamentoEventPublisher;
import com.itau.dailybanking.cancelamento.domain.model.Cancelamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;

@Component
public class SqsCancelamentoPublisher implements CancelamentoEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(SqsCancelamentoPublisher.class);

    private final SqsClient sqs;
    private final String queueUrl;
    private final String endpoint;
    private final ObjectMapper mapper;

    public SqsCancelamentoPublisher(
            SqsClient sqs,
            @Value("${aws.sqs.queue-url}") String queueUrl,
            @Value("${aws.sqs.endpoint}") String endpoint,
            ObjectMapper mapper
    ) {
        this.sqs = sqs;
        this.queueUrl = queueUrl;
        this.endpoint = endpoint;
        this.mapper = mapper;

        log.info("SqsCancelamentoPublisher inicializado → endpoint={} queueUrl={}", endpoint, queueUrl);
    }

    @Override
    public void publish(Cancelamento evento) {
        try {
            String body = mapper.writeValueAsString(evento);
            log.debug("Enviando mensagem para SQS → queueUrl={} body={}", queueUrl, body);

            sqs.sendMessage(r -> r
                    .queueUrl(queueUrl)
                    .messageBody(body)
            );

            log.info("Mensagem enviada com sucesso (debitoId={})", evento.debitoId());
        } catch (Exception e) {
            log.error("Erro ao publicar evento no SQS", e);
            throw new RuntimeException("Erro ao publicar evento no SQS", e);
        }
    }
}
