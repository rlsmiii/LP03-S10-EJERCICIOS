package Sonidos;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AppSonidos {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reproductor de Efectos de Sonido - Molina Zegarra");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton btnAplausos = new JButton("Aplausos");
        JButton btnCampana = new JButton("Campana");
        JButton btnExplosion = new JButton("Explosión");

        panel.add(btnAplausos);
        panel.add(btnCampana);
        panel.add(btnExplosion);

        // Acción de cada botón
        btnAplausos.addActionListener(e -> reproducirSonido("aplausos.wav"));
        btnCampana.addActionListener(e -> reproducirSonido("campana.wav"));
        btnExplosion.addActionListener(e -> reproducirSonido("explosion.wav"));


        frame.add(panel);
        frame.setVisible(true);
    }

    // ====================================
    // MÉTODO PARA REPRODUCIR ARCHIVOS WAV
    // ====================================
public static void reproducirSonido(String archivo) {
    try {
        File sonido = new File("Sonidos/" + archivo);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(sonido);

        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo cargar: " + archivo);
        e.printStackTrace();
    }
}

}
