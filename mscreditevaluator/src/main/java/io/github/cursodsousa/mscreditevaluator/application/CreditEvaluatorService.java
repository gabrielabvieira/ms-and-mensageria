package io.github.cursodsousa.mscreditevaluator.application;

import feign.FeignException;
import io.github.cursodsousa.mscreditevaluator.application.ex.CommunicationErrorMicroservicesException;
import io.github.cursodsousa.mscreditevaluator.application.ex.DataClientNotFoundException;
import io.github.cursodsousa.mscreditevaluator.domain.model.*;
import io.github.cursodsousa.mscreditevaluator.infra.clients.CardsControllerClient;
import io.github.cursodsousa.mscreditevaluator.infra.clients.ClientControllerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientControllerClient clientesClient;
    private final CardsControllerClient cartoesClient;

    public ClientSituation obterClientSituation(String cpf)
            throws DataClientNotFoundException, CommunicationErrorMicroservicesException {
        try {
            // obterDadosCliente -MSCLIENTS
            // obter cartoes do cliente -MSCARTOES
            ResponseEntity<DataClient> dataClientResponse = clientesClient.dataClient(cpf);
            ResponseEntity<List<ClientCard>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

            return ClientSituation
                    .builder()
                    .cliente(dataClientResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DataClientNotFoundException();
            }
            throw new CommunicationErrorMicroservicesException(e.getMessage(), status);
        }
    }

    public ReturnAssessmentClient realizarAvaliacao(String cpf, Long renda)
            throws DataClientNotFoundException, CommunicationErrorMicroservicesException {
        try {

            ResponseEntity<DataClient> dataClientResponse = clientesClient.dataClient(cpf);
            ResponseEntity<List<Card>> cartoesResponse = cartoesClient.getCartoesRendaAteh(renda);

            List<Card> cards = cartoesResponse.getBody();
            var listaCardsApproved = cards.stream().map(card -> {

                DataClient dataClient = dataClientResponse.getBody();

                BigDecimal limiteBasico = card.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dataClient.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);


                CardApproved approved = new CardApproved();
                approved.setCartao(card.getNome());
                approved.setBandeira(card.getBandeira());
                approved.setLimiteAprovado(limiteAprovado);

                return approved;
            }).collect(Collectors.toList());

            return new ReturnAssessmentClient(listaCardsApproved);


        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DataClientNotFoundException();
            }
            throw new CommunicationErrorMicroservicesException(e.getMessage(), status);
        }
    }
}
