package io.github.cursodsousa.mscards.application;

import io.github.cursodsousa.mscards.application.dto.CardSaveRequest;
import io.github.cursodsousa.mscards.application.representation.CartoesPorClienteResponse;
import io.github.cursodsousa.mscards.domain.Card;
import io.github.cursodsousa.mscards.domain.ClientCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CardsController {

    private final CardService cardService;
    private final ClientCardService clientCardService;

    @GetMapping
    public String status(){
        return "ok";
    }
    @PostMapping
    public ResponseEntity cadastra(@RequestBody CardSaveRequest request){
        Card card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Card>> getCartoesRendaAteh(@RequestParam("renda") Long renda){
        List<Card> list = cardService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }
    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(
            @RequestParam("cpf") String cpf){
        List<ClientCard> lista = clientCardService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = lista.stream()
                .map((CartoesPorClienteResponse::fromModel))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);

    }

}
