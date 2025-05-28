package com.uniquindio.bibliotecaonline.interfaz;

import com.uniquindio.bibliotecaonline.logica.GestorLibros;
import com.uniquindio.bibliotecaonline.modelo.Libro;

import javax.swing.*;
import java.awt.*;

public class GestionLibrosGui extends JFrame {

    private final GestorLibros gestorLibros;

    public GestionLibrosGui(JFrame parent, GestorLibros gestorLibros) {
        this.gestorLibros = gestorLibros;

        setTitle("Gestión de Libros");
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(7, 2));
        JTextField campoTitulo = new JTextField();
        JTextField campoAutor = new JTextField();
        JTextField campoCategoria = new JTextField();
        JTextField campoAnio = new JTextField();
        JTextField campoEstado = new JTextField();
        JTextField campoCalificacion = new JTextField();

        panelCampos.add(new JLabel("Título:"));
        panelCampos.add(campoTitulo);
        panelCampos.add(new JLabel("Autor:"));
        panelCampos.add(campoAutor);
        panelCampos.add(new JLabel("Categoría:"));
        panelCampos.add(campoCategoria);
        panelCampos.add(new JLabel("Año de Publicación:"));
        panelCampos.add(campoAnio);
        panelCampos.add(new JLabel("Estado:"));
        panelCampos.add(campoEstado);
        panelCampos.add(new JLabel("Calificación:"));
        panelCampos.add(campoCalificacion);

        JButton btnAgregar = new JButton("Agregar Libro");
        JButton btnEliminar = new JButton("Eliminar Libro");

        btnAgregar.addActionListener(e -> {
            try {
                String titulo = campoTitulo.getText();
                String autor = campoAutor.getText();
                String categoria = campoCategoria.getText();
                int anio = Integer.parseInt(campoAnio.getText());
                String estado = campoEstado.getText();
                double calificacion = Double.parseDouble(campoCalificacion.getText());

                Libro libro = new Libro(titulo, autor, categoria, anio, estado, calificacion);
                gestorLibros.agregarLibro(libro);
                JOptionPane.showMessageDialog(this, "Libro agregado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar el libro: " + ex.getMessage());
            }
        });

        btnEliminar.addActionListener(e -> {
            boolean eliminado = gestorLibros.eliminarLibroPorTitulo(campoTitulo.getText());
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un libro con ese título.");
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }
}
