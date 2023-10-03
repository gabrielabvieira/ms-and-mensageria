package io.github.cursodsousa.mscreditevaluator.application;

import io.github.cursodsousa.mscreditevaluator.application.ex.CommunicationErrorMicroservicesException;
import io.github.cursodsousa.mscreditevaluator.application.ex.DataClientNotFoundException;
import io.github.cursodsousa.mscreditevaluator.domain.model.AssessmentData;
import io.github.cursodsousa.mscreditevaluator.domain.model.ClientSituation;
import io.github.cursodsousa.mscreditevaluator.domain.model.ReturnAssessmentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity consultaClientSituation(@RequestParam("cpf") String cpf) {
        try {
            ClientSituation clientSituation = creditEvaluatorService.obterClientSituation(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (DataClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CommunicationErrorMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity realizarAvaliação(@RequestBody AssessmentData data){
        try {
            ReturnAssessmentClient returnAssessmentClient = creditEvaluatorService
                    .realizarAvaliacao(data.getCpf(), data.getRenda());
            return ResponseEntity.ok(returnAssessmentClient);
        } catch (DataClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CommunicationErrorMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}