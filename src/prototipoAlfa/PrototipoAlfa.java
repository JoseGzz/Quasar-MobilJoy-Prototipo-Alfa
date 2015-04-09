
package prototipoAlfa;
/**
 * MenuPrincipal
 *
 * ????????????????????????????????????
 *
 * @author Fred Roblero Maldonado A01037070
 * @author Iván Alejandro Leal Cervantes A00815154
 * @version 1.0
 * @date ?2&11&2015
 */
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.JFrame;

/**
 *
 * @author AntonioM
 */
public class PrototipoAlfa extends JFrame implements Runnable, KeyListener {
    
    private Base basPrincipal;         // Objeto principal
    private Base basMalo;         // Objeto malo
    private Base basMalo1;         // Objeto malo
    private Base basMalo2;         // Objeto malo
    private int iControlImagenes = 0;
    private long lTimer;
    
    // BOTONES MENU PRINCIPAL
    private Base basJugar;         // Objeto malo
    private Base basInstrucciones;         // Objeto malo
    private Base basScores;         // Objeto malo
    private Base basCreditos;         // Objeto malo
    
    
    
    /* objetos para manejar el buffer del Applet y este no parpadee */
    private Image imaImagenApplet;   // Imagen a proyectar en Applet	
    private Graphics graGraficaApplet;  // Objeto grafico de la Imagen

    public PrototipoAlfa() { //MenuPrincipal 
        // hago el applet de un tamaño 500,500
        lTimer = 0;
        URL urlImagenPrincipal = this.getClass().getResource("principal.gif");

        // se crea el objeto para principal 
        basPrincipal = new Base(100, 200, 79 , 73,
                Toolkit.getDefaultToolkit().getImage(urlImagenPrincipal));

      

        // defino la imagen del malo
        URL urlImagenMalo = this.getClass().getResource("gato.gif");
        
        basMalo = new Base(550, 200, 69, 68,
                    Toolkit.getDefaultToolkit().getImage(urlImagenMalo));
        
        // defino la imagen del malo
        URL urlImagenMalo1 = this.getClass().getResource("gato.gif");
        
        basMalo1 = new Base(585, 270, 69, 68,
                    Toolkit.getDefaultToolkit().getImage(urlImagenMalo1));
        
        // defino la imagen del malo
        URL urlImagenMalo2 = this.getClass().getResource("gato.gif");
        
        basMalo2 = new Base(550, 120, 69, 68,
                    Toolkit.getDefaultToolkit().getImage(urlImagenMalo2));

    
        // BOTONES
        // jugar
        URL urlBotonJugar = this.getClass().getResource("btn_jugar.png");
        basJugar = new Base(50, 450, 260, 70,
                    Toolkit.getDefaultToolkit().getImage(urlBotonJugar));
        // instrucciones
        URL urlBotonInstrucciones = this.getClass().getResource("btn_Instrucciones.png");
        basInstrucciones = new Base(450, 400, 200, 50,
                    Toolkit.getDefaultToolkit().getImage(urlBotonInstrucciones));
        
        // scores
        URL urlBotonScores = this.getClass().getResource("btn_highscores.png");
        basScores = new Base(450, 460, 200, 50,
                    Toolkit.getDefaultToolkit().getImage(urlBotonScores));
        
        // creditos
        URL urlBotonCreditos = this.getClass().getResource("btn_creditos.png");
        basCreditos = new Base(450, 520, 200, 50,
                    Toolkit.getDefaultToolkit().getImage(urlBotonCreditos));
        
        // SEGUNDO DESPLIEGUE
        
        
        
        addKeyListener(this); //Añade Key Listener

        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();

    }

    /**
     * run
     *
     * Metodo sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, que contendrá las instrucciones de
     * nuestro juego.
     *
     */
    public void run() {
        /* mientras dure el juego, se actualizan posiciones de jugadores
         se checa si hubo colisiones para desaparecer jugadores o corregir
         movimientos y se vuelve a pintar todo
         */ 
        lTimer = System.currentTimeMillis();
        
            repaint();
            try {
                // El thread se duerme.
                Thread.sleep(20);
            } catch (InterruptedException iexError) {
                System.out.println("Hubo un error en el juego "
                        + iexError.toString());
            }
        
    } 

