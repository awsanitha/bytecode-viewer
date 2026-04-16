package the.bytecode.club.bytecodeviewer.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LazyNameUtilTest
{
    @BeforeEach
    void reset()
    {
        LazyNameUtil.reset();
        LazyNameUtil.sameNameJarWorkspace = false;
    }

    @Test
    void firstOccurrence_returnsOriginalName()
    {
        assertEquals("Foo.class", LazyNameUtil.applyNameChanges("Foo.class"));
    }

    @Test
    void secondOccurrence_appendsSequenceNumber()
    {
        LazyNameUtil.applyNameChanges("Foo.class");
        String second = LazyNameUtil.applyNameChanges("Foo.class");
        assertEquals("Foo#1.class", second);
    }

    @Test
    void thirdOccurrence_incrementsSequenceNumber()
    {
        LazyNameUtil.applyNameChanges("Foo.class");
        LazyNameUtil.applyNameChanges("Foo.class");
        String third = LazyNameUtil.applyNameChanges("Foo.class");
        assertEquals("Foo#2.class", third);
    }

    @Test
    void secondOccurrence_setsSameNameJarWorkspace()
    {
        assertFalse(LazyNameUtil.sameNameJarWorkspace);
        LazyNameUtil.applyNameChanges("Bar.jar");
        LazyNameUtil.applyNameChanges("Bar.jar");
        assertTrue(LazyNameUtil.sameNameJarWorkspace);
    }

    @Test
    void removeName_afterAddingOnce_removesEntry()
    {
        LazyNameUtil.applyNameChanges("Baz.class");
        LazyNameUtil.removeName("Baz.class");
        // After removal, next add should return original name again (no collision)
        assertEquals("Baz.class", LazyNameUtil.applyNameChanges("Baz.class"));
    }

    @Test
    void removeName_blank_doesNothing()
    {
        assertDoesNotThrow(() -> LazyNameUtil.removeName(""));
        assertDoesNotThrow(() -> LazyNameUtil.removeName(null));
    }
}
