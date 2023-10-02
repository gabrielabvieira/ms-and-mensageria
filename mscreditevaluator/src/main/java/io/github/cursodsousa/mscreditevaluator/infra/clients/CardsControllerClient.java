package io.github.cursodsousa.mscreditevaluator.infra.clients;


import io.github.cursodsousa.mscreditevaluator.domain.model.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cartoes")
public interface CardsControllerClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getCartoesByCliente(@RequestParam("cpf") String cpf);
}
