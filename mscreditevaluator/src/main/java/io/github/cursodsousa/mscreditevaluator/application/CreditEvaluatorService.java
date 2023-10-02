package io.github.cursodsousa.mscreditevaluator.application;

import io.github.cursodsousa.mscreditevaluator.domain.model.ClientCard;
import io.github.cursodsousa.mscreditevaluator.domain.model.ClientSituation;
import io.github.cursodsousa.mscreditevaluator.domain.model.DataClient;
import io.github.cursodsousa.mscreditevaluator.infra.clients.CardsControllerClient;
import io.github.cursodsousa.mscreditevaluator.infra.clients.ClientControllerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientControllerClient clientesClient;
    private final CardsControllerClient cartoesClient;

    public ClientSituation obterClientSituation(String cpf){
        // obterDadosCliente -MSCLIENTS
        // obter cartoes do cliente -MSCARTOES

        ResponseEntity<DataClient> dataClientResponse = clientesClient.dataClient(cpf);
        ResponseEntity<List<ClientCard>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

        return ClientSituation
                .builder()
                .cliente(dataClientResponse.getBody())
                .cartoes(cartoesResponse.getBody())
                .build();

    }
}
