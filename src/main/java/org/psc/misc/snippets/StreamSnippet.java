package org.psc.misc.snippets;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StreamSnippet {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamSnippet.class);

    public static void main(String[] args) throws MalformedURLException {

        List<String> stringList = List.of("abc", "def", "ghi", "jklmnopq");

        AtomicInteger counter = new AtomicInteger(0);
        List<ValueHolder> values = stringList.stream()
                .map(e -> new ValueHolder(counter.getAndIncrement(), e)).collect(Collectors.toList());

        values.forEach(e -> LOGGER.info("{} - {}", e.id, e.value));

        var someVar = new URL("http://www.youtube.com");

        LOGGER.info(someVar.toString());
    }

    @Data
    @AllArgsConstructor
    private static class ValueHolder {
        int id;
        String value;
    }
}
