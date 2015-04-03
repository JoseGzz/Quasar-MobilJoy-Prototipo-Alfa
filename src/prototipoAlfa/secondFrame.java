/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipoAlfa;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author José
 */
public class secondFrame extends JFrame {

    JPanel panel = new JPanel(); // panel para insertar el boton y otros elementos
    JButton button = new JButton(); // boton
    
    public secondFrame(String strTitulo) throws HeadlessException {
        // el acomodo de los elementos en el panel es libre
        panel.setLayout(null);
        button.setBounds(400, 400, 100, 100);
        panel.add(button); // se aniade un boton al panel
        add(panel); // se aniade el panel al frame
        // configuracion del frame
        setTitle(strTitulo); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800, 600);
         
        // se enlaza al boton el listener correspondiente
         button.addActionListener(new secondFrame.RegresaTitulo());
        
    }
    
  public class RegresaTitulo  implements ActionListener {
      // si se presiona el boton
        public void actionPerformed(ActionEvent ev) {
            // se abre la ventana del titulo
            new Titulo("First window");
            // se cierra la actual
            secondFrame.this.setVisible(false);
            secondFrame.this.dispose();
        }

    } 
    
}
