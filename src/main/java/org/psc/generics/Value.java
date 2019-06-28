package org.psc.generics;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Value<T> {
    private T value;

    public static void main(String[] args) {
        List<Value<?>> list = new ArrayList<>();
        list.add(new Value<String>("sadasd"));
        list.add(new Value<Integer>(555));

        String s = (String) list.get(0).getValue();
        System.out.println(s);
        Integer i = (Integer) list.get(1).getValue();
        System.out.println(i);
    }
}
