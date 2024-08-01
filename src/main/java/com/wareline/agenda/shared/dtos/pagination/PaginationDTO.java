package com.wareline.agenda.shared.dtos.pagination;

import java.util.List;
import java.util.function.Function;

public record PaginationDTO<T>(
        int currentPage,
        int perPage,
        long total,
        List<T> items) {

    public <R> PaginationDTO<R> map(final Function<T, R> mapper) {
        final List<R> aNewList = this.items.stream()
                .map(mapper)
                .toList();

        return new PaginationDTO<>(currentPage(), perPage(), total(), aNewList);
    }
}