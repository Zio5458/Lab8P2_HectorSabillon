
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;


public class Espera extends Thread{
   
    private int num;
    private JProgressBar pg;
    private int type;

    public Espera(int num, JProgressBar pg, int type) {
        this.num = num;
        this.pg = pg;
        this.pg.setMaximum(num * 10);
        this.type = type;
    }
    
    @Override
    public void run() {
        while(true){
            pg.setValue(pg.getValue() + 10);
            switch (type){
                case 0:
                    if (pg.getValue() == pg.getMaximum()){
                        System.out.println(pg.getValue());
                        System.out.println(pg.getMaximum());
                        JOptionPane.showMessageDialog(null, "Evento creado correctamente");
                        stop();
                    }
                    break;
            }
            try {
                Thread.sleep(num * 1000);
            } catch (InterruptedException ex) {
                
            }
        }        
    }
    
}