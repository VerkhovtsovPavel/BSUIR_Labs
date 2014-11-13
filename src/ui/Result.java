package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import algorithms.Sorter;

public class Result extends JFrame {

	private static final long serialVersionUID = 6946609135675030780L;
	private static Sorter sorter;

	public Result() {
		configureDefaultLayot();
	}

	/**
	 * Launch the application.
	 */
	public static void create(Sorter sorter) {
		Result.sorter = sorter;
		initialaze();
	}

	private static void initialaze() {
		Result frame = new Result();
		frame.setSize(465, 140);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}

	private void configureDefaultLayot() {
		setResizable(false);
		setTitle("Result");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		JButton bestFitRecipeBtn = new JButton("Best fit recipes");
		bestFitRecipeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowRecipe.create("Best fit recipes", sorter.getFirstGeneration());
			}
		});
		bestFitRecipeBtn.setBounds(25, 11, 216, 23);
		mainPanel.add(bestFitRecipeBtn);

		JButton suitableRecipesBtn = new JButton("Suitable recipes");
		suitableRecipesBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowRecipe.create("Suitable recipes", sorter.getSecondGeneration());
			}
		});
		suitableRecipesBtn.setBounds(25, 51, 216, 23);
		mainPanel.add(suitableRecipesBtn);

		JButton recipesContainingIngredientsBtn = new JButton("Recipes containing ingredients");
		recipesContainingIngredientsBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowRecipe.create("Recipes containing ingredients", sorter.getThirdGenerartion());
			}
		});
		recipesContainingIngredientsBtn.setBounds(25, 94, 216, 23);
		mainPanel.add(recipesContainingIngredientsBtn);
	}

}
