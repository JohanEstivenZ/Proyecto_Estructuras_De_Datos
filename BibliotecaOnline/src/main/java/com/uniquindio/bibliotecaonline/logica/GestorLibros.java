
package com.uniquindio.bibliotecaonline.logica;

import com.uniquindio.bibliotecaonline.modelo.Libro;
import com.uniquindio.bibliotecaonline.estructuras.ListaEnlazada;

import java.io.*;

public class GestorLibros {
    private ListaEnlazada<Libro> listaLibros;
    private final File archivo = new File("src/main/resources/libros.txt");

    public GestorLibros() {
        this.listaLibros = new ListaEnlazada<>();
        cargarLibrosDesdeArchivo();
    }

    private void cargarLibrosDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    String titulo = partes[0].trim();
                    String autor = partes[1].trim();
                    String genero = partes[2].trim();
                    int año = Integer.parseInt(partes[3].trim());
                    String estado = partes[4].trim();
                    double calificacion = Double.parseDouble(partes[5].trim());

                    Libro libro = new Libro(titulo, autor, genero, año, estado, calificacion);
                    listaLibros.insertarElementoAlFinal(libro);
                } else {
                    System.err.println("Formato incorrecto en línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error en formato numérico: " + e.getMessage());
        }
    }

    private void guardarLibrosEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Libro libro : listaLibros) {
                writer.println(libro.getTitulo() + "," +
                        libro.getAutor() + "," +
                        libro.getCategoria() + "," +
                        libro.getAñoPublicacion() + "," +
                        libro.getEstado() + "," +
                        libro.getCalificacion());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar libros: " + e.getMessage());
        }
    }

    public void agregarLibro(Libro libro) {
        if (libro != null) {
            listaLibros.insertarElementoAlFinal(libro);
            guardarLibrosEnArchivo(); // Guardar en el archivo
        }
    }

    public boolean eliminarLibroPorTitulo(String titulo) {
        for (Libro libro : listaLibros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                listaLibros.eliminarElemento(libro);
                guardarLibrosEnArchivo(); // Guardar cambios
                return true;
            }
        }
        return false;
    }

    public ListaEnlazada<Libro> getListaLibros() {
        return listaLibros;
    }

    public ListaEnlazada<String> obtenerTodosLosAutores() {
        ListaEnlazada<String> autores = new ListaEnlazada<>();
        for (Libro libro : listaLibros) {
            String autor = libro.getAutor();
            if (!autores.busqueda(autor)) {
                autores.insertarElementoAlFinal(autor);
            }
        }
        return autores;
    }
}
