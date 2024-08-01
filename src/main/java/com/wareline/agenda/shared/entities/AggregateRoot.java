package com.wareline.agenda.shared.entities;

import com.wareline.agenda.shared.vo.Identifier;

public abstract class AggregateRoot<T extends Identifier> extends BaseEntity<T> {

    protected AggregateRoot(final T id) {
        super(id);
    }
}