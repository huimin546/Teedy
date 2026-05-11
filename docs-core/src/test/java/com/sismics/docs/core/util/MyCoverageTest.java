package com.sismics.docs.core.util;
import com.sismics.docs.core.util.ValidationUtil;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyCoverageTest {
    @Test
    public void testValidation() {
        // 这个类通常有检查邮箱的方法，我们通过不同的输入来覆盖 if-else 分支
        assertTrue(ValidationUtil.isValidEmail("test@example.com"));
        assertFalse(ValidationUtil.isValidEmail("invalid-email"));
        assertFalse(ValidationUtil.isValidEmail(null));
    }
}