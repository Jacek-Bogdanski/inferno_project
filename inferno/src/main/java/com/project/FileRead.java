package com.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileRead {

    private String content = "";

    public FileRead(String path) {
        String fileContent;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            fileContent = new String(bytes);
            this.content = fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getContent() {
        return content;
    }
}
