import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class appproducto {

    public static void main(String[] args) {

        producto producto = new producto("Sin nombre", 0.0, 0, "Sin categoría");

        JFrame frame = new JFrame("Gestión de Producto - Molina Zegarra");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel para organizar campos
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(4, 2, 10, 10)); // 4 filas, 2 columnas

        // Campos
        JTextField txtNombre = new JTextField(15);
        JTextField txtPrecio = new JTextField(15);
        JTextField txtCantidad = new JTextField(15);
        JTextField txtCategoria = new JTextField(15);

        // Agregar en filas limpias
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Precio:"));
        panelCampos.add(txtPrecio);

        panelCampos.add(new JLabel("Cantidad:"));
        panelCampos.add(txtCantidad);

        panelCampos.add(new JLabel("Categoría:"));
        panelCampos.add(txtCategoria);

        // Botón y etiqueta final
        JButton btnActualizar = new JButton("Actualizar Producto");
        JLabel lblInfo = new JLabel("Información del producto aparecerá aquí");

        // Acción del botón
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    producto.setNombre(txtNombre.getText());
                    producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    producto.setCantidadStock(Integer.parseInt(txtCantidad.getText()));
                    producto.setCategoria(txtCategoria.getText());

                    lblInfo.setText(producto.toString());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "⚠ Ingrese valores numéricos válidos.");
                }
            }
        });

        // Panel principal
        frame.setLayout(new BorderLayout());
        frame.add(panelCampos, BorderLayout.NORTH);
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);
        frame.add(panelBoton, BorderLayout.CENTER);
        frame.add(lblInfo, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
