package org.psc.clock;

import java.sql.Timestamp;
import java.time.Clock;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NanoClockTest {

	@Test
	public void testNanoClock() throws InterruptedException {
		Clock nanoClock = new NanoClock();
		Timestamp now = Timestamp.from(nanoClock.instant());
		log.info("now = {}", now.toString());

		Thread.sleep(1234);

		Timestamp now1 = Timestamp.from(nanoClock.instant());
		Timestamp now2 = Timestamp.from(nanoClock.instant());
		log.info("now1 = {}", now1.toString());
		log.info("now2 = {}", now2.toString());
	}
}
