package com.uniquindio.bibliotecaonline.interfaz;

import javax.swing.*;
import java.awt.*;

public class GrafoAfinidadGui extends JFrame {

    public GrafoAfinidadGui(JFrame parent) {
        setTitle("Grafo de Afinidad entre Lectores");
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Aquí se mostrará el grafo (simulado o con librería gráfica).", JLabel.CENTER);
        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
}

