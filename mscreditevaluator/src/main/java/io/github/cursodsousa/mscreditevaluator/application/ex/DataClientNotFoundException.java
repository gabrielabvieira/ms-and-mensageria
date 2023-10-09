package io.github.cursodsousa.mscreditevaluator.application.ex;

public class DataClientNotFoundException extends Exception{
    public DataClientNotFoundException() {
        super("Dados do cliente não encontrados para o CPF informado.");

    }
}
