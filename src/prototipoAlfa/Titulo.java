/*

 */
package prototipoAlfa;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author José (el mero mero sabor ranshero) :V
 */
public class Titulo extends JFrame implements MouseListener {
    
   
    private Image imgPrincipal; // imagen del conejo
    private Image imgSaltar; // imagen del fondo
    JPanel panel = new JPanel(); // panel para insertar el boton y otros elementos
    JButton button = new JButton("Saltar >>"); // boton
    Image imgBotonSaltar;
    Image imgFondo;
    Boton btnBotonSaltar;

    public Titulo (String strTitulo) {

        // se cargan las imagenes
        URL urlFondo = this.getClass().getResource("background1.jpg");
        imgFondo = Toolkit.getDefaultToolkit().getImage(urlFondo);
        
        URL urlBotonSaltar = this.getClass().getResource("btn_saltar.png");
        imgBotonSaltar = Toolkit.getDefaultToolkit().getImage(urlBotonSaltar);
        
        btnBotonSaltar = new Boton(0 , 0 /*this.getWidth() - imgBotonSaltar.getWidth(this),
           this.getHeight() - imgBotonSaltar.getHeight(this)*/ , getWidth(), 
                getHeight(),  Toolkit.getDefaultToolkit().getImage(urlBotonSaltar));

        // el acomodo de los elementos en el panel es libre
       // panel.setLayout(null);
        // configuracion del boton
       // button.setBounds(400, 400, 100, 100);
       // panel.add(button); // se aniade el boton al panel
       //  add(panel); // se aniade el panel al frame
        // configuracion del frame
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(strTitulo);
        // se enlaza al boton el listener correspondiente
        button.addActionListener(new SiguienteFrame());
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
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
        
       //   graDibujo.drawImage(imgFondo, 0, 0, this); // imagen del conejo
          btnBotonSaltar.paint(graDibujo, this); 
          graDibujo.drawImage(imgBotonSaltar, this.getWidth() - imgBotonSaltar.getWidth(this),
           this.getHeight() - imgBotonSaltar.getHeight(this), this);
          
           
    }
    
    
       
    public void mousePressed(MouseEvent mseEvent) { 
        //Si hay interseccion entre el planeta y el mouse
        if ( btnBotonSaltar.intersecta(mseEvent.getX(), mseEvent.getY())) {
            System.out.println("si jaló");
// se abre una segunda vetana
            new secondFrame("Second window");
            // se cierra el frame presente
            Titulo.this.setVisible(false);
            Titulo.this.dispose();           

// bClick = true; //prendo la booleana
        
        }
       
    }
}
