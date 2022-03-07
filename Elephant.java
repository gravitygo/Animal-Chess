/** This class is what Elephants can do in the game
 	@author Chyle Andrei Lee
*/
public class Elephant extends Animal{
	
	/**This constructor creates a Elephant with the owner and the coordinates of the Allied den
	@param player The owner of the Elephant
	@param denX The x coordinate of the ally den
	@param denY The y coordinate of the ally den*/
	public Elephant(boolean player, int denX, int denY){
		super(player, 7, "Elephant", denX, denY, "8elephant.png");
		if(player){
			x = 2; 
			y = 0;
		}else{
			x = 6;
			y = 6;
		}
	}

	/**Method for checking if the animal can eat an animal
		@param a The current animal to be eaten
		@return true if the animal can successfully eat, false otherwise.
	*/ 
	public boolean canEat(Animal a){
		return OWNER!=a.getOwner() && ((a.getRank()!=0 && RANK >= a.getRank())||a.getIsTrapped());
	}
}