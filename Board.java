import java.util.*;

/** This class is the board of the game.
 	@author Chyle Andrei Lee
*/

public class Board{
	/**Constructor for initializing the terrain and animal objects.*/
	public Board(){
		/*=========================
		Initial terrain generation
		=========================*/
		for(int i=0; i<9; i++){
			for(int j=0; j<7; j++){
				if(i>=3 && i<=5 && !(j==0 || j==3 || j==6)) terrain[i][j] = new Terrain(1); // River
				
				else if((i==0||i==8) && (j==2||j==4) || 
						(i==1 || i==7) && j==3) terrain[i][j] = new Terrain(2); //traps
				else if((i==0 || i==8) && j==3) terrain [i][j] = new Terrain(3); // Den
				else terrain[i][j] = new Terrain(0); // Land
			}
		}
	}

	/**Removes all animals from the board*/ 
	public void resetTerrain(){
		for(int i=0; i<9; i++)
			for(int j=0; j<7; j++)
				terrain[i][j].animal=null; //assigns null to all terrain
	}

	/**Updates all the position of the animal in the terrain upon call
		@param players all players
	*/
	public void update(Player[] players){
		resetTerrain();
		for(int i=0; i<8; i++){
			for(int j=0; j<2; j++)
				if(players[j].animal[i]!=null && players[j].animal[i].getIsAlive())
					terrain[players[j].animal[i].getX()][players[j].animal[i].getY()].animal = players[j].animal[i];
		}
	}

	/**Gets the terrain array
	   @return terrain array
	**/
	public Terrain[][] getTerrain(){
		return terrain;
	}

	/**Sets the terrain object
	   @param terrain the updated terrain
	**/
	public void setTerrain(Terrain[][] terrain){
		this.terrain = terrain;
	}

	/** An array object of Terrain.*/
	private Terrain[][] terrain = new Terrain[9][7];
}