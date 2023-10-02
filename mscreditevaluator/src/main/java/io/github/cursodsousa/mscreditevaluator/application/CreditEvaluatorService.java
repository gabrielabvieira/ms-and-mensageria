package io.github.cursodsousa.mscreditevaluator.application;

import feign.FeignException;
import io.github.cursodsousa.mscreditevaluator.application.ex.CommunicationErrorMicroservicesException;
import io.github.cursodsousa.mscreditevaluator.application.ex.DataClientNotFoundException;
import io.github.cursodsousa.mscreditevaluator.domain.model.ClientCard;
import io.github.cursodsousa.mscreditevaluator.domain.model.ClientSituation;
import io.github.cursodsousa.mscreditevaluator.domain.model.DataClient;
import io.github.cursodsousa.mscreditevaluator.infra.clients.CardsControllerClient;
import io.github.cursodsousa.mscreditevaluator.infra.clients.ClientControllerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientControllerClient clientesClient;
    private final CardsControllerClient cartoesClient;

    public ClientSituation obterClientSituation(String cpf)
            throws DataClientNotFoundException, CommunicationErrorMicroservicesException{
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

        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DataClientNotFoundException();
            }
            throw new CommunicationErrorMicroservicesException(e.getMessage(), status);
            }
        }
}
