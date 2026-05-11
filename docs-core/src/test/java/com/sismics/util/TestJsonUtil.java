package com.sismics.util;

import com.sismics.BaseTest;
import jakarta.json.JsonValue;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of JSON utilities.
 *
 * @author AI Assistant
 */
public class TestJsonUtil extends BaseTest {

    /**
     * Test nullable() with null String.
     */
    @Test
    public void testNullableStringNull() {
        JsonValue result = JsonUtil.nullable((String) null);
        Assert.assertEquals(JsonValue.NULL, result);
    }

    /**
     * Test nullable() with null Integer.
     */
    @Test
    public void testNullableIntegerNull() {
        JsonValue result = JsonUtil.nullable((Integer) null);
        Assert.assertEquals(JsonValue.NULL, result);
    }

    /**
     * Test nullable() with null Long.
     */
    @Test
    public void testNullableLongNull() {
        JsonValue result = JsonUtil.nullable((Long) null);
        Assert.assertEquals(JsonValue.NULL, result);
    }
}
