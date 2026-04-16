package the.bytecode.club.bytecodeviewer.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MiscUtilsTest
{
    @Test
    void randomString_correctLength()
    {
        String s = MiscUtils.randomString(10);
        assertEquals(10, s.length());
        assertTrue(s.matches("[A-Za-z]+"));
    }

    @Test
    void randomStringNum_correctLength()
    {
        String s = MiscUtils.randomStringNum(12);
        assertEquals(12, s.length());
        assertTrue(s.matches("[A-Za-z0-9]+"));
    }

    @Test
    void getRandomizedName_isUnique()
    {
        String a = MiscUtils.getRandomizedName();
        String b = MiscUtils.getRandomizedName();
        assertNotEquals(a, b);
        assertEquals(25, a.length());
    }

    @Test
    void extension_extractsExtension()
    {
        assertEquals("jar", MiscUtils.extension("myfile.jar"));
        assertEquals("class", MiscUtils.extension("com/example/Foo.class"));
    }

    @Test
    void guessIfBinary_textIsFalse()
    {
        byte[] text = "Hello, World!".getBytes();
        assertFalse(MiscUtils.guessIfBinary(text));
    }

    @Test
    void guessIfBinary_binaryIsTrue()
    {
        // Mostly null bytes — clearly binary
        byte[] binary = new byte[100]; // all zeros (non-printable)
        assertTrue(MiscUtils.guessIfBinary(binary));
    }

    @Test
    void deduplicateAndTrim_removesDuplicates()
    {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "a", "c"));
        MiscUtils.deduplicateAndTrim(list, 10);
        assertEquals(List.of("a", "b", "c"), list);
    }

    @Test
    void deduplicateAndTrim_trimsToMaxLength()
    {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        MiscUtils.deduplicateAndTrim(list, 3);
        assertEquals(3, list.size());
    }

    @Test
    void deduplicateAndTrim_removesEmptyStrings()
    {
        List<String> list = new ArrayList<>(Arrays.asList("a", "", "b"));
        MiscUtils.deduplicateAndTrim(list, 10);
        assertEquals(List.of("a", "b"), list);
    }

    @Test
    void getChildFromPath_returnsLastSegment()
    {
        assertEquals("Foo.class", MiscUtils.getChildFromPath("com/example/Foo.class"));
    }

    @Test
    void getChildFromPath_noSlashReturnsInput()
    {
        assertEquals("Foo.class", MiscUtils.getChildFromPath("Foo.class"));
    }

    @Test
    void getChildFromPath_nullReturnsNull()
    {
        assertNull(MiscUtils.getChildFromPath(null));
    }

    @Test
    void listFiles_nullReturnsEmptyArray()
    {
        assertEquals(0, MiscUtils.listFiles(null).length);
    }
}
