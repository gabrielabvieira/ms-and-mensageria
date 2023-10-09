package io.github.cursodsousa.mscreditevaluator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnAssessmentClient {
    private List<CardApproved> cartoes;
}
