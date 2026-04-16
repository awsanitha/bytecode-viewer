package the.bytecode.club.bytecodeviewer.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileHeaderUtilsTest
{
    @Test
    void doesFileHeaderMatch_javaClass()
    {
        byte[] classHeader = {(byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE};
        assertTrue(FileHeaderUtils.doesFileHeaderMatch(classHeader, FileHeaderUtils.JAVA_CLASS_FILE_HEADER));
    }

    @Test
    void doesFileHeaderMatch_wrongHeader()
    {
        byte[] wrongHeader = {0x00, 0x00, 0x00, 0x00};
        assertFalse(FileHeaderUtils.doesFileHeaderMatch(wrongHeader, FileHeaderUtils.JAVA_CLASS_FILE_HEADER));
    }

    @Test
    void doesFileHeaderMatch_tooShort()
    {
        byte[] shortBytes = {(byte) 0xCA, (byte) 0xFE};
        assertFalse(FileHeaderUtils.doesFileHeaderMatch(shortBytes, FileHeaderUtils.JAVA_CLASS_FILE_HEADER));
    }

    @Test
    void getFileHeaderAsString_returnsHex()
    {
        byte[] bytes = {(byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE, 0x00};
        assertEquals("CAFEBABE", FileHeaderUtils.getFileHeaderAsString(bytes));
    }

    @Test
    void getFileHeaderAsString_nullReturnsEmpty()
    {
        assertEquals("", FileHeaderUtils.getFileHeaderAsString(null));
    }

    @Test
    void getFileHeaderAsString_tooShortReturnsEmpty()
    {
        assertEquals("", FileHeaderUtils.getFileHeaderAsString(new byte[]{0x01}));
    }
}
