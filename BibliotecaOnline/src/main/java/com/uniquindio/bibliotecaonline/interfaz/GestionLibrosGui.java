package com.uniquindio.bibliotecaonline.interfaz;

import com.uniquindio.bibliotecaonline.logica.GestorLibros;
import com.uniquindio.bibliotecaonline.modelo.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionLibrosGui extends JFrame {

    private final GestorLibros gestorLibros;
    private final DefaultTableModel modeloTabla;
    private final JTable tablaLibros;

    public GestionLibrosGui(JFrame parent, GestorLibros gestorLibros) {
        this.gestorLibros = gestorLibros;

        setTitle("Gestión de Libros");
        setSize(800, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Campos de texto
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

        // Botones
        JButton btnAgregar = new JButton("Agregar Libro");
        JButton btnEliminar = new JButton("Eliminar Libro");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        // Tabla de libros
        modeloTabla = new DefaultTableModel(
                new Object[]{"Título", "Autor", "Categoría", "Año", "Estado", "Calificación"}, 0
        );
        tablaLibros = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaLibros);

        // Agregar componentes al frame
        add(panelCampos, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción de agregar
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
                actualizarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar el libro: " + ex.getMessage());
            }
        });

        // Acción de eliminar
        btnEliminar.addActionListener(e -> {
            String titulo = campoTitulo.getText();
            boolean eliminado = gestorLibros.eliminarLibroPorTitulo(titulo);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.");
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un libro con ese título.");
            }
        });

        actualizarTabla(); // Inicializa la tabla con los datos existentes
        setVisible(true);
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0); // Limpia la tabla
        for (Libro libro : gestorLibros.getListaLibros()) {
            modeloTabla.addRow(new Object[]{
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getCategoria(),
                    libro.getAñoPublicacion(),
                    libro.getEstado(),
                    libro.getCalificacion()
            });
        }
    }
}
