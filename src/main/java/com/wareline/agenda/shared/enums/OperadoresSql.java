package com.wareline.agenda.shared.enums;

import lombok.Getter;

@Getter
public enum OperadoresSql {
    IGUAL("="),
    DIFERENTE("!="),
    MAIOR(">"),
    MAIOR_IGUAL(">="),
    MENOR("<"),
    MENOR_IGUAL("<="),
    IN("in"),
    LIKE("LIKE"),
    NOT_LIKE("not like"),;

    private final String operador;

    OperadoresSql(String operador) {
        this.operador = operador;
    }

    public static OperadoresSql getOperador(String operador) {
        for (OperadoresSql operadoresSql : OperadoresSql.values()) {
            if (operadoresSql.getOperador().equals(operador)) {
                return operadoresSql;
            }
        }
        return null;
    }
}