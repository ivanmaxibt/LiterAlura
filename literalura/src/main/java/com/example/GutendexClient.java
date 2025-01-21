package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashSet;
import java.util.Set;

public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private static HttpClient client = HttpClient.newHttpClient();

    public static void searchBookByTitle(String title) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "?search=" + title))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray books = jsonResponse.getAsJsonArray("results");

        if (books.size() == 0) {
            System.out.println("No se encontraron libros con el título: " + title);
        } else {
            System.out.println("Resultados de la búsqueda para el título \"" + title + "\":");
            for (int i = 0; i < books.size(); i++) {
                JsonObject book = books.get(i).getAsJsonObject();
                System.out.println("Título: " + book.get("title").getAsString());
                JsonArray authors = book.getAsJsonArray("authors");
                for (int j = 0; j < authors.size(); j++) {
                    JsonObject author = authors.get(j).getAsJsonObject();
                    System.out.println("Autor: " + author.get("name").getAsString());
                }
                System.out.println();
            }
        }
    }

    public static void listRegisteredBooks() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray books = jsonResponse.getAsJsonArray("results");

        if (books.size() == 0) {
            System.out.println("No se encontraron libros registrados.");
        } else {
            System.out.println("Lista de libros registrados:");
            for (int i = 0; i < books.size(); i++) {
                JsonObject book = books.get(i).getAsJsonObject();
                System.out.println("Título: " + book.get("title").getAsString());
                JsonArray authors = book.getAsJsonArray("authors");
                for (int j = 0; j < authors.size(); j++) {
                    JsonObject author = authors.get(j).getAsJsonObject();
                    System.out.println("Autor: " + author.get("name").getAsString());
                }
                System.out.println();
            }
        }
    }

    public static void listRegisteredAuthors() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray books = jsonResponse.getAsJsonArray("results");
        Set<String> authorsSet = new HashSet<>();

        if (books.size() == 0) {
            System.out.println("No se encontraron autores registrados.");
        } else {
            System.out.println("Lista de autores registrados:");
            for (int i = 0; i < books.size(); i++) {
                JsonObject book = books.get(i).getAsJsonObject();
                JsonArray authors = book.getAsJsonArray("authors");
                for (int j = 0; j < authors.size(); j++) {
                    JsonObject author = authors.get(j).getAsJsonObject();
                    authorsSet.add(author.get("name").getAsString());
                }
            }
            for (String author : authorsSet) {
                System.out.println("Autor: " + author);
            }
        }
    }

    public static void listLivingAuthors(int year) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray books = jsonResponse.getAsJsonArray("results");
        Set<String> authorsSet = new HashSet<>();

        if (books.size() == 0) {
            System.out.println("No se encontraron autores vivos en el año: " + year);
        } else {
            System.out.println("Lista de autores vivos en el año " + year + ":");
            for (int i = 0; i < books.size(); i++) {
                JsonObject book = books.get(i).getAsJsonObject();
                JsonArray authors = book.getAsJsonArray("authors");
                for (int j = 0; j < authors.size(); j++) {
                    JsonObject author = authors.get(j).getAsJsonObject();
                    int birthYear = author.get("birth_year").isJsonNull() ? Integer.MIN_VALUE : author.get("birth_year").getAsInt();
                    int deathYear = author.get("death_year").isJsonNull() ? Integer.MAX_VALUE : author.get("death_year").getAsInt();
                    if (year >= birthYear && year <= deathYear) {
                        authorsSet.add(author.get("name").getAsString());
                    }
                }
            }
            for (String author : authorsSet) {
                System.out.println("Autor: " + author);
            }
        }
    }

    public static void listBooksByLanguage(String language) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "?languages=" + language))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray books = jsonResponse.getAsJsonArray("results");

        if (books.size() == 0) {
            System.out.println("No se encontraron libros en el idioma: " + language);
        } else {
            System.out.println("Resultados de la búsqueda para el idioma \"" + language + "\":");
            for (int i = 0; i < books.size(); i++) {
                JsonObject book = books.get(i).getAsJsonObject();
                System.out.println("Título: " + book.get("title").getAsString());
                JsonArray authors = book.getAsJsonArray("authors");
                for (int j = 0; j < authors.size(); j++) {
                    JsonObject author = authors.get(j).getAsJsonObject();
                    System.out.println("Autor: " + author.get("name").getAsString());
                }
                System.out.println();
            }
        }
    }
}
