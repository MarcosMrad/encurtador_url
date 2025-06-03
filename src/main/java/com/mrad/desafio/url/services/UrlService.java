package com.mrad.desafio.url.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrad.desafio.url.entities.Url;
import com.mrad.desafio.url.repositories.UrlRepository;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository repository;
	
    private static final String Characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LenghtUrl = 6;
	
	public Url shortenUrl(String originalUrl) {
		String shortUrl = generatedShortUrl();
		while(repository.existsByShortUrl(shortUrl)) {
			shortUrl = generatedShortUrl();
		}
		Url url = new Url(null, originalUrl, shortUrl, LocalDateTime.now().plusDays(3));
		
		
		return repository.save(url);
	}

	public Optional<Url> getOriginalUrl(String shortUrl) {
        Optional<Url> urlOptional = repository.findByShortUrl(shortUrl);
        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            if (url.getExpirationDate() != null && url.getExpirationDate().isBefore(LocalDateTime.now())) {
                repository.delete(url);
                return Optional.empty();
            }
        }
        return urlOptional;
    }
	
	private String generatedShortUrl() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < LenghtUrl; i++) {
			sb.append(Characters.charAt(random.nextInt(Characters.length())));
		}
		return sb.toString();
	}
	
}
