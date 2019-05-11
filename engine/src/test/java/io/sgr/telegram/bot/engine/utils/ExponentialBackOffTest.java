package io.sgr.telegram.bot.engine.utils;

import static io.sgr.telegram.bot.engine.utils.ExponentialBackOff.DEFAULT_INITIAL_INTERVAL_IN_MILLI;
import static io.sgr.telegram.bot.engine.utils.ExponentialBackOff.DEFAULT_MAX_INTERVAL_IN_MILLI;
import static io.sgr.telegram.bot.engine.utils.ExponentialBackOff.DEFAULT_MULTIPLIER;
import static io.sgr.telegram.bot.engine.utils.ExponentialBackOff.DEFAULT_RANDOMIZATION_FACTOR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Random;

public class ExponentialBackOffTest {

    @Test
    public void testGetInterval() {
        final ExponentialBackOff backOff = ExponentialBackOff.newInstance();
        assertEquals(DEFAULT_INITIAL_INTERVAL_IN_MILLI, backOff.getInitialIntervalInMilli());
        assertEquals(DEFAULT_RANDOMIZATION_FACTOR, backOff.getRandomizationFactor(), 0);
        assertEquals(DEFAULT_MULTIPLIER, backOff.getMultiplier(), 0);
        assertEquals(DEFAULT_MAX_INTERVAL_IN_MILLI, backOff.getMaxIntervalInMilli());

        long currentInterval = backOff.getCurrentInterval();
        long nextBackOff = backOff.getNextBackOffInMilli();
        assertEquals(currentInterval, nextBackOff, currentInterval * backOff.getRandomizationFactor());
        long lastInterval = currentInterval;

        int round = new Random().nextInt(100) + 1;
        while (round > 0) {
            currentInterval = backOff.getCurrentInterval();
            nextBackOff = backOff.getNextBackOffInMilli();
            if (lastInterval != backOff.getMaxIntervalInMilli()) {
                assertTrue(String.format("Round %d, last interval is %d but next back off is %d", round, lastInterval, nextBackOff), nextBackOff > lastInterval);
            }
            double delta = currentInterval * backOff.getRandomizationFactor();
            assertTrue(nextBackOff > currentInterval - delta);
            assertTrue(nextBackOff < currentInterval + delta);
            lastInterval = currentInterval;
            round--;
        }
    }

}
