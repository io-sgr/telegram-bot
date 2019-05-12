package io.sgr.telegram.bot.api.http;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureBasedCallbackTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructWithoutResponseTye() {
        new CompletableFutureBasedCallback<>(null, true, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructWithoutLogger() {
        new CompletableFutureBasedCallback<>(new CompletableFuture<>(), true, null);
    }

}
