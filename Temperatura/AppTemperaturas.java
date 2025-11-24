package Temperatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppTemperaturas {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Temperaturas Semanales - MolinaZegarra");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel de entradas (lunes a domingo)
        JPanel panelCampos = new JPanel(new GridLayout(7, 2, 5, 5));

        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        JTextField[] campos = new JTextField[7];

        for (int i = 0; i < 7; i++) {
            panelCampos.add(new JLabel(dias[i] + ":"));
            campos[i] = new JTextField(10);
            panelCampos.add(campos[i]);
        }

        JButton btnMostrar = new JButton("Mostrar Gráfico");

        // Panel para el gráfico
        GraficoPanel graficoPanel = new GraficoPanel();

        // Acción del botón
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[] temperaturas = new double[7];

                try {
                    for (int i = 0; i < 7; i++) {
                        temperaturas[i] = Double.parseDouble(campos[i].getText());
                    }

                    graficoPanel.setTemperaturas(temperaturas);
                    graficoPanel.repaint();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese valores numéricos válidos.");
                }
            }
        });

        // Panel superior (campos + botón)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(panelCampos, BorderLayout.CENTER);
        topPanel.add(btnMostrar, BorderLayout.SOUTH);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(graficoPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}


// =============================================
// PANEL QUE DIBUJA EL GRÁFICO DE TEMPERATURAS
// =============================================
class GraficoPanel extends JPanel {

    private double[] temperaturas = {0,0,0,0,0,0,0};  // valores iniciales

    public void setTemperaturas(double[] temps) {
        this.temperaturas = temps;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Suavizado (antialias)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Márgenes
        int margin = 50;

        // Dibujar los ejes
        g2.drawLine(margin, height - margin, width - margin, height - margin); // eje X
        g2.drawLine(margin, margin, margin, height - margin); // eje Y

        // Etiquetas X (días)
        String[] dias = {"L", "M", "X", "J", "V", "S", "D"};

        int espacioX = (width - 2 * margin) / 6;

        for (int i = 0; i < 7; i++) {
            int x = margin + i * espacioX;
            g2.drawString(dias[i], x - 3, height - margin + 20);
        }

        // Escala Y
        double maxTemp = obtenerMaximo();

        if (maxTemp == 0) maxTemp = 1; // evita división por cero

        // Dibujar línea + puntos
        int[] puntosX = new int[7];
        int[] puntosY = new int[7];

        for (int i = 0; i < 7; i++) {

            puntosX[i] = margin + (i * espacioX);

            // Escalar la temperatura y voltear eje Y
            puntosY[i] = (int) (height - margin - (temperaturas[i] / maxTemp) * (height - 2 * margin));
        }

        // Dibujar línea
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2));

        for (int i = 0; i < 6; i++) {
            g2.drawLine(puntosX[i], puntosY[i], puntosX[i + 1], puntosY[i + 1]);
        }

        // Dibujar puntos
        g2.setColor(Color.RED);
        for (int i = 0; i < 7; i++) {
            g2.fillOval(puntosX[i] - 4, puntosY[i] - 4, 8, 8);
        }
    }

    private double obtenerMaximo() {
        double max = temperaturas[0];
        for (double t : temperaturas) {
            if (t > max) max = t;
        }
        return max;
    }
}
