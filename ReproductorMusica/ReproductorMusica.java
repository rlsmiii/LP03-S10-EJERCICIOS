package ReproductorMusica;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ReproductorMusica extends JFrame {

    private Clip clip;
    private Long posicionPausa = 0L;   // almacena dónde se pausó

    public ReproductorMusica() {
        super("Reproductor de Música - Molina Zegarra");

        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton btnReproducir = new JButton("Reproducir");
        JButton btnPausar = new JButton("Pausar");
        JButton btnReanudar = new JButton("Reanudar");

        add(btnReproducir);
        add(btnPausar);
        add(btnReanudar);

        // Acción de Reproducir
        btnReproducir.addActionListener(e -> reproducir("musica.wav"));

        // Acción de Pausar
        btnPausar.addActionListener(e -> pausar());

        // Acción de Reanudar
        btnReanudar.addActionListener(e -> reanudar());

        setVisible(true);
    }

    private void reproducir(String archivo) {
    try {
        File sonido = new File("d:/APRENDIZAJE DE CHIBOLOS/ReproductorMusica/musica.wav");
        AudioInputStream audio = AudioSystem.getAudioInputStream(sonido);

        clip = AudioSystem.getClip();
        clip.open(audio);

        posicionPausa = 0L;
        clip.setMicrosecondPosition(0);
        clip.start();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private void pausar() {
        if (clip != null && clip.isRunning()) {
            posicionPausa = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    private void reanudar() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(posicionPausa);
            clip.start();
        }
    }

    public static void main(String[] args) {
        new ReproductorMusica();
    }
}
