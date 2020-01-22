
package net.wth.wordsfinder.controller;


import net.wth.wordsfinder.domain.Zapros;
import net.wth.wordsfinder.service.WordsFinderService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping ("/words")
class IndexController {

    private WordsFinderService wordsFinderService;

    public IndexController(WordsFinderService wordsFinderService) {
        this.wordsFinderService = wordsFinderService;
    }

    @PostMapping
    public List<String> findWords(@RequestBody Zapros zapros) {
        List<String> words = wordsFinderService.findWords(zapros);
        return words;
    }

    }
