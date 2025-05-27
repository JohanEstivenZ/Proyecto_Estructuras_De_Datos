package com.uniquindio.bibliotecaonline.interfaz;

import com.uniquindio.bibliotecaonline.modelo.Lector;


import javax.swing.*;
import com.uniquindio.bibliotecaonline.modelo.*;

import java.awt.*;

public class DevolucionesGui extends JFrame {

    public DevolucionesGui(Lector lector, JFrame ventanaAnterior) {
        setTitle("Devolver Libros");
        setSize(400, 300);
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

        JButton btnDevolver = new JButton("Devolver");
        JButton btnAtras = new JButton("Atrás");

        btnDevolver.addActionListener(e -> {
            Prestamo seleccionado = listaPrestamos.getSelectedValue();
            if (seleccionado != null) {
                seleccionado.setDevuelto(true);
                modelo.removeElement(seleccionado);
                JOptionPane.showMessageDialog(this, "Libro devuelto correctamente.");
            }
        });

        btnAtras.addActionListener(e -> {
            this.dispose();
            ventanaAnterior.setVisible(true);
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnDevolver);
        panelBotones.add(btnAtras);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}

