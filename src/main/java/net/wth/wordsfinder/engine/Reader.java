package net.wth.wordsfinder.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private File file;

    public Reader(File file) {
        this.file = file;
    }

    public List<String> findWordsWithLength(int length) {
        List<String> wordsWithLength = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                if (tmp.length() == length) {
                    wordsWithLength.add(tmp);
                }
            }
        } catch (IOException e) {
            logger.info("Ошибка чтения из файла " + file.getAbsolutePath());
            System.err.println("Ошибка чтения из файла " + file.getAbsolutePath());
            e.printStackTrace();
        }
        return wordsWithLength;
    }
}
