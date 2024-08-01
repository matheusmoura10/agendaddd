package com.wareline.agenda.shared.dtos.pagination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.wareline.agenda.shared.enums.OperadoresSql;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GenericSpecification<T> implements Specification<T> {
    private final String field;
    private final OperadoresSql operator;
    private final String value;
    private List<String> values;

    public GenericSpecification() {
        this.field = "";
        this.operator = OperadoresSql.IGUAL;
        this.value = "";

    }

    public GenericSpecification(String field, OperadoresSql operator, String value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public GenericSpecification(String field, String value) {
        this.field = field;
        this.operator = OperadoresSql.IGUAL;
        this.value = value;
    }

    public GenericSpecification(String field, List<String> values) {
        this.field = field;
        this.operator = OperadoresSql.IN;
        this.values = values;
        this.value = null;
    }

    @SuppressWarnings("null")
    @Override
    public Predicate toPredicate(
            @NotNull Root<T> root,
            @NotNull CriteriaQuery<?> query,
            @NotNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (this.field == null || this.value == null || this.operator == null) {
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }

        Expression<String> campoExpression = obterCampoExpressaoModel(root);

        if (campoExpression == null) {
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }

        montarPredicados(criteriaBuilder, predicates, campoExpression);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void montarPredicados(@NotNull CriteriaBuilder criteriaBuilder, List<Predicate> predicates,
            Expression<String> campoExpression) {
        switch (operator) {
            case IN:
                predicates.add(campoExpression.in(values));
                break;
            case LIKE:
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(campoExpression), "%" + value.toLowerCase() + "%"));
                break;
            case NOT_LIKE:
                predicates.add(criteriaBuilder.notLike(campoExpression, "%" + value + "%"));
                break;
            case IGUAL:
                predicates.add(criteriaBuilder.equal(campoExpression, value));
                break;
            case DIFERENTE:
                predicates.add(criteriaBuilder.notEqual(campoExpression, value));
                break;
            case MAIOR:
                predicates.add(criteriaBuilder.greaterThan(campoExpression, value));
                break;
            case MAIOR_IGUAL:
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(campoExpression, value));
                break;
            case MENOR:
                predicates.add(criteriaBuilder.lessThan(campoExpression, value));
                break;
            case MENOR_IGUAL:
                predicates.add(criteriaBuilder.lessThanOrEqualTo(campoExpression, value));
                break;
            default:
                throw new IllegalArgumentException("Operador desconhecido: " + operator);
        }
    }

    private Expression<String> obterCampoExpressaoModel(@NotNull Root<T> root) {
        Expression<String> campoExpression = null;

        if (field.contains(".")) {
            String[] campos = field.split("\\.");
            Join<Object, Object> join = root.join(campos[0]);
            campoExpression = join.get(campos[1]);
        } else {
            campoExpression = root.get(field);
        }
        return campoExpression;
    }
}