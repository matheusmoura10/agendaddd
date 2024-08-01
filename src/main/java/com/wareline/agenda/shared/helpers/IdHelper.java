package com.wareline.agenda.shared.helpers;

import java.util.UUID;

public final class IdHelper {
    private IdHelper() {
    }

    public static String uuid() {
        String uuid = UUID.randomUUID().toString().toLowerCase().replace("-", "");

        if (uuid.isEmpty()) {
            throw new IllegalStateException("UUID is empty");
        }

        return uuid;
    }
}
