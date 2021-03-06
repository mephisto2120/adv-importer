package com.tryton.adv.importer.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.function.Consumer;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionalConsumer<T> {
    private final Optional<T> optional;

    public static <T> OptionalConsumer<T> of(Optional<T> optional) {
        return new OptionalConsumer<>(optional);
    }

    public OptionalConsumer<T> ifPresent(Consumer<T> consumer) {
        if (optional.isPresent()) {
            optional.ifPresent(consumer);
        }
        return this;
    }

    public OptionalConsumer<T> ifNotPresent(Procedure procedure){
        if(!optional.isPresent()){
            procedure.execute();
        }
        return this;
    }
}
