package org.example.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class PointOfMenu {

    private final @Getter String text;
    private final Supplier<Boolean> action;

    public Supplier<Boolean> getAction() {

        return action;
    }
}
