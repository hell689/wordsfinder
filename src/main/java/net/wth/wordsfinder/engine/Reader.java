package net.wth.wordsfinder.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    //private Logger logger = LoggerFactory.getLogger(this.getClass());

    private InputStream stream;

    public Reader(InputStream stream) {
        this.stream = stream;
    }

    public List<String> findWordsWithLength(int length) {
        List<String> wordsWithLength = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                if (tmp.length() == length) {
                    wordsWithLength.add(tmp);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла ");
            e.printStackTrace();
        }
        return wordsWithLength;
    }
}
