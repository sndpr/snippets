package org.psc.python;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyArray;

public class PythonGuestScript {

    public static void main(String[] args) {
        try (Context context = Context.create("python")) {
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

            Value evaluatedContext = context.eval("python", """
                def add(a, b):
                    return a + b
                    
                # does it accept type annotations?
                def greet(name: str) -> str:
                    print('hi from python')
                    return f'Hello {name}!'
                    
                    
                def sum_of_squares(ints: [int]) -> int:
                    return sum( i * i for i in ints )
                """);
            long anotherLong = evaluatedContext.invokeMember("add", 51561, 8978).asLong();
            System.out.println(anotherLong);

            var greeting = evaluatedContext.invokeMember("greet", "world").asString();
            System.out.println(greeting);

            var sumOfSquares =
                evaluatedContext.invokeMember("sum_of_squares", ProxyArray.fromArray(2, 2, 3, 2, 5)).asLong();
            System.out.println(sumOfSquares);
        }
    }
}
