/** This class is what Cats can do in the game
 	@author Chyle Andrei Lee
*/
public class Cat extends Animal{
	
	/**This constructor creates a Cat with the owner and the coordinates of the Allied den
	@param player The owner of the Cat
	@param denX The x coordinate of the ally den
	@param denY The y coordinate of the ally den*/
	public Cat(boolean player, int denX, int denY){
		super(player, 1, "Cat", denX, denY, "2cat.png");
		if(player){
			x = 1; 
			y = 1;
		}else{
			x = 7;
			y = 5;
		}
	}
}