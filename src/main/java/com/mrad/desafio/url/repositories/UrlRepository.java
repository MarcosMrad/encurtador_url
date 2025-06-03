package com.mrad.desafio.url.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrad.desafio.url.entities.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {

	Optional<Url> findByShortUrl(String shortUrl);
	boolean existsByShortUrl(String shortUrl);
}
