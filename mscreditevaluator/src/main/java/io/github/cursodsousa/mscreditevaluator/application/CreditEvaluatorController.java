package io.github.cursodsousa.mscreditevaluator.application;

import io.github.cursodsousa.mscreditevaluator.domain.model.ClientSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class CreditEvaluatorController {

    private final CreditEvaluatorService creditEvaluatorService;

    @GetMapping
    public String status(){
        return"ok";
    }
    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity<ClientSituation> consultaClientSituation(@RequestParam("cpf") String cpf) {
        ClientSituation clientSituation = creditEvaluatorService.obterClientSituation(cpf);
        return ResponseEntity.ok(clientSituation);

    }
}