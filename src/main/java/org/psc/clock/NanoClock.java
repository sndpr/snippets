package org.psc.clock;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class NanoClock extends Clock {

	private Clock clock = Clock.systemDefaultZone();
	private Instant start = Instant.now();
	private long nanosStart = System.nanoTime();
	
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
