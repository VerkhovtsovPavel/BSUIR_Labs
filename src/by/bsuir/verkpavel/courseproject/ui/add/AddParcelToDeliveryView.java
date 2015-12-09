package by.bsuir.verkpavel.courseproject.ui.add;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.entity.Delivery;
import by.bsuir.verkpavel.courseproject.dao.entity.Parcel;
import by.bsuir.verkpavel.courseproject.dao.entity.ParcelM2MDelivery;

public class AddParcelToDeliveryView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;
    private JComboBox<String> parcelComboBox;
    private JComboBox<String> deliveryComboBox;
    private List<Parcel> parcels;
    private List<Delivery> deliveries;
    private boolean isDelete;

    public void showView() {
        this.setSize(445, 200);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);
    }

    public AddParcelToDeliveryView(boolean isDelete) {
        //TODO Change logic list filing
        this.isDelete = isDelete;
        setResizable(false);
        configureDefaultLayot();
        fillComboBoxes();
    }

    private void configureDefaultLayot() {
        setTitle("Добавление посылки в доставку");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
        
        JLabel parcelLabel = new JLabel("Посылка");
        parcelLabel.setBounds(24, 21, 114, 14);
        mainPanel.add(parcelLabel);
        
        parcelComboBox = new JComboBox<String>();
        parcelComboBox.setBounds(172, 15, 255, 25);
        mainPanel.add(parcelComboBox);
        
        JLabel deliveryLabel = new JLabel("Доставка");
        deliveryLabel.setBounds(24, 70, 114, 14);
        mainPanel.add(deliveryLabel);
        
        deliveryComboBox =  new JComboBox<String>();
        deliveryComboBox.setBounds(172, 64, 255, 25);
        mainPanel.add(deliveryComboBox);
       
        
        JButton submitBtn = new JButton(isDelete ? "Удалить" : "Добавить");
        submitBtn.setBounds(158, 113, 93, 30);
        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ParcelM2MDelivery parcelM2MDelivery = new ParcelM2MDelivery();
                parcelM2MDelivery.setDelivery(deliveries.get(deliveryComboBox.getSelectedIndex()));
                parcelM2MDelivery.setParcel(parcels.get(parcelComboBox.getSelectedIndex()));
                if(isDelete){
                    DeliveryServiceDao.getInstance().deleteRecord(parcelM2MDelivery);
                }else{
                    DeliveryServiceDao.getInstance().addRecord(parcelM2MDelivery);
                }
            }
        });
        mainPanel.add(submitBtn);
    }
    
    private void fillComboBox(JComboBox<String> target, List<? extends Describable> source) {
        for (Describable item : source) {
            target.addItem(item.getDescription());
        }
    }

    private void fillComboBoxes() {
        parcels = DeliveryServiceDao.getInstance().getAllRecord(Parcel.class);
        deliveries = DeliveryServiceDao.getInstance().getAllRecord(Delivery.class);
        
        fillComboBox(parcelComboBox, parcels);
        fillComboBox(deliveryComboBox, deliveries);
    }
}
