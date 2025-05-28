package com.uniquindio.bibliotecaonline.interfaz;

import com.uniquindio.bibliotecaonline.logica.GestorLectores;
import com.uniquindio.bibliotecaonline.modelo.Lector;

import javax.swing.*;
import java.awt.*;

public class GestionUsuariosGui extends JFrame {

    private final GestorLectores gestorLectores;

    public GestionUsuariosGui(JFrame parent, GestorLectores gestorLectores) {
        this.gestorLectores = gestorLectores;

        setTitle("Gestión de Usuarios");
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2));

        JTextField campoNombre = new JTextField();
        JTextField campoId = new JTextField();

        JButton btnAgregar = new JButton("Agregar Usuario");
        JButton btnEliminar = new JButton("Eliminar Usuario");

        add(new JLabel("Nombre:"));
        add(campoNombre);
        add(new JLabel("ID:"));
        add(campoId);

        add(btnAgregar);
        add(btnEliminar);

        btnAgregar.addActionListener(e -> {
            gestorLectores.agregarLector(new Lector(campoId.getText(), campoNombre.getText()));
            JOptionPane.showMessageDialog(this, "Usuario agregado.");
        });

        btnEliminar.addActionListener(e -> {
            gestorLectores.eliminarLector(campoId.getText());
            JOptionPane.showMessageDialog(this, "Usuario eliminado si existía.");
        });

        setVisible(true);
    }
}
