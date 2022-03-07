/** This class is what Leopards can do in the game
 	@author Chyle Andrei Lee
*/
public class Leopard extends Animal{
	
	/**This constructor creates a Leopard with the owner and the coordinates of the Allied den
	@param player The owner of the Leopard
	@param denX The x coordinate of the ally den
	@param denY The y coordinate of the ally den*/
	public Leopard(boolean player, int denX, int denY){
		super(player, 4, "Leopard", denX, denY, "5leopard.png");
		if(player){
			x = 2; 
			y = 4;
		}else{
			x = 6;
			y = 2;
		}
	}
}