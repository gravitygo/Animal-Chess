/** This class is what Mouses can do in the game
 	@author Chyle Andrei Lee
*/
public class Mouse extends Animal{
	
	/**This constructor creates a Mouse with the owner and the coordinates of the Allied den
	@param player The owner of the Mouse
	@param denX The x coordinate of the ally den
	@param denY The y coordinate of the ally den*/
	public Mouse(boolean player, int denX, int denY){
		super(player, 0, " Mouse", denX, denY, "1mouse.png");
		if(player){
			x = 2; 
			y = 6;
		}else{
			x = 6;
			y = 0;
		}
	}

	/**Method for checking if the animal can move left on the board
		@param t The current state of the terrain
		@return true if the animal can successfully moved left.
	*/ 
	public boolean canMoveLeft(Terrain[][] t){
		//guard to not go out of bounds
		if(x == 0)return false;	

		//check if next terrain is ally den
		if(x-1 == ALLY_DEN_X && y == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x-1][y].animal!=null && !canEat(t[x-1][y].animal))	return false; 

		return true;
	}

	/**Method for checking if the animal can move right on the board
		@param t The current state of the terrain
		@return true if the animal can successfully moved  right.
	*/ 
	public boolean canMoveRight(Terrain[][] t){
		//guard to not go out of bounds
		if(x == 8)return false;	
		
		//check if next terrain is ally den
		if(x+1 == ALLY_DEN_X && y == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x+1][y].animal!=null && !canEat(t[x+1][y].animal))	return false;

		return true;
	}

	/**Method for checking if the animal can move down on the board
		@param t The current state of the terrain
		@return true if the animal can successfully moved  down.
	*/ 
	public boolean canMoveDown(Terrain[][] t){
		//guard to not go out of bounds
		if(y == 6)return false;	

		//check if next terrain is ally den
		if(x == ALLY_DEN_X && y + 1 == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x][y+1].animal!=null && !canEat(t[x][y+1].animal))	return false; 

		return true;
	}

	/**Method for checking if the animal can move up on the board
		@param t The current state of the terrain
		@return true if the animal can successfully moved left, false otherwise.
	*/ 
	public boolean canMoveUp(Terrain[][] t){
		//guard to not go out of bounds
		if(y == 0)return false;	

		//check if next terrain is ally den
		if(x == ALLY_DEN_X && y - 1 == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x][y-1].animal!=null && !canEat(t[x][y-1].animal))	return false; 
		
		return true;
	}

	/**Method for checking if the animal can eat an animal
		@param a The current animal to be eaten
		@return true if the animal can successfully be eaten, false otherwise.
	*/ 
	public boolean canEat(Animal a){
		//the prey is another mouse
		if(a.getRank()==0)
			//should have the same terrain
			return isOnWater() == ((Mouse)a).isOnWater();

		//if this mouse is in the water
		if(isOnWater())
			//Can't eat anything unless another mouse
			return false;

		return OWNER!=a.getOwner() && (a.getIsTrapped()|| a.getRank()==7);
	}

	/** Method for checking if mouse is on water
		@return True if mouse is in water, false otherwise.
	*/ 
	public boolean isOnWater(){
		if(x>=3 && x<=5 && !(y==0 || y==3 || y==6)) return true;
		return false;
	}
}