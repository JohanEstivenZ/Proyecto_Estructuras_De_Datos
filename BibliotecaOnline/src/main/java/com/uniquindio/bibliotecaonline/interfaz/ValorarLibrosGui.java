package com.uniquindio.bibliotecaonline.interfaz;

import com.uniquindio.bibliotecaonline.modelo.Lector;
import com.uniquindio.bibliotecaonline.modelo.Prestamo;
import com.uniquindio.bibliotecaonline.modelo.Libro;

import javax.swing.*;
import java.awt.*;

public class ValorarLibrosGui extends JFrame {

    public ValorarLibrosGui(Lector lector, JFrame ventanaAnterior) {
        setTitle("Valorar Libros");
        setSize(450, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultListModel<Prestamo> modelo = new DefaultListModel<>();
        for (int i = 0; i < lector.historialPrestamos.size(); i++) {
            Prestamo p = lector.historialPrestamos.obtener(i);
            if (!p.isDevuelto()) {
                modelo.addElement(p);
            }
        }

        JList<Prestamo> listaPrestamos = new JList<>(modelo);
        listaPrestamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listaPrestamos);

        JButton btnValorar = new JButton("Valorar y Devolver");
        JButton btnAtras = new JButton("Atrás");

        btnValorar.addActionListener(e -> {
            Prestamo seleccionado = listaPrestamos.getSelectedValue();
            if (seleccionado != null) {
                String[] opciones = { "1", "2", "3", "4", "5" };
                String input = (String) JOptionPane.showInputDialog(
                        this,
                        "Selecciona una valoración (1-5 estrellas):",
                        "Valorar Libro",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[4]
                );

                if (input != null) {
                    try {
                        int valoracion = Integer.parseInt(input);
                        Libro libro = seleccionado.getLibro();
                        libro.registrarValoracion(valoracion); // Asegúrate que este método exista
                        seleccionado.setDevuelto(true);
                        modelo.removeElement(seleccionado);
                        JOptionPane.showMessageDialog(this, "Libro valorado y devuelto exitosamente.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Valoración no válida.");
                    }
                }
            }
        });

        btnAtras.addActionListener(e -> {
            this.dispose();
            ventanaAnterior.setVisible(true);
        });


        JPanel panelBotones = new JPanel();
        panelBotones.add(btnValorar);
        panelBotones.add(btnAtras);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            dispose(); // cerrar esta ventana
        });

        add(btnVolver, BorderLayout.SOUTH);
    }
    }


