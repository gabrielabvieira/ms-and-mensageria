package io.github.cursodsousa.mscreditevaluator.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientCard {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;
}
