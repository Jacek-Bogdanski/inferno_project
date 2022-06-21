package com.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWrite {
    public FileWrite(String path, String content) {
        try {
            byte[] strToBytes = content.getBytes();
            Files.write(Paths.get(path), strToBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
