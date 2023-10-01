package io.github.sursodsousa.mscreditevaluator.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacoes-credito")
public class CreditEvaluatorController {

    @GetMapping
    public String status(){
        return"ok";
    }
}
