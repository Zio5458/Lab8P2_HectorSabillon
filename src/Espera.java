
import javax.swing.JProgressBar;


public class Espera extends Thread{
   
    private int num;
    private JProgressBar pg;

    public Espera(int num, JProgressBar pg) {
        this.num = num;
        this.pg = pg;
        this.pg.setMaximum(100);
    }
    
    @Override
    public void run() {
        while(true){
            pg.setValue(pg.getValue() + 10);
            try {
                Thread.sleep(num * 100);
            } catch (InterruptedException ex) {
                
            }
        }        
    }
    
}
