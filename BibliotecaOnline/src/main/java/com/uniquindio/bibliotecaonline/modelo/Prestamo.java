
package com.uniquindio.bibliotecaonline.modelo;

import java.time.LocalDate;


public class Prestamo implements Comparable<Prestamo> {

    private Libro libro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean devuelto; // NUEVO campo

    public Prestamo(Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = false; // Por defecto no está devuelto
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }



    @Override
    public int compareTo(Prestamo otro) {
        return this.fechaPrestamo.compareTo(otro.fechaPrestamo);
    }

}
