
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;

public class Espera extends Thread {

    private int num;
    private JProgressBar pg;
    private int type;
    private DefaultTableModel m;
    private ArrayList<Cancion> c;
    private ArrayList<Artista> a;

    public Espera(int num, JProgressBar pg, int type) {
        this.num = num;
        this.pg = pg;
        this.pg.setMaximum(num * 10);
        this.type = type;
    }

    public Espera(int num, JProgressBar pg, int type, DefaultTableModel m, ArrayList<Cancion> c, ArrayList<Artista> a) {
        this.num = num;
        this.pg = pg;
        this.type = type;
        this.m = m;
        this.c = c;
        this.a = a;
    }

    public Espera(int num, int type) {
        this.num = num;
        this.type = type;
    }

    @Override
    public void run() {
        if (type != 3) {
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
                        case 3:
                            if (pg.getValue() == pg.getMaximum()) {
                                Object[] row = new Object[3];
                                for (int i = 0; i < c.size(); i++) {
                                    row[0] = c.get(i).toString();
                                    row[1] = c.get(i).getDuracion();
                                    row[2] = findArtist(c.get(i));
                                    m.addRow(row);
                                }
                                pg.setValue(0);
                            }
                            break;
                    }

                } else {
                    System.out.println("Syncing...");
                }
                try {
                    Thread.sleep(num * 100);
                    if (pg.getValue() == pg.getMaximum()) {
                        break;
                    }
                } catch (InterruptedException ex) {

                }
            }
        } else {
            for (int i = 0; i < c.size(); i++) {
                while (true) {
                    pg.setValue(pg.getValue() + 10);
                    if (pg.getValue() == pg.getMaximum()) {
                        Object[] row = new Object[3];
                        row[0] = c.get(i).toString();
                        row[1] = c.get(i).getDuracion();
                        row[2] = findArtist(c.get(i));
                        m.addRow(row);
                        
                    }
                    try {
                        Thread.sleep(num * 100);
                        if (pg.getValue() == pg.getMaximum()) {
                            pg.setValue(0);
                            break;
                        }
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }
    }

    public Artista findArtist(Cancion c) {
        Artista ar;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getCanciones().contains(c)) {
                ar = a.get(i);
                return ar;
            }
        }
        return null;
    }

}
