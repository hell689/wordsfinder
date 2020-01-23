
package net.wth.wordsfinder.controller;


import net.wth.wordsfinder.domain.Zapros;
import net.wth.wordsfinder.service.WordsFinderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping ("/words")
class IndexController {

    private WordsFinderService wordsFinderService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public IndexController(WordsFinderService wordsFinderService) {
        this.wordsFinderService = wordsFinderService;
    }

    @PostMapping
    public List<String> findWords(@RequestBody Zapros zapros) {
        logger.info("Поступил запрос " + zapros);
        List<String> words = new ArrayList<>();
        try {
            words = wordsFinderService.findWords(zapros);
        } catch (Exception e) {
            words.add(e.getMessage());
            e.printStackTrace();
            return words;
        }
        logger.info("Запрос обработан");
        return words;
    }

}
