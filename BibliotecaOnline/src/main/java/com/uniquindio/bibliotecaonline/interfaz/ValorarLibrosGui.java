package com.uniquindio.bibliotecaonline.vista;

import com.uniquindio.bibliotecaonline.modelo.Prestamo;
import com.uniquindio.bibliotecaonline.modelo.Lector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ValorarLibrosGui extends JFrame {

    private Lector lector;
    private JFrame ventanaAnterior;

    private DefaultListModel<Prestamo> modelo;
    private JList<Prestamo> listaPrestamos;

    public ValorarLibrosGui(Lector lector, JFrame ventanaAnterior) {
        this.lector = lector;
        this.ventanaAnterior = ventanaAnterior;

        setTitle("Valorar Libros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inicializar modelo y lista
        modelo = new DefaultListModel<>();
        listaPrestamos = new JList<>(modelo);

        // Agregar préstamos no devueltos
        for (int i = 0; i < lector.historialPrestamos.size(); i++) {
            Prestamo p = lector.historialPrestamos.obtener(i);
            if (!p.isDevuelto()) {
                modelo.addElement(p);
            }
        }

        // Mostrar los préstamos en la lista
        listaPrestamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(listaPrestamos), BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnValorarLibro = new JButton("Valorar");
        JButton btnVolver = new JButton("Volver");

        panelBotones.add(btnValorarLibro);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción para el botón Valorar
        btnValorarLibro.addActionListener((ActionEvent e) -> {
            Prestamo seleccionado = listaPrestamos.getSelectedValue();

            if (seleccionado != null) {
                String[] opciones = { "1", "2", "3", "4", "5" };
                String input = (String) JOptionPane.showInputDialog(
                        this,
                        "¿Cuántas estrellas le das al libro?",
                        "Valorar Libro",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[4]
                );

                if (input != null) {
                    int calificacion = Integer.parseInt(input);
                    seleccionado.getLibro().setCalificacion(calificacion); // Asegúrate de tener este método
                    seleccionado.setDevuelto(true); // marcar como devuelto
                    modelo.removeElement(seleccionado); // quitar de la lista visual

                    JOptionPane.showMessageDialog(this,
                            "Gracias por valorar el libro con " + calificacion + " estrellas.");
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Selecciona un préstamo para valorar.");
            }
        });

        // Acción para volver
        btnVolver.addActionListener(e -> {
            this.dispose();
            ventanaAnterior.setVisible(true);
        });
    }

    @Override
    public String toString() {
        return lector.getNombre(); // Para que se muestre bien si esta ventana se agrega a una lista u otro componente
    }
}

