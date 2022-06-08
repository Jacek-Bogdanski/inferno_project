package com.project;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWrite {
    public FileWrite(String path, String conent) {
        try {
            byte[] strToBytes = conent.getBytes();
            Files.write(Paths.get(path), strToBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
