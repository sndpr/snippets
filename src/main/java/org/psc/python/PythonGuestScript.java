package org.psc.python;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class PythonGuestScript {

    public static void main(String[] args) {
        try (Context context = Context.create()) {
            Value result = context.eval(
                "python",
                """
                    type('obj', (object,), {
                        'id'  : 42,
                        'text': '42',
                        'arr' : [1,42,3]
                    })()
                    """);
            assert result.hasMembers();

            int id = result.getMember("id").asInt();
            assert id == 42;

            String text = result.getMember("text").asString();
            assert text.equals("42");

            Value array = result.getMember("arr");
            assert array.hasArrayElements();
            assert array.getArraySize() == 3;
            assert array.getArrayElement(1).asInt() == 42;

            Value someAddition = context.eval("python", """
                def some_fun():
                    a = 4
                    b = 5
                    return a + b
                """);
            long aLong = someAddition.invokeMember("some_fun").asLong();
            System.out.println(aLong);

            Value additionWithParams = context.eval("python", """
                def some_fun(a, b):
                    return a + b
                """);
            long anotherLong = additionWithParams.invokeMember("some_fun", 51561, 8978).asLong();
            System.out.println(anotherLong);
        }
    }
}
