package the.bytecode.club.bytecodeviewer.util;

import com.google.googlejavaformat.java.Formatter;

/**
 * @author Konloch
 * @since 10/2/2024
 */
public class JavaFormatterUtils
{
    public static String formatJavaCode(String decompiledCode)
    {
        try
        {
            return new Formatter().formatSource(decompiledCode);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return decompiledCode;
        }
    }
}
