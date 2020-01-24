package net.wth.wordsfinder.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class FinderImpl implements Finder{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String pathToFile = "classpath:static//pldf-win.txt";
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List<String> find(String letters, int count) {
        logger.info("Поступил запрос на поиск: Символы: *" + letters + "*, количество символов: " + count);
        Resource resource = resourceLoader.getResource(pathToFile);

        Reader reader = null;
        try {
            InputStream stream = resource.getInputStream();
            logger.info("Попытка чтения из файла ");// + resource.getFile().getAbsolutePath());
            reader = new Reader(stream);
        } catch (IOException e) {
            logger.info("Ошибка чтения из файла ");
            e.printStackTrace();
        }
        List<String> wordsWithLength = reader.findWordsWithLength(count);
        logger.info("Найдено всего слов с длиной " + count + " : " + wordsWithLength.size());
        List<String> words = analyze(wordsWithLength, letters, count);
        logger.info("Найдено всего слов, соответствующих условиям: " + words.size());
        return words;
    }

    private List<String> analyze(List<String> wordsWithLength, String letters, int count) {
        List<String> words = new ArrayList<>();
        ArrayList<Character> arrLetters = convertLettersToArrayList(letters);

        for (String word : wordsWithLength) {
            boolean trueWord = true;
            ArrayList<Character> tempList = new ArrayList<>(arrLetters);
            ArrayList<Character> arrWord = convertLettersToArrayList(word);
            for(int i = 0; i < count; i++){
                if(tempList.contains(arrWord.get(i))) {
                    tempList.remove(arrWord.get(i));
                } else {
                    trueWord = false;
                    break;
                }
            }
            if (trueWord)
                words.add(word);
        }
        return words;
    }

    private ArrayList<Character> convertLettersToArrayList(String letters) {
        ArrayList<Character> arr = new ArrayList<>();
        for(int i = 0; i < letters.length(); i++) {
            arr.add(letters.charAt(i));
        }
        return arr;
    }

}
