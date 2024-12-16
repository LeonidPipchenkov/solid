package net.happiness.file.impl;

import net.happiness.file.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DefaultFileService implements FileService {

    @Override
    public void createFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void writeToFile(String filename, String content) {
        try {
            Path filePath = Path.of(filename);
            Files.writeString(filePath, content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String readFromFile(String filename) {
        try {
            Path filePath = Path.of(filename);
            return Files.readString(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
