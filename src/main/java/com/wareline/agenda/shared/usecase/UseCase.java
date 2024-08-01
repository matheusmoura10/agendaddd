package com.wareline.agenda.shared.usecase;

public abstract class UseCase<I, O> {
    public abstract O execute(final I input);
}
