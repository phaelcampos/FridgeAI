package dev.raphael.fridgeai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class RecipeService {
    @Value("${api.key}")
    public String apiKey;

    @Value("${open.ai.url}")
    public String apiUrl;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public RecipeService(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    public Mono<String> getRecipeService() {
        System.out.println("API KEY: " + apiKey);
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4.1-nano-2025-04-14",
                "input", "Write a one-sentence bedtime story about a unicorn"
        );
        return webClient.post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonString -> {
                    try {
                        JsonNode root = objectMapper.readTree(jsonString);
                        return root.path("output").get(0).path("content").get(0).path("text").asText();
                    } catch (Exception e) {
                        throw new RuntimeException("Falha ao parsear a resposta da API", e);
                    }
                });
    }
}
