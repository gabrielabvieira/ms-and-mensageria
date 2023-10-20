package io.github.cursodsousa.mscreditevaluator.application.ex;

public class CardRequestErrorException extends RuntimeException {
    public CardRequestErrorException(String message) {
        super(message);
    }
}
