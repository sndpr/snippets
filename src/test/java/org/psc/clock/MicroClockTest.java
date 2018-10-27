package org.psc.clock;

import java.sql.Timestamp;
import java.time.Clock;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MicroClockTest {

    @Test
    public void testMicroClock() throws InterruptedException {
        Clock microClock = new MicroClock();
        Timestamp now = Timestamp.from(microClock.instant());
        log.info("now = {}", now.toString());

        Thread.sleep(1234);

        Timestamp now1 = Timestamp.from(microClock.instant());
        Timestamp now2 = Timestamp.from(microClock.instant());
        log.info("now1 = {}", now1.toString());
        log.info("now2 = {}", now2.toString());
    }
}
