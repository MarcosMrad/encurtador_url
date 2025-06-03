package com.mrad.desafio.url.resources;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mrad.desafio.url.entities.Url;
import com.mrad.desafio.url.entities.dto.ShortenRequestDTO;
import com.mrad.desafio.url.services.UrlService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api")
public class UrlResource {

	@Autowired
	private UrlService service;

	@PostMapping(value = "/shorten")
	public ResponseEntity<Url> shortenUrl(@RequestBody ShortenRequestDTO request){
		String originalUrlFromDto = request.originalURL();
		if(originalUrlFromDto == null || originalUrlFromDto.isEmpty() ) {
			return ResponseEntity.badRequest().build();
		}
		Url createdUrl = service.shortenUrl(originalUrlFromDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUrl);
	}
	
	@GetMapping("/s/{shortUrl}")
    public void redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        Optional<Url> urlOptional = service.getOriginalUrl(shortUrl);
        if (urlOptional.isPresent()) {
            response.sendRedirect(urlOptional.get().getOriginalUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
	
	@GetMapping("/details/{shortUrl}")
    public ResponseEntity<Url> getUrlDetails(@PathVariable String shortUrl) {
        return service.getOriginalUrl(shortUrl)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "URL não encontrada"));
    }
}
	
