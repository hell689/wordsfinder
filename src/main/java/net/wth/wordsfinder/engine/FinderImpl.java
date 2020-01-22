package net.wth.wordsfinder.engine;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FinderImpl implements Finder{

    private static final String pathToFile = "files/pldf-win.txt";

    @Override
    public List<String> find(String letters, int count) {
        System.out.println("Поступил запрос на поиск: Символы: *" + letters + "*, количество символов: " + count);
        Resource resource = new ClassPathResource(pathToFile);

        Reader reader = null;
        try {
            reader = new Reader(resource.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> wordsWithLength = reader.findWordsWithLength(count);
        System.out.println("Найдено всего слов с длиной " + count + " : " + wordsWithLength.size());
        List<String> words = analyze(wordsWithLength, letters, count);
        System.out.println("Найдено всего слов, соответствующих условиям: " + words.size());
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
