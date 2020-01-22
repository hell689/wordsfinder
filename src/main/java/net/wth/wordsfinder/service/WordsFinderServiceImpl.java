package net.wth.wordsfinder.service;

import net.wth.wordsfinder.domain.Zapros;
import net.wth.wordsfinder.engine.Finder;
import net.wth.wordsfinder.engine.FinderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordsFinderServiceImpl implements WordsFinderService {

    private Finder finder;

    public WordsFinderServiceImpl(Finder finder) {
        this.finder = finder;
    }

    @Override
    public List<String> findWords(Zapros zapros) {
        return finder.find(zapros.getLetters(), zapros.getCount());
    }
}
