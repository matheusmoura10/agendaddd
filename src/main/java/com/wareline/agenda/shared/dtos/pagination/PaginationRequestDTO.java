package com.wareline.agenda.shared.dtos.pagination;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationRequestDTO<T> {

    private int page;
    private int size;
    private String orderBy;
    private String direction;
    private List<GenericSpecification<T>> specifications;

}
