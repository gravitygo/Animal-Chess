import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

/** This class is where the the view is generated.
	@author Chyle Andrei Lee
*/
@SuppressWarnings("serial")
public class Views extends JFrame{

	/**This constructor sets up the view where the card game is placed by default
	 	@param b the current state of the board
	 	@param c the controller(Game) object for connections
	 	@param choices the randomized setup of the cards
	*/
	public Views(Board b, Game c, ArrayList<Integer> choices){
		//Instantiate and change the name of the JFrame
		super("Animal Chess - Lee");

		//Connects with the controller
		controller = c;

		//Grab the current content
		contents = getContentPane();

		//Instantiate the other containers
		board = new Container();
		buttons = new Container();
		goFirst = new Container();

		//Set up the layouts of the containers
		contents.setLayout(new BorderLayout());
		board.setLayout(new GridLayout(7,9));
		buttons.setLayout(new GridLayout(1,3));
		goFirst.setLayout(new GridLayout(2,4));

		//add action listeners
		animal.addActionListener(new PickAnimal());
		nextPlayer.addActionListener(new ButtonHandler());
		moves.addActionListener(new PickMove());

		//initialize the board
		for(int i=0; i<7; i++){
			for(int j=0; j<9; j++){
				terrain[j][i]= new JButton(b.getTerrain()[j][i].getIcon());
				terrain[j][i].setDisabledIcon(b.getTerrain()[j][i].getIcon());
				terrain[j][i].setBorderPainted(false);
				terrain[j][i].setEnabled(false);
				terrain[j][i].add(new JLabel());
				board.add(terrain[j][i]);
			}
		}
		animal.setForeground(Color.green);

		//Add color to the JLabel in which states the current phase of the game
		playerMove.setForeground(Color.green);
		playerMove.setOpaque(true);
		playerMove.setBackground(Color.white);

		//runs the code for the setup of the card game
		goFirstSetUp(choices);
		
		//turns off the next turn button
		nextPlayer.setEnabled(false);

		//add all the buttons to be used in the button panel
		buttons.add(animal);
		buttons.add(moves);
		buttons.add(nextPlayer);

		//add the current necesary panels
		contents.add(playerMove, BorderLayout.NORTH);
		contents.add(buttons, BorderLayout.SOUTH);
		// contents.add(board, BorderLayout.CENTER);
		contents.add(goFirst, BorderLayout.CENTER);

		//set up the frame
		setBounds(0, 0, 909, 776);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**Updates the view, from the board to the labels and JComboBoxes and buttons
	 	@param b the current updated board
	 	@param animalList the list of animals that are alive
	 	@param currentPlayer the current player's code name
	 	@param currentColor the current player's color
	*/
	public void update(Board b, String[] animalList, int currentPlayer,boolean currentColor){
		for(int i=0; i<7; i++){
			for(int j=0; j<9; j++){
				if(b.getTerrain()[j][i].animal!=null)
					((JLabel)terrain[j][i].getComponents()[0]).setIcon(b.getTerrain()[j][i].animal.getIcon());
				else
					((JLabel)terrain[j][i].getComponents()[0]).setIcon(null);
			}
		}
		if(currentPlayer==0)
			playerMove.setText("Player "+ 2 + " to Move");
		else 
			playerMove.setText("Player "+ currentPlayer+ " to Move");
		animal.setBackground(getColor(currentColor));
		playerMove.setBackground(getColor(currentColor));
		updateAnimalSelection(animalList);
		repaint();
	}

	/**Gets the color
	 	@param currentColor the color in a boolean state, red if true, blue otherwise
	 	@return red if currentColor is true, blue otherwise
	*/
	private Color getColor(boolean currentColor){
		if(currentColor)
			return Color.red;
		return Color.blue;
	}

	/**The set up of the card picking phase
		@param choices the randomized positions of the cards
	*/
	private void goFirstSetUp(ArrayList<Integer> choices){
		for(int i=0; i<choices.size(); i++){
			cards[i]=new JButton();
			cards[i].addActionListener(new PickCard(choices));
			goFirst.add(cards[i]);
		}
	}

	/** Updates the animal JComboBox
	 	@param animalList the array of animals that are alive
	*/
	private void updateAnimalSelection(String[] animalList){
		if(animalList.length > 0){
				animal.setModel(new DefaultComboBoxModel<String>(animalList));
				updateMoveSelection(controller.availableMove(animal.getSelectedItem().toString()));
				nextPlayer.setEnabled(true);
		}else nextPlayer.setEnabled(false);
	}

	/** Updates the move JComboBox
	 	@param moveList the array of possible moves that can be executed
	*/
	public void updateMoveSelection(String[] moveList){
		if(moveList.length > 0){
			moves.setModel(new DefaultComboBoxModel<String>(moveList));
			nextPlayer.setEnabled(true);
		}else{
			moves.setModel(new DefaultComboBoxModel<String>(moveList));
			nextPlayer.setEnabled(false);
		}
		repaint();
	}

	/** Procedures if the game is done
	 	@param player the current player
	 	@param isStaleMate checks if the board ended with a stalemate
	*/
	public void gameDone(int player, boolean isStaleMate){
		String message;
		if(isStaleMate)
			message="Stale Mate";
		else
			if(player==0)
				message="Player 1 Won!";
			else
				message="Player 2 Won!";
		JOptionPane.showMessageDialog(null, message);
		playerMove.setText(message);
		playerMove.setBackground(Color.white);
		Component[] component = buttons.getComponents();
		for(Component c : component)
			c.setEnabled(false);
		repaint();
	}

	/**Get the animal name using its rank - 1
	 	@param i the rank reduced by 1
	 	@return the name of the animal, returns null if can't find it.
	*/
	private String getAnimalName(int i){
		switch(i){
			case 0: return "Mouse";
			case 1: return "Cat";
			case 2: return "Wolf";
			case 3: return "Dog";
			case 4: return "Leopard";
			case 5: return "Tiger";
			case 6: return "Lion";
			case 7: return "Elephant"; 
		}
		return "";
	}

	/**This object is to listen and execute a task when a swing object is connected with this(Animal JComboBox)*/
	private class PickAnimal implements ActionListener{
		public void actionPerformed(ActionEvent e){
			updateMoveSelection(controller.availableMove(animal.getSelectedItem().toString()));
			repaint();
		}
	}

	/**This object is to listen and execute a task when a swing object is connected with this(Move JComboBox)*/
	private class PickMove implements ActionListener{
		public void actionPerformed(ActionEvent e){
			nextPlayer.setEnabled(true);
			repaint();
		}
	}

	/**This object is to listen and execute a task when a swing object is connected with this(Card JButtons)*/
	private class PickCard implements ActionListener{
		/**Constructor to initialize the choices and who to pick
			@param c the randomized choices for the cards
		*/
		public PickCard(ArrayList<Integer> c){
			choices=c;
			whoWillPick=0;
		}

		/**This is the task to be executed when the object performed an action
			@param e the place in which the action takes place
		*/
		public void actionPerformed(ActionEvent e){
			//Gets the source of the action
			Object a = e.getSource();

			//loop through every card button to find the source
			for(int i=0; i<choices.size(); i++){
				if(cards[i].equals(a)){
					//Show the animal of the chosen card and disables it
					cards[i].setText(getAnimalName(choices.get(i)));
					cards[i].setEnabled(false);

					whoWillPick++;

					//Checks if the second player has already picked a card
					if(whoWillPick==2){
						int n;
						player2choice=choices.get(i);
						String[] buttons = { "Blue", "Red"};

						//Checks if player 1 has a greater ranking animal than player 2
						if(player1choice>player2choice){
							//shows a message dialog in which you choose the color you want, the default color will be blue
							n = JOptionPane.showOptionDialog(null,
								    getAnimalName(player1choice) +" is more powerful than "+ getAnimalName(player2choice),
								    "Player 1 will pick color",
								    JOptionPane.YES_NO_OPTION,
								    JOptionPane.QUESTION_MESSAGE,
								    null,     //do not use a custom Icon
								    buttons,  //the titles of buttons
								    buttons[0]); //default button title
						}
						else{
							//shows a message dialog in which you choose the color you want, the default color will be blue
							n = JOptionPane.showOptionDialog(null,
								    getAnimalName(player1choice) +" is less powerful than "+ getAnimalName(player2choice),
								    "Player 2 will pick color",
								    JOptionPane.YES_NO_OPTION,
								    JOptionPane.QUESTION_MESSAGE,
								    null,     //do not use a custom Icon
								    buttons,  //the titles of buttons
								    buttons[0]); //default button title
						}
						//If the player chooses blue, or run it by default it sets playerColor to true, false otherwise
						if(n==0) playerColor=true;
						else playerColor=false;

						//Sets the colors and the turns in the controller
						controller.setFirstColor(player1choice>player2choice,playerColor);

						// Removes the card picking panel and replace it with the board panel
						contents.remove(goFirst);
						contents.add(board);
						goFirst=null;

						//Starts the game from the controller
						controller.startGame();
					}else{
						player1choice=choices.get(i);
					}
				}
			}
			repaint();
		}
		/**Current person who picked a card*/
		private static int whoWillPick;
		
		/**The rank of animal player 1 chooses*/
		private static int player1choice;

		/**The rank of animal player 2 chooses*/
		private static int player2choice;
		
		/**Sets the color of the player. Blue if true, Red otherwise*/
		private static boolean playerColor;
		
		/**The randomized choices*/
		private ArrayList<Integer> choices;
	}

	/**This object is to listen and execute a task when a swing object is connected with this(Execute Move JButton)*/
	private class ButtonHandler implements ActionListener{
		/**This is the task to be executed when the object performed an action
			@param e the place in which the action takes place
		*/
		public void actionPerformed(ActionEvent e){
			controller.move(animal.getSelectedItem().toString(), moves.getSelectedItem().toString());
			controller.gamePhase();
		}
	}

	/**The controller of the game*/
	private Game controller;

	//contents of the frame
	private Container contents;
	private Container board;
	private Container buttons;
	private Container goFirst;
	//Components
	private JButton cards[] = new JButton[8];
	private JButton[][] terrain = new JButton[9][7];
	private JButton nextPlayer = new JButton("Next Player");
	private JLabel playerMove = new JLabel("Card Selection", SwingConstants.CENTER);
	private JComboBox<String> animal = new JComboBox<String>();
	private JComboBox<String> moves = new JComboBox<String>();
}