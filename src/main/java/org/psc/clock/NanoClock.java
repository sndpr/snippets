package org.psc.clock;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class NanoClock extends Clock {

    private final Clock clock = Clock.systemDefaultZone();
    private final Instant start = Instant.now();
    private final long nanosStart = System.nanoTime();

    @Override
    public ZoneId getZone() {
        return clock.getZone();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        clock.withZone(zone);
        return clock;
    }

    @Override
    public Instant instant() {
        long nanosNow = System.nanoTime();
        long delta = nanosNow - nanosStart;
        return start.plusNanos(delta);
    }

}
