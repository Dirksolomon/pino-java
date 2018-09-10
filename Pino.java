    import java.awt.Color;
    import java.awt.Component;
    import java.awt.GridLayout;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JOptionPane;
     
     
    public class Pino  extends JFrame implements KeyListener {
		//Muuttujat
            int kerta = 1;
            static double aika = 200;
            static int viim = 0;
            static int m = 10;
            static int n = 20;
            JButton b[][];
            static int pitka[] = {5,5};
            static int taso = 19;
            static int sij[] = {0,0};
            static boolean paina = false;
            static boolean eteen = true;
            static boolean alku = true;
            public static void main (String[] args){
                    new Pino();
            }
           
            public Pino(){
                    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    b = new JButton [m][n];
					
            setLayout(new GridLayout(n,m));
			
            for (int y = 0;y<n;y++){
                for (int x = 0;x<m;x++){
                        b[x][y] = new JButton(" ");
                        b[x][y].setBackground(Color.black);
                        add(b[x][y]);
                        b[x][y].setEnabled(true);
                }
        }
            setFocusable(true);
			
            addKeyListener(this);
			
            pack();
			
            setVisible(true);
			
            go();
            }
           
            public void go(){
                    int tmp = 0;
                    Component temporaryLostComponent = null;
                    do{
                    if (eteen == true){
                            eteen();
                    } else {
                            back();
                    }
                    if (sij[1] == 10-pitka[1]){
                            eteen = false;
                    } else if (sij[1] == 0){
                            eteen = true;
                    }
                    draw();
                    try {
                            Thread.sleep((long) aika);
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
     
                    }while(paina == false);
                    if (taso>12){
                            aika= 150-(kerta*kerta*2-kerta);
                    } else {
                            aika = aika - 2.2;
                    }
                    kerta++;
                    taso--;
                    paina = false;
                    tmp = check();
                    pitka[0] = pitka[1];
                    pitka[1] = tmp;
					
                    if (taso == -1){
                            JOptionPane.showMessageDialog(temporaryLostComponent, "Voitit pelin!");
                    }
                    if (pitka[1] <= 0){   
                            JOptionPane.showMessageDialog(temporaryLostComponent, "Hävisit pelin, pääsit riville "+(18-taso)+"!");
                System.exit(0);
                    }
                    viim = sij[1];
                    alku = false;
                    go();
            }
			
            public int check(){
                    if (alku == true){
                            return pitka[1];
                    } else if (viim<sij[1]){
                            if (sij[1]+pitka[1]-1 <= viim+pitka[0]-1){
                                    return pitka[1];
                            } else {
                                    return pitka[1]-Math.abs((sij[1]+pitka[1])-(viim+pitka[0]));
                            }
                    } else if (viim>sij[1]){
                            return pitka[1]-Math.abs(sij[1]-viim);
                    } else {
                            return pitka[1];
                    }
            }
			//Liikuttaa
            public void eteen(){
                    sij[0] = sij[1];
                    sij[1]++;
            }
           
            public void back(){
                    sij[0] = sij[1];
                    sij[1]--;
            }
           //Piirtaa ruudun
            public void draw(){
                    for (int x = 0;x<pitka[1];x++){
                            b[x+sij[0]][taso].setBackground(Color.black);
                           
                            }
                    for (int x = 0;x<pitka[1];x++){
                            b[x+sij[1]][taso].setBackground(Color.white);
                            }
            }
     
            @Override
            public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE){
                            paina = true;
                            }
                   
            }
     
            @Override
            public void keyReleased(KeyEvent arg0) {
                   
            }
     
            @Override
            public void keyTyped(KeyEvent arg0) {
                   
            }
    }