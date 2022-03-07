/** This class is a general form of what an animal is like and what it should do
	@author Chyle Andrei Lee
*/
import javax.swing.*;
abstract class Animal{

	/**This constructor creates an object with the owner, rank of the animal, name of the animal, x and y coordinate of the ally den and the icon of the animal
		@param player The owner of the animal
		@param rank The rank of the animal
		@param name The name of the animal (formated for easier display in the board)
		@param denX The x coordinate of ally den
		@param denY The y coordinate of ally den
	*/
	protected Animal(boolean player, int rank, String name, int denX, int denY, String path){
		char redBlue='B';
		if(player){
			redBlue='R';
		}
		ICON = new ImageIcon("res/"+redBlue+"/"+path);
		OWNER = player;
		RANK = rank;
		NAME = name;
		ALLY_DEN_X=denX;
		ALLY_DEN_Y=denY;
		isAlive = true;
		isTrapped=false;
	}

	/**Get the x coordinate of the animal
		@return the x coordinate of the animal
	*/ 
	public int getX(){
		return x;
	}

	/**Get the y coordinate of the animal
	 	@return the y coordinate of the animal
	*/
	public int getY(){
		return y;
	}

	/**Get the owner of the animal
		@return the owner of the animal
	*/
	public boolean getOwner(){
		return OWNER;
	}

	/**Get the rank of the animal
		@return the rank of the animal
	*/
	public int getRank(){
		return RANK;
	}

	/**Moves the animal up if returns true, and returns false otherwise.
		@param b The current state of the board
	*/ 
	public void moveUp(Board b){
		Terrain[][] t = b.getTerrain();
		if(canMoveUp(t)){
			y--;
			if(t[x][y].getTerrain()==2)isTrapped=true;
			else isTrapped=false;
			
			if(t[x][y].animal!=null)
				eat(t[x][y].animal);
			b.setTerrain(t);
		}
	} 

	/**Moves the animal down if returns true, and returns false otherwise.
		@param b The current state of the board
	*/ 
	public void moveDown(Board b){
		Terrain[][] t = b.getTerrain();
		if(canMoveDown(t)){
			y++;
			if(t[x][y].getTerrain()==2)isTrapped=true;
			else isTrapped=false;
			
			if(t[x][y].animal!=null)
				eat(t[x][y].animal);
			b.setTerrain(t);
		}
	}

	/**Moves the animal right if returns true, and returns false otherwise.
		@param b The current state of the board
	*/ 
	public void moveRight(Board b){
		Terrain[][] t = b.getTerrain();
		if(canMoveRight(t)){
			x++;
			if(t[x][y].getTerrain()==2)isTrapped=true;
			else isTrapped=false;

			if(t[x][y].animal!=null)
				eat(t[x][y].animal);
			b.setTerrain(t);
		}
	}

	/**Moves the animal left if returns true, and returns false otherwise.
		@param b The current state of the board
	*/ 
	public void moveLeft(Board b){
		Terrain[][] t = b.getTerrain();
		if(canMoveLeft(t)){
			x--;
			if(t[x][y].getTerrain()==2) 
				isTrapped=true;
			else isTrapped=false;

			if(t[x][y].animal!=null)
				eat(t[x][y].animal);
			b.setTerrain(t);
		}
	}	

	/**Method for checking if there is a possible move.
		@param t the current state of the terrain
		@return true if there is a possible move, false otherwise
	*/
	public boolean canMove(Terrain[][] t){
		if(!isAlive) return false; //can't move if dead
		return 	canMoveUp(t)||
				canMoveDown(t)||
				canMoveLeft(t)||
				canMoveRight(t);
	}

	/**Method for checking if the animal can move left on the board.
		@param t The current state of the terrain
		@return true if the animal can successfully moved up.
	*/ 
	public boolean canMoveLeft(Terrain[][] t){
		//guard to not go out of bounds
		if(x == 0)return false;	

		//check if next terrain is water
		if(t[x-1][y].getTerrain() == 1)	return false; 

		//check if next terrain is ally den
		if(x-1 == ALLY_DEN_X && y == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x-1][y].animal!=null && !canEat(t[x-1][y].animal))	return false; 

		return true;
	}

	/**Method for checking if the animal can move right on the board.
		@param t The current state of the terrain
		@return true if the animal can successfully moved right.
	*/ 
	public boolean canMoveRight(Terrain[][] t){
		//guard to not go out of bounds
		if(x == 8)return false;	

		//check if next terrain is water
		if(t[x+1][y].getTerrain() == 1)	return false; 
		
		//check if next terrain is ally den
		if(x+1 == ALLY_DEN_X && y == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x+1][y].animal!=null && !canEat(t[x+1][y].animal))	return false;

		return true;
	}

	/**Method for checking if the animal can move down on the board.
		@param t The current state of the terrain
		@return true if the animal can successfully moved down.
	*/ 
	public boolean canMoveDown(Terrain[][] t){
		//guard to not go out of bounds
		if(y == 6)return false;	

		//check if next terrain is water
		if(t[x][y+1].getTerrain() == 1)	return false; 

		//check if next terrain is ally den
		if(x == ALLY_DEN_X && y + 1 == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x][y+1].animal!=null && !canEat(t[x][y+1].animal))	return false; 

		return true;
	}

	/**Method for checking if the animal can move up on the board.
		@param t The current state of the terrain
		@return true if the animal can successfully moved up.
	*/ 
	public boolean canMoveUp(Terrain[][] t){
		//guard to not go out of bounds
		if(y == 0)return false;	

		//check if next terrain is water
		if(t[x][y-1].getTerrain() == 1)	return false; 

		//check if next terrain is ally den
		if(x == ALLY_DEN_X && y - 1 == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x][y-1].animal!=null && !canEat(t[x][y-1].animal))	return false; 
		
		return true;
	}

	/**Method for checking if the animal can eat an animal.
		@param a The current animal to be eaten
		@return true if the animal can successfully be eaten.
	*/ 
	public boolean canEat(Animal a){
		return OWNER!=a.getOwner() && (RANK >= a.getRank()||a.getIsTrapped());
	}

	/**Method to set animal dead if eaten.*/
	public void gotEaten(){
		isAlive = false;
	}

	/**Method to eat an animal.
		@param a The animal to be eaten*/
	public void eat(Animal a){
		a.gotEaten();
	}

	/**Method to get trap status.
		@return the trap status
	*/
	public boolean getIsTrapped(){
		return isTrapped;
	}

	/**Method to get the name of an animal.
		@return the animal name
	*/
	public String getName(){
		return NAME;
	}

	/**Method to get the alive status of an animal.
		@return the animal alive status
	*/
	public boolean getIsAlive(){
		return isAlive;
	}

	/**Method to get the icon of this animnal.
		@return the icon of this animal
	*/
	public ImageIcon getIcon(){
		return ICON;
	}

	/**The status of an animal if its alive*/
	protected boolean isAlive;

	/**The status of an animal if its trapped*/
	protected boolean isTrapped;

	/**The location of an animal on the x coordinate*/
	protected int x;

	/**The location of an animal on the y coordinate*/
	protected int y;

	/**The name of an animal*/
	protected final String NAME;

	/**The owner of the animal*/
	protected final boolean OWNER;

	/**The rank of an animal*/
	protected final int RANK;

	/**The location of ally den on the x coordinate*/
	protected final int ALLY_DEN_X;

	/**The location of ally den on the y coordinate*/
	protected final int ALLY_DEN_Y;

	/**The icon of the animal*/
	protected final ImageIcon ICON;
}
