package io.github.cursodsousa.msclients.application;

import io.github.cursodsousa.msclients.application.representation.ClientSaveRequest;
import io.github.cursodsousa.msclients.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Slf4j
public class ClientsController {

    private final ClientService service;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de clientes");
        return "ok";
    }
    @PostMapping
    public ResponseEntity save(@RequestBody ClientSaveRequest request) {
       var client  = request.toModel();
       service.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dataClient(@RequestParam String cpf){
        var client = service.getByCPF(cpf);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
}
