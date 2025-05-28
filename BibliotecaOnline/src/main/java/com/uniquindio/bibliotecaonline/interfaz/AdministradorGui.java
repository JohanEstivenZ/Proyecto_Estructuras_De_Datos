package com.uniquindio.bibliotecaonline.interfaz;

import com.uniquindio.bibliotecaonline.logica.GestorLectores;
import com.uniquindio.bibliotecaonline.logica.GestorLibros;
import com.uniquindio.bibliotecaonline.logica.GestorPrestamos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministradorGui extends JFrame {

    private final BibliotecaGUI bibliotecaGUI;
    private final GestorPrestamos gestor;
    private final GestorLibros gestorLibros;
    private final GestorLectores gestorLectores;

    public AdministradorGui(BibliotecaGUI bibliotecaGUI) {
        this.bibliotecaGUI = bibliotecaGUI;
        this.gestorLibros = new GestorLibros();
        this.gestorLectores = new GestorLectores();
        this.gestor = new GestorPrestamos(gestorLibros, gestorLectores);

        setTitle("Interfaz de Administrador");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de estadísticas
        JPanel panelEstadistica = new JPanel();
        JButton btnBuscar = new JButton("Generar Estadísticas");
        JComboBox<String> comboEstadisticas = new JComboBox<>(new String[]{
                "Seleccione", "Cantidad de préstamos por lector", "Libros más valorados",
                "Lectores con más conexiones", "Caminos más cortos entre dos lectores",
                "Detectar clústeres de afinidad (grupos)"
        });

        panelEstadistica.add(new JLabel("Seleccione una estadística:"));
        panelEstadistica.add(comboEstadisticas);
        panelEstadistica.add(btnBuscar);

        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnLibros = new JButton("Añadir y eliminar libros");
        JButton btnUsuarios = new JButton("Gestionar usuarios");
        JButton btnGrafo = new JButton("Visualizar el grafo de afinidad entre lectores");
        JButton btnAtras = new JButton("Atrás");

        Dimension botonSize = new Dimension(220, 40);
        btnLibros.setPreferredSize(botonSize);
        btnUsuarios.setPreferredSize(botonSize);
        btnGrafo.setPreferredSize(botonSize);
        btnAtras.setPreferredSize(botonSize);

        panelBotones.add(btnLibros);
        panelBotones.add(btnUsuarios);
        panelBotones.add(btnGrafo);
        panelBotones.add(btnAtras);

        // Añadir paneles al frame
        add(panelEstadistica, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        // Botón Atrás
        btnAtras.addActionListener(e -> {
            bibliotecaGUI.setVisible(true);
            dispose();
        });

        // Conexión correcta a las clases hijas con parámetros
        btnLibros.addActionListener(e -> {
            GestionLibrosGui gestionLibrosGui = new GestionLibrosGui(this, gestorLibros);
            gestionLibrosGui.setVisible(true);
        });

        btnUsuarios.addActionListener(e -> {
            GestionUsuariosGui gestionUsuariosGui = new GestionUsuariosGui(this, gestorLectores);
            gestionUsuariosGui.setVisible(true);
        });

        btnGrafo.addActionListener(e -> {
            GrafoAfinidadGui grafoAfinidadGui = new GrafoAfinidadGui(this);
            grafoAfinidadGui.setVisible(true);
        });

        setVisible(true);
    }
}


