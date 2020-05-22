import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import java.util.Iterator;

public class AllTimes extends JFrame implements Runnable {

    private JLabel label;
    private JComboBox<String> cbb = new JComboBox<String>();
    private Thread thread = new Thread(this);

    public AllTimes() {
        super("All Times");
        this.setResizable(false);
        this.setSize(400, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2, 1));

        Font font = new Font("Arial", Font.BOLD, 25);

        Iterator<String> i = ZoneId.getAvailableZoneIds().iterator();
        cbb.setFont(font);
        while (i.hasNext()) {
            cbb.addItem(i.next());
        }

        label = new JLabel("-", SwingConstants.CENTER);
        label.setFont(font);

        this.add(cbb);
        this.add(label);
        thread.start();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new AllTimes();
    }

    @Override
    public void run() {
        while(true){
            String selectedItem = (String) cbb.getSelectedItem();
            ZoneId zoneid = ZoneId.of(selectedItem);
            ZonedDateTime zdt = ZonedDateTime.now(zoneid);
            label.setText(zdt.toLocalTime().toString().substring(0, 8));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}