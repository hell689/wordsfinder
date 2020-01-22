package net.wth.wordsfinder.service;

import net.wth.wordsfinder.domain.Zapros;

import java.util.List;

public interface WordsFinderService {
    List<String> findWords(Zapros zapros);
}
