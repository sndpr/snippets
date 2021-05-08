package org.psc.clock;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

public class MicroClock extends Clock {

    private final Clock clock = Clock.systemDefaultZone();
    private final Instant start = Instant.now().with(ChronoField.NANO_OF_SECOND,
            (int) (((int) (Instant.now().getNano() / 1e6)) * 1e6));
    private final long microsStart = System.nanoTime() / 1000;

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
    public synchronized Instant instant() {
        long nanosNow = System.nanoTime();
        long delta = nanosNow / 1000 - microsStart;
        return start.plusNanos(delta * 1000);
    }

}
