package io.github.cursodsousa.mscreditevaluator.application;

import io.github.cursodsousa.mscreditevaluator.domain.model.ClientSituation;
import io.github.cursodsousa.mscreditevaluator.domain.model.DataClient;
import io.github.cursodsousa.mscreditevaluator.clients.ClientControllerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientControllerClient clientesClient;

    public ClientSituation obterClientSituation(String cpf){
        // obterDadosCliente -MSCLIENTS
        // obter cartoes do cliente -MSCARTOES

        ResponseEntity<DataClient> dataClientResponse = clientesClient.dataClient(cpf);

        return ClientSituation
                .builder()
                .cliente(dataClientResponse.getBody())
                .build();

    }
}
