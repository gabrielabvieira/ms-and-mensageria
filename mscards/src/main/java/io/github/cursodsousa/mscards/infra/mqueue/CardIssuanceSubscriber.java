package io.github.cursodsousa.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cursodsousa.mscards.domain.Card;
import io.github.cursodsousa.mscards.domain.CardIssuanceRequestData;
import io.github.cursodsousa.mscards.domain.ClientCard;
import io.github.cursodsousa.mscards.infra.repository.CardRepository;
import io.github.cursodsousa.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receiveRequestIssue(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            CardIssuanceRequestData data = mapper.readValue(payload, CardIssuanceRequestData.class);
            Card card = cardRepository.findById(data.getIdCartao()).orElseThrow();
            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(data.getCpf());
            clientCard.setLimite(data.getLimiteLiberado());

            clientCardRepository.save(clientCard);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
