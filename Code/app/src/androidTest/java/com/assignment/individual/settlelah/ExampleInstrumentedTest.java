package com.assignment.individual.settlelah;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.test.InstrumentationRegistry;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        InstrumentationRegistry InstrumentationRegistry = null;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.assignment.individual.settlelah", appContext.getPackageName());
    }
}