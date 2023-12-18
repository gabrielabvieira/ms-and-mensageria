package io.github.cursodsousa.mscards.application;

import io.github.cursodsousa.mscards.domain.ClientCard;
import io.github.cursodsousa.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {
    private final ClientCardRepository repository;

    public List<ClientCard> listCartoesByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
