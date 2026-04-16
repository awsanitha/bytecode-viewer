package the.bytecode.club.bytecodeviewer.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ZipUtilsTest
{
    @TempDir
    Path tempDir;

    @Test
    void zipFile_createsZipContainingFile() throws IOException
    {
        Path input = tempDir.resolve("hello.txt");
        Files.writeString(input, "hello world");

        File outputZip = tempDir.resolve("out.zip").toFile();
        ZipUtils.zipFile(input.toFile(), outputZip);

        assertTrue(outputZip.exists());
        assertTrue(outputZip.length() > 0);
    }

    @Test
    void zipFolder_andUnzip_roundTrip() throws Exception
    {
        // Create a source folder with a file
        Path srcFolder = tempDir.resolve("src");
        Files.createDirectory(srcFolder);
        Path srcFile = srcFolder.resolve("data.txt");
        Files.writeString(srcFile, "test content");

        String zipPath = tempDir.resolve("archive.zip").toString();
        ZipUtils.zipFolder(srcFolder.toString(), zipPath, null);

        assertTrue(new File(zipPath).exists());

        // Unzip to a new directory
        Path destDir = tempDir.resolve("dest");
        Files.createDirectory(destDir);
        ZipUtils.unzipFilesToPath(zipPath, destDir.toString());

        // The unzipped file should exist somewhere under destDir
        boolean found = Files.walk(destDir)
            .anyMatch(p -> p.getFileName().toString().equals("data.txt"));
        assertTrue(found, "Expected data.txt to be extracted");
    }
}
