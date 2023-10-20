package io.github.cursodsousa.mscards.application.representation;

import io.github.cursodsousa.mscards.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesPorClienteResponse {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesPorClienteResponse fromModel(ClientCard model){
        return new CartoesPorClienteResponse(
                model.getCard().getNome(),
                model.getCard().getBandeira().toString(),
                model.getLimite()
        );
    }

}