    /**
     * update
     *
     * Metodo sobrescrito de la clase <code>Applet</code>, heredado de la clase
     * Container.<P>
     * En este metodo lo que hace es actualizar el contenedor y define cuando
     * usar ahora el paint2
     *
     * @param graGrafico es el <code>objeto grafico</code> usado para dibujar.
     *
     */
    public void paint(Graphics graGrafico) { //UPDATE
        // Inicializan el DoubleBuffer
          long lTiempoActual = System.currentTimeMillis() - lTimer;
          
          lTimer += lTiempoActual;
          
        if (imaImagenApplet == null) {
            imaImagenApplet = createImage(this.getSize().width,
                    this.getSize().height);
            graGraficaApplet = imaImagenApplet.getGraphics();
        }

        if (iControlImagenes >= 500 && iControlImagenes <= 600){
            // Actualiza la imagen de fondo.
            URL urlImagenFondo = this.getClass().getResource("background1.jpg");
            Image imaImagenFondo = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
            graGraficaApplet.drawImage(imaImagenFondo, 0, 0, getWidth(), getHeight(), this);
            
        } else {
            // Actualiza la imagen de fondo.
            URL urlImagenFondo = this.getClass().getResource("imagen_fondo.gif");
            Image imaImagenFondo = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
            graGraficaApplet.drawImage(imaImagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
        // Actualiza el Foreground.
        graGraficaApplet.setColor(getForeground());
        paint2(graGraficaApplet);

        // Dibuja la imagen actualizada
        graGrafico.drawImage(imaImagenApplet, 0, 0, this);
    }

    /**
     * paint2
     *
     * Metodo sobrescrito de la clase <code>Applet</code>, heredado de la clase
     * Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia.
     *
     * @param graDibujo es el objeto de <code>Graphics</code> usado para
     * dibujar.
     *
     */
    public void paint2(Graphics graDibujo) {

        // si la imagen ya se cargo
        if (basPrincipal != null) {
            iControlImagenes ++;
            
            if (iControlImagenes >= 0 && iControlImagenes <= 100 ) {
                // MENU PRINCIPAL    
                    //Dibuja la imagen de principal en el Applet
                basPrincipal.paint(graDibujo, this);
                //Dibuja la imagen de malo en el Applet
                basMalo.paint(graDibujo, this);

                basJugar.paint(graDibujo, this);

                basInstrucciones.paint(graDibujo, this);
                basScores.paint(graDibujo, this);
                basCreditos.paint(graDibujo, this);   
                
                Font font = new Font ("Garamond", Font.BOLD , 48);
                graDibujo.setFont(font);
                graDibujo.drawString("TITULO DEL JUEGO", 100, 100);
                
            }
            
            if (iControlImagenes >= 100 && iControlImagenes <= 200) {
                // INSTRUCCIONES
                URL urlImagenFondo = this.getClass().getResource("instrucciones.jpg");
                Image imaImagenInstrucciones = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
                graGraficaApplet.drawImage(imaImagenInstrucciones, 0, 0, getWidth(), getHeight(), this);
            }
            
            if (iControlImagenes >= 200 && iControlImagenes <= 300) {
                // HIGH SCORE 
                URL urlImagenFondo = this.getClass().getResource("scores.jpg");
                Image imaImagenInstrucciones = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
                graGraficaApplet.drawImage(imaImagenInstrucciones, 0, 0, getWidth(), getHeight(), this);
            }

            
            if (iControlImagenes >= 300 && iControlImagenes <= 400) {
                // CREDITOS
              URL urlImagenFondo = this.getClass().getResource("creditos.jpg");
              Image imaImagenInstrucciones = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
              graGraficaApplet.drawImage(imaImagenInstrucciones, 0, 0, getWidth(), getHeight(), this);
            }
               
               
             if (iControlImagenes >= 400 && iControlImagenes <= 500) {
                 // JUEGO
                 basPrincipal.paint(graDibujo, this);
                 basMalo.paint(graDibujo, this);
                 basMalo1.paint(graDibujo, this);
                 basMalo2.paint(graDibujo, this);
            }
             
             if (iControlImagenes >= 500 && iControlImagenes <= 600) {
                 // PAUSA
                URL urlImagenPrincipal = this.getClass().getResource("bunny1.png");
                URL urlImagenMalo = this.getClass().getResource("cat1.png");
                
                basPrincipal.setImagen(Toolkit.getDefaultToolkit().getImage(urlImagenPrincipal));

                basMalo.setImagen(Toolkit.getDefaultToolkit().getImage(urlImagenMalo));
                basMalo1.setImagen(Toolkit.getDefaultToolkit().getImage(urlImagenMalo));
                basMalo2.setImagen(Toolkit.getDefaultToolkit().getImage(urlImagenMalo));
                
                 basPrincipal.paint(graDibujo, this);
                 basMalo.paint(graDibujo, this);
                 basMalo1.paint(graDibujo, this);
                 basMalo2.paint(graDibujo, this);
                
                URL urlImagenFondo = this.getClass().getResource("pausa.png");
                Image imaImagenInstrucciones = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
                graGraficaApplet.drawImage(imaImagenInstrucciones, 0, 0, getWidth(), getHeight(), this);
                
            }
             if (iControlImagenes >= 600 && iControlImagenes <= 700) {
             // NIVEL COMPLETADO
                URL urlImagenFondo = this.getClass().getResource("nivelCompletado.gif");
                Image imaImagenInstrucciones = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
                graGraficaApplet.drawImage(imaImagenInstrucciones, 0 , 0, getWidth(), getHeight(), this);
                
                
                URL urlImagenPrincipal = this.getClass().getResource("principal.gif");
                basPrincipal.setImagen(Toolkit.getDefaultToolkit().getImage(urlImagenPrincipal));
                
                basPrincipal.paint(graDibujo, this);
             }
                
               if (iControlImagenes >= 700 && iControlImagenes <= 800) {
                 // GAME OVER
                URL urlImagenPrincipal = this.getClass().getResource("bunny_dead.png");
                URL urlImagenMalo = this.getClass().getResource("cat1.png");
                
                basPrincipal.setImagen(Toolkit.getDefaultToolkit().getImage(urlImagenPrincipal));

                basMalo.setImagen(Toolkit.getDefaultToolkit().getImage(urlImagenMalo));
                basMalo1.setImagen(Toolkit.getDefaultToolkit().getImage(urlImagenMalo));
                basMalo2.setImagen(Toolkit.getDefaultToolkit().getImage(urlImagenMalo));
                
                 basPrincipal.paint(graDibujo, this);
                 basMalo.paint(graDibujo, this);
                 basMalo1.paint(graDibujo, this);
                 basMalo2.paint(graDibujo, this);
                
                 URL urlImagenFondo = this.getClass().getResource("game_over.png");
                Image imaImagenInstrucciones = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
                graGraficaApplet.drawImage(imaImagenInstrucciones, 0, 0, getWidth(), getHeight(), this);
                
            }
               
               if (iControlImagenes >= 800) {
                   this.dispose();
               }
             
            
            
        } // sino se ha cargado se dibuja un mensaje 
        else {
            //Da un mensaje mientras se carga el dibujo	
            graDibujo.drawString("No se cargo la imagen..", 20, 20);
        }
    }


    /**
     * keyPressed
     *
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al dejar presionada alguna
     * tecla.
     *
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al
     * presionar.
     *
     */
    public void keyPressed(KeyEvent keyEvent) {

    }

    /**
     * keyTyped
     *
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una tecla que
     * no es de accion.
     *
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al
     * presionar.
     *
     */
    public void keyTyped(KeyEvent keyEvent) {
        // no hay codigo pero se debe escribir el metodo
    }

    /**
     * keyReleased Metodo sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al soltar la tecla.
     *
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al soltar.
     *
     */
    @SuppressWarnings("empty-statement")
    public void keyReleased(KeyEvent keyEvent) {
       
    }

    public static void main(String[] args) {
        PrototipoAlfa Jframejuego = new PrototipoAlfa();
        Jframejuego.setVisible(true);
        Jframejuego.setTitle("Juego");
        Jframejuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframejuego.setSize(800, 600);
    }

}
