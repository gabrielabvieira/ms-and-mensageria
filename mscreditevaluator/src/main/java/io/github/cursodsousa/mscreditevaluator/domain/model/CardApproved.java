package io.github.cursodsousa.mscreditevaluator.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardApproved {
    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;
}
