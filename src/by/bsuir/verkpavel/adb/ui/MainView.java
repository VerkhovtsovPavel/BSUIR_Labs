package by.bsuir.verkpavel.adb.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.ui.client.ShowUsersView;
import by.bsuir.verkpavel.adb.ui.deposit.ShowDepositsView;

public class MainView extends JFrame {
	private static final long serialVersionUID = 2883993883146596569L;
	private JPanel mainPanel;

	private static void initialaze() {
		MainView frame = new MainView();
		frame.setSize(460, 310);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}

	public static void create() {
		initialaze();
	}

	private MainView() {
		configureDefaultLayot();
	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("Главная форма");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JButton clientsBtn = new JButton("Клиенты");
		clientsBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowUsersView.create();
				dispose();
			}
		});
		clientsBtn.setBounds(255, 106, 111, 23);
		mainPanel.add(clientsBtn);

		JButton depositButton = new JButton("Депозиты");
		depositButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowDepositsView.create();
				dispose();
			}
		});
		depositButton.setBounds(107, 107, 111, 23);
		mainPanel.add(depositButton);
	}
}
