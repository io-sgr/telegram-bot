/*
 * Copyright 2017-2019 SgrAlpha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.sgr.telegram.bot.engine.processors;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.engine.BotUpdateProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

/**
 * @author SgrAlpha
 */
@RunWith(MockitoJUnitRunner.class)
public class LogOnlyBotUpdateProcessorTest {

    @Mock
    private Logger mockLogger;

    @Test(expected = IllegalArgumentException.class)
    public void testInitWithNullLogger() {
        new LogOnlyBotUpdateProcessor(null);
    }

    @Test
    public void testHandleNullUpdate() {
        final BotUpdateProcessor processor = new LogOnlyBotUpdateProcessor(mockLogger);
        final boolean handled = processor.handleUpdate(null);
        assertTrue(handled);
        verify(mockLogger, times(1)).info(eq("NULL"));
    }

    @Test
    public void testMockUpdate() {
        final BotUpdateProcessor processor = new LogOnlyBotUpdateProcessor(mockLogger);
        final boolean handled = processor.handleUpdate(Mockito.mock(Update.class));
        assertTrue(handled);
        verify(mockLogger, times(1)).info(anyString());
    }

}
