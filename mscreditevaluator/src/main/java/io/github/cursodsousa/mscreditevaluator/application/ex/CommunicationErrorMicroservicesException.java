package io.github.cursodsousa.mscreditevaluator.application.ex;

import lombok.Getter;

public class CommunicationErrorMicroservicesException extends Exception{

    @Getter
    private Integer status;

    public CommunicationErrorMicroservicesException(String msg, Integer status){
        super(msg);
        this.status = status;
    }
}
