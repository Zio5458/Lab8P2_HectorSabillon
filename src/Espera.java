
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Espera extends Thread {

    private int num;
    private JProgressBar pg;
    private int type;

    public Espera(int num, JProgressBar pg, int type) {
        this.num = num;
        this.pg = pg;
        this.pg.setMaximum(num * 10);
        this.type = type;
    }

    public Espera(int num, int type) {
        this.num = num;
        this.type = type;
    }

    @Override
    public void run() {
        while (true) {
            if (type != 2) {
                pg.setValue(pg.getValue() + 10);
                switch (type) {
                    case 0:
                        if (pg.getValue() == pg.getMaximum()) {
                            JOptionPane.showMessageDialog(null, "Evento creado correctamente");
                            stop();
                        }
                        break;
                    case 1:
                        if (pg.getValue() == pg.getMaximum()) {
                            JOptionPane.showMessageDialog(null, "Registrado exitosamente");
                            stop();
                        }
                        break;
                }

            } else {
                System.out.println("Syncing...");
            }
            try {
                Thread.sleep(num * 100);
            } catch (InterruptedException ex) {

            }
        }
    }

}
