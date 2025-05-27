package com.uniquindio.bibliotecaonline.modelo;

public class Libro implements Comparable<Libro> {
    private String titulo;
    private String autor;
    private String categoria;
    private int añoPublicacion;
    private String estado;
    private double calificacion;

    private int totalValoraciones;
    private int sumaValoraciones;

    public static final String DISPONIBLE = "Disponible";
    public static final String PRESTADO = "Prestado";

    public Libro(String titulo, String autor, String categoria, int añoPublicacion, String estado, double calificacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.añoPublicacion = añoPublicacion;
        setEstado(estado);
        this.calificacion = calificacion;
        this.totalValoraciones = 0;
        this.sumaValoraciones = 0;
    }

    public Libro(String titulo) {
        this(titulo, "", "", 0, DISPONIBLE, 0.0);
    }

    public void registrarValoracion(int estrellas) {
        if (estrellas < 1 || estrellas > 5) {
            throw new IllegalArgumentException("La valoración debe estar entre 1 y 5.");
        }
        sumaValoraciones += estrellas;
        totalValoraciones++;
        calificacion = (double) sumaValoraciones / totalValoraciones;
    }

    @Override
    public int compareTo(Libro otro) {
        return this.titulo.compareToIgnoreCase(otro.titulo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libro libro = (Libro) obj;
        return titulo.equalsIgnoreCase(libro.titulo);
    }

    @Override
    public int hashCode() {
        return titulo.toLowerCase().hashCode();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado.equals(DISPONIBLE) || estado.equals(PRESTADO)) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado inválido. Debe ser 'Disponible' o 'Prestado'.");
        }
    }

    public double getCalificacion() {
        return calificacion;
    }

    // Ya no se recomienda usar este método directamente
    public void setCalificacion(double calificacion) {
        if (calificacion < 0 || calificacion > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 5");
        }
        this.calificacion = calificacion;
        this.totalValoraciones = 1;
        this.sumaValoraciones = (int) calificacion;
    }

    public int getTotalValoraciones() {
        return totalValoraciones;
    }

    public void prestar() {
        if (this.estado.equals(PRESTADO)) {
            throw new IllegalStateException("El libro ya está prestado");
        }
        this.estado = PRESTADO;
    }

    public void devolver() {
        if (this.estado.equals(DISPONIBLE)) {
            throw new IllegalStateException("El libro ya está disponible");
        }
        this.estado = DISPONIBLE;
    }

    @Override
    public String toString() {
        return String.format(
                "Libro [Título: %s, Autor: %s, Categoría: %s, Año: %d, Estado: %s, Calificación: %.2f (%d valoraciones)]",
                titulo, autor, categoria, añoPublicacion, estado, calificacion, totalValoraciones
        );
    }
}
