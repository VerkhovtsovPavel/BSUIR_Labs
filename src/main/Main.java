package main;

import java.io.IOException;

import ui.forms.MainForm;

public class Main {

	public static void main(String[] args) throws IOException {
		MainForm.create();
	}
}

// ////////////////Graph element sample/////////////////////////
/*
 * RoundButton temp = new RoundButton("Рецепт №1");
 * temp.setBounds(50,50,100,100); temp.setBackground(Color.white);
 * temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowRecipe.create(new ArrayList<Recipe>(i));
			}
		});
 * mainPanel.add(temp);
 */
// /////////////////////////////////////////////////////////////