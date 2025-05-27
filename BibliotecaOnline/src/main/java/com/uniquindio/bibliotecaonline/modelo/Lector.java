package com.uniquindio.bibliotecaonline.modelo;

import com.uniquindio.bibliotecaonline.estructuras.ListaEnlazada;
import java.time.LocalDate;

public class Lector extends Usuario implements Comparable<Lector>{

    public ListaEnlazada<Prestamo> historialPrestamos;
    protected ListaEnlazada<Valoracion> valoraciones;

    public ListaEnlazada<Prestamo> getHistorialPrestamos() {
        return historialPrestamos;
    }

    public ListaEnlazada<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setHistorialPrestamos(ListaEnlazada<Prestamo> historialPrestamos) {
        this.historialPrestamos = historialPrestamos;
    }

    public void setValoraciones(ListaEnlazada<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public Lector(String idUsuario, String nombre, String correo, String contraseña, LocalDate añoNacimiento) {
        super(idUsuario, nombre, correo, contraseña, añoNacimiento);
        this.historialPrestamos = new ListaEnlazada<>();
        this.valoraciones = new ListaEnlazada<>();
    }

    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo != null) {
            historialPrestamos.insertarElementoAlFinal(prestamo);
        }
    }

    public void agregarValoracion(Valoracion valoracion) {
        if (valoracion != null) {
            valoraciones.insertarElementoAlFinal(valoracion);
        }
    }

    @Override
    public int compareTo(Lector otro) {
        return this.getNombre().compareToIgnoreCase(otro.getNombre());
    }
}