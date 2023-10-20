package io.github.cursodsousa.mscards.application.dto;


import io.github.cursodsousa.mscards.domain.Card;
import io.github.cursodsousa.mscards.domain.FlagCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {
    private String nome;
    private FlagCard bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Card toModel(){
        return new Card(nome, bandeira, renda, limite);
    }
}
