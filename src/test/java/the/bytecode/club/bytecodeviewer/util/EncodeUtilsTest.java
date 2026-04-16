package the.bytecode.club.bytecodeviewer.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncodeUtilsTest
{
    @Test
    void stringToUnicode_roundTrip()
    {
        String original = "Hello";
        String unicode = EncodeUtils.stringToUnicode(original);
        assertNotNull(unicode);
        assertTrue(unicode.startsWith("\\u"));
        assertEquals(original, EncodeUtils.unicodeToString(unicode));
    }

    @Test
    void unicodeToString_knownValue()
    {
        // 'A' = U+0041
        assertEquals("A", EncodeUtils.unicodeToString("\\u0041"));
    }

    @Test
    void convertStringToUTF8_asciiPassthrough()
    {
        // ASCII chars (<= 255) pass through as-is
        String result = EncodeUtils.convertStringToUTF8("ABC");
        assertEquals("ABC", result);
    }

    @Test
    void convertStringToUTF8_nullReturnsNull()
    {
        assertNull(EncodeUtils.convertStringToUTF8(null));
    }

    @Test
    void convertUTF8ToString_nullReturnsNull()
    {
        assertNull(EncodeUtils.convertUTF8ToString(null));
    }

    @Test
    void convertUTF8ToString_roundTrip()
    {
        // "Hi" in hex: 48 69
        String hex = EncodeUtils.convertStringToUTF8("Hi");
        assertNotNull(hex);
        assertEquals("Hi", EncodeUtils.convertUTF8ToString(hex));
    }
}
