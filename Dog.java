/** This class is what dogs can do in the game
 	@author Chyle Andrei Lee
*/
public class Dog extends Animal{
	
	/**This constructor creates a dog with the owner and the coordinates of the Allied den
	@param player The owner of the Dog
	@param denX The x coordinate of the ally den
	@param denY The y coordinate of the ally den*/
	public Dog(boolean player, int denX, int denY){
		super(player, 3, "Dog", denX, denY, "4dog.png");
		if(player){
			x = 1; 
			y = 5;
		}else{
			x = 7;
			y = 1;
		}
	}
}