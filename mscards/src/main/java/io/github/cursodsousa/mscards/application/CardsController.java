package io.github.cursodsousa.mscards.application;

import io.github.cursodsousa.mscards.application.dto.CardSaveRequest;
import io.github.cursodsousa.mscards.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CardsController {

    private final CardService service;

    @GetMapping
    public String status(){
        return "ok";
    }
    @PostMapping
    public ResponseEntity cadastra(@RequestBody CardSaveRequest request){
        Card card = request.toModel();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity<List<Card>> getCartoesRendaAteh(@RequestParam("renda") Long renda){
        List<Card> list = service.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }





}
