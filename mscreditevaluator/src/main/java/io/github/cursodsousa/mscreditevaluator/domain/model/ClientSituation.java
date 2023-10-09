package io.github.cursodsousa.mscreditevaluator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSituation {
    private DataClient cliente;
    private List<ClientCard> cartoes;
}
