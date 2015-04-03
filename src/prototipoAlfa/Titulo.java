/*

 */
package prototipoAlfa;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author José (el mero mero sabor ranshero) :V
 */
public class Titulo extends JFrame {
    
   
    private Image imgPrincipal; // imagen del conejo
    private Image imgBack; // imagen del fondo
    JPanel panel = new JPanel(); // panel para insertar el boton y otros elementos
    JButton button = new JButton("Saltar >>"); // boton

    public Titulo (String strTitulo) {
        // se cargan las imagenes
        URL urlImagenConejo = this.getClass().getResource("principal.gif");
        URL urlBack = this.getClass().getResource("whiteBack.jpg");
        imgPrincipal = Toolkit.getDefaultToolkit().getImage(urlImagenConejo);
        imgBack = Toolkit.getDefaultToolkit().getImage(urlBack);
        // el acomodo de los elementos en el panel es libre
        panel.setLayout(null);
        // configuracion del boton
        button.setBounds(400, 400, 100, 100);
        panel.add(button); // se aniade el boton al panel
        add(panel); // se aniade el panel al frame
        // configuracion del frame
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(strTitulo);
        // se enlaza al boton el listener correspondiente
        button.addActionListener(new SiguienteFrame());
    }
    
    public class SiguienteFrame  implements ActionListener {
        // cuando se presione el boton
        public void actionPerformed(ActionEvent ev) {
            // se abre una segunda vetana
            new secondFrame("Second window");
            // se cierra el frame presente
            Titulo.this.setVisible(false);
            Titulo.this.dispose();
        }

    } 
    
        /**
     * paint
     *
     * Metodo sobrescrito de la clase <code>JFrame</code>, 
     * 
     * En este metodo lo que hace es actualizar el contenedor y define cuando
     * usar ahora el paint
     *
     * @param graGrafico es el <code>objeto grafico</code> usado para dibujar.
     *
     */

    public void paint(Graphics graDibujo) {
          // graDibujo.drawImage(imgBack, 0, 0, this); // se dibuja un fondo blanco
           graDibujo.drawImage(imgPrincipal, 200, 200, this); // imagen del conejo
           
    }
}
