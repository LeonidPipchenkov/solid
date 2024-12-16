package net.happiness.file;

public interface FileService {
    void createFile(String filename);
    void writeToFile(String filename, String content);
    String readFromFile(String filename);
}
