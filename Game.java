import java.util.*;

/** This class is where the the general game flow is stated.
 	@author Chyle Andrei Lee
*/

public class Game{
	/**Constructor for initializing the terrain and animal objects.*/
	public Game(){
		board = new Board();
		view= new Views(board, this, choices());
	}

	/** Initializes the random pattern of the cards in the first phase of the game
	 	@return The random pattern of the cards
	*/
	private ArrayList<Integer> choices(){
		Random r= new Random(System.currentTimeMillis()); 

		//Create an empty ArrayList for ranking
		ArrayList<Integer> choices = new ArrayList<Integer>();

		//local variable for player choices 
		int playerChoice1, playerChoice2; 

		//create a unique pattern using the random object for the ArrayList
		while(choices.size()<8){
			int temp = r.nextInt(8);
			if(!choices.contains(temp))
				choices.add(temp);
		}
		return choices;
	}

	/** Sets the color of the winner of the card picking and initializes the first one to move
		@param p1Greaterp2 this will be true if Player 1 has a greater ranking card than Player 2
		@param playerColor This is the chosen color of the winner.
	*/
	public void setFirstColor(boolean p1Greaterp2, boolean playerColor){
		if(p1Greaterp2){
			turn=1;
			players[0] = new Player(playerColor);
			players[1] = new Player(!playerColor);
		}else{
			turn=0;
			players[0] = new Player(!playerColor);
			players[1] = new Player(playerColor);
		}
	}

	/** Check if player has no more animals left
		@param p The player to be checked if there are animals alive left.
		@return true if no animals left, false if not
	*/
	private boolean noAnimalsLeft(Player p){
		for(int i=0; i<8; i++){
			if(p.animal[i]!=null && p.animal[i].getIsAlive())
				return false;
		}
		return true;
	}

	/** Checks if the board is in a state of a stalemate
	 	@return true if the board is stalemate, false otherwise.
	*/
	private boolean staleMate(){
		//Loop through all the animals of the player
		for(int i=0; i<players[turn].animal.length; i++){
			if(players[turn].animal[i].canMove(board.getTerrain())) return false; //not yet stalemate if there is still 1 animal that can move
		}
		return true;
	}

	/** Compile all the animals that are alive
	 	@param p the current player to move
	 	@return an array of animals that are alive
	*/
	private String[] availableAnimals(Player p){
		ArrayList<String> list = new ArrayList<String>();
		for(Animal a : p.animal){
			if(a.getIsAlive())
				list.add(a.getName());
		}
		return list.toArray(new String[list.size()]);
	}

	/** Gets the animal object of a given animal name
	 	@param aName the name of the animal.
	 	@return The animal with the name of the parameter or null if can't find it.
	*/
	public Animal getAnimal(String aName){
		for(Animal a : players[turn].animal){
			if(a.getName().equals(aName))
				return a;
		}
		return null;
	}

	/** Compile all the possible moves of an animal within an arraylist and returns it as a string array
	 @param aName the name of the animal
	 @return the list of possible moves
	*/
	public String[] availableMove(String aName){
		Animal a = getAnimal(aName);
		ArrayList<String> list = new ArrayList<String>();
		if(a.canMoveUp(board.getTerrain())) list.add("Up");
		if(a.canMoveDown(board.getTerrain())) list.add("Down");
		if(a.canMoveLeft(board.getTerrain())) list.add("Left");
		if(a.canMoveRight(board.getTerrain())) list.add("Right");

		return list.toArray(new String[list.size()]);
	}

	/** Checks if all animal are dead
		@return true if all animals are dead, false otherwise.
	*/
	private boolean allAnimalDead(){
		for(Animal a : players[turn].animal)
			if(a.getIsAlive()) return false;
		return true;
	}

	/** Moves the piece and updates the view
		@param animal the name of the animal to be moved
		@param move the move to be executed
	*/
	public void move(String animal, String move){
		if(move.equals("Up"))
			getAnimal(animal).moveUp(board);
		if(move.equals("Down"))
			getAnimal(animal).moveDown(board);
		if(move.equals("Left"))
			getAnimal(animal).moveLeft(board);
		if(move.equals("Right"))
			getAnimal(animal).moveRight(board);
		turn=(turn+1)%2;
		board.update(players);
		view.update(board, availableAnimals(players[turn]), turn, players[turn].getPlayer());
	}

	/** This method checks if the game has already have a winner
		@return true if the game already has a winner, false otherwise.
	 */
	public boolean isFinished(){
		for(Animal a : players[(turn+1) % 2].animal)
			if(a.getX()==players[turn].getDenX() && a.getY() == players[turn].getDenY())
				return true;
		if(allAnimalDead())
			return true;
		return false;
	}

	/**The start of the Game where it sets up the board and display it in the frame. */
	public void startGame(){
		board.update(players);
		view.update(board, availableAnimals(players[turn]), turn, players[turn].getPlayer());
	}

	/**Checks if the game is finished and wraps it up if it is.*/
	public void gamePhase(){
		if(isFinished() || staleMate()){
			view.gameDone(turn, !isFinished());
			
			s=null;
			board=null;
			players=null;
		}
	}


	/** The current board state */
	private Board board;
	
	/** Instance of players 1 and 2*/
	private Player[] players= new Player[2];
	
	/** Instance of Scanner*/
	private	Scanner s = new Scanner(System.in); 
	
	/**Determines who's turn the game currently is.*/
	private int turn;

	/** The frame view */ 
	private Views view;
}