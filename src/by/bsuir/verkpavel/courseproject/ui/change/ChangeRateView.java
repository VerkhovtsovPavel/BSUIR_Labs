package by.bsuir.verkpavel.courseproject.ui.change;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.apache.log4j.Logger;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.Rate;
import by.bsuir.verkpavel.courseproject.resources.ProjectProperties;

public class ChangeRateView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private static Logger log = Logger.getLogger(ChangeRateView.class);
    
    private JPanel mainPanel;
    private JSpinner heightSpinner;
    private JSpinner depthSpinner;
    private JSpinner weigthSpinner;
    private JSpinner widthSpinner;
    private Rate lastRate;


    public void showView() {
        this.setSize(445, 200);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);
    }

    public ChangeRateView() {
        setResizable(false);
        loadRates();
        configureDefaultLayot();
    }

    private void configureDefaultLayot() {
        setTitle("Изменение тарифов");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        JLabel parcelLabel = new JLabel("Посылка");
        parcelLabel.setBounds(24, 21, 114, 14);
        mainPanel.add(parcelLabel);

        JLabel deliveryLabel = new JLabel("Доставка");
        deliveryLabel.setBounds(24, 70, 114, 14);
        mainPanel.add(deliveryLabel);

        JButton submitBtn = new JButton("Сохранить");
        submitBtn.setBounds(158, 113, 93, 30);
        submitBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int height = (int) heightSpinner.getValue();
                int weigth = (int) weigthSpinner.getValue();
                int depth = (int) depthSpinner.getValue();
                int width = (int) widthSpinner.getValue();
                
                Rate rate = new Rate();
                rate.setHeigth(height);
                rate.setDepth(depth);
                rate.setWeigth(weigth);
                rate.setWidth(width);
                
                DeliveryServiceDao.getInstance().addRecord(rate);
            }
        });
        mainPanel.add(submitBtn);
        
        heightSpinner = new JSpinner();
        heightSpinner.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
        heightSpinner.setBounds(60, 111, 58, 40);
        heightSpinner.setValue(lastRate.getHeigth());
        mainPanel.add(heightSpinner);

        depthSpinner = new JSpinner();
        depthSpinner.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
        depthSpinner.setBounds(349, 111, 58, 40);
        depthSpinner.setValue(lastRate.getDepth());
        mainPanel.add(depthSpinner);

        weigthSpinner = new JSpinner();
        weigthSpinner.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
        weigthSpinner.setBounds(211, 111, 58, 40);
        weigthSpinner.setValue(lastRate.getWeigth());
        mainPanel.add(weigthSpinner);

        widthSpinner = new JSpinner();
        widthSpinner.setModel(new SpinnerNumberModel(1, 1, 250000, 1));
        widthSpinner.setBounds(513, 108, 102, 40);
        widthSpinner.setValue(lastRate.getWidth());
        mainPanel.add(widthSpinner);
    }

    private void loadRates() {
        List<Rate> rateList = DeliveryServiceDao.getInstance().getAllRecord(Rate.class);
        if (rateList.isEmpty()) {
            lastRate = ProjectProperties.getDefaultRate();
            log.warn("Using default rates");
        } else {
            lastRate = rateList.get(rateList.size() - 1);
        }
    }

}
