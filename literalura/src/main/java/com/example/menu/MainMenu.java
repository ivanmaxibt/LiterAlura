package com.example.menu;

import com.example.GutendexClient;
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("Menú:");
                System.out.println("1. Buscar libro por título.");
                System.out.println("2. Listar libros registrados.");
                System.out.println("3. Listar autores registrados.");
                System.out.println("4. Listar autores vivos en un determinado año.");
                System.out.println("5. Listar libros por idioma.");
                System.out.println("6. Salir.");
                System.out.print("Seleccione una opción: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Ingrese el título del libro: ");
                        String title = scanner.nextLine();
                        GutendexClient.searchBookByTitle(title);
                        break;
                    case 2:
                        GutendexClient.listRegisteredBooks();
                        break;
                    case 3:
                        GutendexClient.listRegisteredAuthors();
                        break;
                    case 4:
                        System.out.print("Ingrese el año: ");
                        int year = scanner.nextInt();
                        scanner.nextLine();  // Limpiar el buffer
                        GutendexClient.listLivingAuthors(year);
                        break;
                    case 5:
                        System.out.print("Ingrese el idioma: ");
                        String language = scanner.nextLine();
                        GutendexClient.listBooksByLanguage(language);
                        break;
                    case 6:
                        exit = true;
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}