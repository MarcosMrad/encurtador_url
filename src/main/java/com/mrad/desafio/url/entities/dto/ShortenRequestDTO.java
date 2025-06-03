package com.mrad.desafio.url.entities.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record ShortenRequestDTO(
		@NotBlank
		@URL
		String originalUrl) {}
