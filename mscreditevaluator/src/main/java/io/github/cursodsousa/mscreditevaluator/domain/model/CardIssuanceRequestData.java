package io.github.cursodsousa.mscreditevaluator.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardIssuanceRequestData {
    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;




}
