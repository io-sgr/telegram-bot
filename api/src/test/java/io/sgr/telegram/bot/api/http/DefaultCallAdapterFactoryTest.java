package io.sgr.telegram.bot.api.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import io.sgr.telegram.bot.api.models.http.ApiResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.CallAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

public class DefaultCallAdapterFactoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCallAdapterFactory.class);

    @Test
    public void testCreateAdapter() {
        DefaultCallAdapterFactory factory = new DefaultCallAdapterFactory(false, LOGGER);
        Type returnType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{String.class};
            }

            @Override
            public Type getRawType() {
                return CompletableFuture.class;
            }

            @Override
            public Type getOwnerType() {
                return Object.class;
            }
        };
        CallAdapter<?, ?> adapter = factory.get(returnType, new Annotation[0], null);
        assertNotNull(adapter);
        Type responseType = adapter.responseType();
        assertTrue(responseType instanceof ParameterizedType);
        assertEquals(Object.class, ((ParameterizedType) responseType).getOwnerType());
        assertEquals(ApiResponse.class, ((ParameterizedType) responseType).getRawType());
        assertEquals(String.class, ((ParameterizedType) responseType).getActualTypeArguments()[0]);
        assertEquals(DefaultCallAdapterFactory.ApiResponseType.class, responseType.getClass());
    }

    @Test(expected = IllegalStateException.class)
    public void testNonParameterizedCompletableFuture() {
        DefaultCallAdapterFactory factory = new DefaultCallAdapterFactory(false, LOGGER);
        assertNull(factory.get(CompletableFuture.class, new Annotation[0], null));
    }

    @Test
    public void testUnsupportedReturnType() {
        DefaultCallAdapterFactory factory = new DefaultCallAdapterFactory(false, LOGGER);
        assertNull(factory.get(Object.class, new Annotation[0], null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructWithoutLogger() {
        new DefaultCallAdapterFactory(true, null);
    }

}
