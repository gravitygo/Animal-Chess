/**This class is what a player consist of and what a player can do.
 	@author Chyle Andrei Lee
 */
public class Player{
	/**This constructor creates a player object which will sets the player Red if o is true, Blue otherwise.
		@param o The player
	*/
	public Player(boolean o){
		PLAYER = o;
		if(o){
			DEN_X=0;
			DEN_Y=3;
		}else{
			DEN_X=8;
			DEN_Y=3;
		}
		/*======================================
		Create the animal objects for player
		======================================*/
		animal[0] = new Mouse 	(PLAYER, DEN_X, DEN_Y);
		animal[1] = new Cat 	(PLAYER, DEN_X, DEN_Y);
		animal[2] = new Wolf 	(PLAYER, DEN_X, DEN_Y);
		animal[3] = new Dog 	(PLAYER, DEN_X, DEN_Y);
		animal[4] = new Leopard	(PLAYER, DEN_X, DEN_Y);
		animal[5] = new Tiger 	(PLAYER, DEN_X, DEN_Y);
		animal[6] = new Lion 	(PLAYER, DEN_X, DEN_Y);
		animal[7] = new Elephant(PLAYER, DEN_X, DEN_Y);
	}

	/**Gets the X coordinate of the ally Den
		@return ally den x coordinate
	*/
	public int getDenX(){
		return DEN_X;
	}

	/**Gets the Y coordinate of the ally Den
		@return ally den y coordinate
	*/
	public int getDenY(){
		return DEN_Y;
	}

	/**Gets the color of the player
		@return the color of the player
	*/
	public boolean getPlayer(){
		return PLAYER;
	}

	/**Ally den x coordinate.*/
	private final int DEN_X;

	/**Ally den y coordinate.*/
	private final int DEN_Y;

	/**Array of all the animals*/
	public Animal[] animal = new Animal[8];

	/**The player(Color)*/
	protected final boolean PLAYER;
}