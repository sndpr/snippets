package org.psc.generics;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class ToStringMappingStore {

    private final Map<Class<?>, Function<?, String>> mappings;

    public ToStringMappingStore(Map<Class<?>, Function<?, String>> mappings) {
        this.mappings = mappings;
    }

    public ToStringMappingStore() {
        mappings = new ConcurrentHashMap<>();

        mappings.put(String.class, s -> "'" + s + "'");
        mappings.put(Integer.class, String::valueOf);
    }

    public <T> void putMapping(Class<T> type, Function<T, String> mapping){
        mappings.put(type, mapping);
    }

    public <T> Function<T, String> getMapping(Class<T> type){
        //noinspection unchecked
        return (Function<T, String>) mappings.get(type);
    }
}
