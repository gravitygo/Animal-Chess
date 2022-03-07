/** This class is what lions can do in the game
 	@author Chyle Andrei Lee
*/
public class Lion extends Animal{
	/**This constructore creates a lion with the owner and the coordinates of the Allied den
		@param player The owner of the Lion object
		@param denX The location of the ally den in the x coordinate
		@param denY The location of the ally den in the y coordinate
	*/
	public Lion(boolean player, int denX, int denY){
		super(player, 6, "Lion", denX, denY, "7lion.png");		

		if(player){
			x = 0; 
			y = 6;
		}else{
			x = 8;
			y = 0;
		}
	}

	/**Method for moving the lion up the board
		@param b The current state of the board
	*/
	@Override
	public void moveUp(Board b){
		Terrain[][] t = b.getTerrain();
		if(canMoveUp(t)){
			do
				y--;
			while(t[x][y].getTerrain()==1);
			if(t[x][y].getTerrain()==2) 
				isTrapped=true;
			else isTrapped=false;

			if(t[x][y].animal!=null)
				eat(t[x][y].animal);
			b.setTerrain(t);
			
		}
	} 

	/**Method for moving the lion down the board
		@param b The current state of the board
	*/
	@Override
	public void moveDown(Board b){
		Terrain[][] t = b.getTerrain();

		if(canMoveDown(t)){
			do
				y++;
			while(t[x][y].getTerrain()==1);
			if(t[x][y].getTerrain()==2) 
				isTrapped=true;
			else isTrapped=false;

			if(t[x][y].animal!=null)
				eat(t[x][y].animal);
			b.setTerrain(t);
		}
	}

	/**Method for moving the lion right the board
		@param b The current state of the board
	*/
	@Override
	public void moveRight(Board b){
		Terrain[][] t = b.getTerrain();

		if(canMoveRight(t)){
			do
				x++;
			while(t[x][y].getTerrain()==1);
			if(t[x][y].getTerrain()==2) 
				isTrapped=true;
			else isTrapped=false;

			if(t[x][y].animal!=null)
				eat(t[x][y].animal);
			b.setTerrain(t);
		}
	}

	/**Method for moving the lion left the board
		@param b The current state of the board
	*/
	@Override
	public void moveLeft(Board b){
		Terrain[][] t = b.getTerrain();

		if(canMoveLeft(t)){
			do
				x--;
			while(t[x][y].getTerrain()==1);
			if(t[x][y].getTerrain()==2) 
				isTrapped=true;
			else isTrapped=false;

			if(t[x][y].animal!=null)
				eat(t[x][y].animal);
			b.setTerrain(t);
		}
	}	

	/**Method for checking if the lion can move left on the board
		@param t The current state of the terrain
		@return true if the animal can successfully moved left.
	*/
	@Override
	public boolean canMoveLeft(Terrain[][] t){
		int leap=1;
		//guard to not go out of bounds
		if(x == 0)return false;	 

		//check if next terrain is water and no enemy on path
		while(t[x-leap][y].getTerrain() == 1){
			if(t[x-leap][y].animal!=null) return false;
			leap++;
		}

		//check if next terrain is ally den
		if(x-leap == ALLY_DEN_X && y == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x-leap][y].animal!=null && !canEat(t[x-leap][y].animal))	return false; 

		return true;
	}

	/**Method for checking if the lion can move right on the board
		@param t The current state of the terrain
		@return true if the animal can successfully moved right.
	*/
	@Override
	public boolean canMoveRight(Terrain[][] t){
		int leap=1;

		//guard to not go out of bounds
		if(x == 8)return false;	

		//check if next terrain is water and no enemy on path
		while(t[x+leap][y].getTerrain() == 1){
			if(t[x+leap][y].animal!=null) return false;
			leap++;
		}
		
		//check if next terrain is ally den
		if(x+leap == ALLY_DEN_X && y == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x+leap][y].animal!=null && !canEat(t[x+leap][y].animal))	return false;

		return true;
	}

	/**Method for checking if the lion can move down on the board
		@param t The current state of the terrain
		@return true if the animal can successfully moved down.
	*/
	@Override
	public boolean canMoveDown(Terrain[][] t){
		int leap=1;
		//guard to not go out of bounds
		if(y == 6) return false;	

		//check if next terrain is water and no enemy on path
		while(t[x][y+leap].getTerrain() == 1){
			if(t[x][y+leap].animal!=null) return false;
			leap++;
		}

		//check if next terrain is ally den
		if(x == ALLY_DEN_X && y + leap == ALLY_DEN_Y)	return false; 

		//check if animal can be eaten
		if(t[x][y+leap].animal!=null && !canEat(t[x][y+leap].animal))	return false; 

		return true;
	}

	/**Method for checking if the lion can move up on the board
		@param t The current state of the terrain
		@return true if the animal can successfully moved up.
	*/
	@Override
	public boolean canMoveUp(Terrain[][] t){
		int leap=1;
		//guard to not go out of bounds
		if(y == 0)return false;	

		//check if next terrain is water and no enemy on path
		while(t[x][y-leap].getTerrain() == 1){
			if(t[x][y-leap].animal!=null) return false;
			leap++;
		}

		//check if next terrain is ally den
		if(x == ALLY_DEN_X && y - leap == ALLY_DEN_Y)	return false;  

		//check if animal can be eaten
		if(t[x][y-leap].animal!=null && !canEat(t[x][y-leap].animal))	return false; 
		
		return true;
	}
}