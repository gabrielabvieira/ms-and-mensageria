package io.github.cursodsousa.mscreditevaluator.infra.clients;

import io.github.cursodsousa.mscreditevaluator.domain.model.DataClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclients", path = "/clientes")
public interface ClientControllerClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DataClient> dataClient(@RequestParam String cpf);
}
