/** This class is what wolves can do in the game
	@author Chyle Andrei Lee
*/
public class Wolf extends Animal{
	
	/**This constructor creates a Wolf with the owner and the coordinates of the Allied den
	@param player The owner of the Wolf
	@param denX The x coordinate of the ally den
	@param denY The y coordinate of the ally den*/
	public Wolf(boolean player, int denX, int denY){
		super(player, 2, "Wolf", denX, denY, "3wolf.png");
		if(player){
			x = 2; 
			y = 2;
		}else{
			x = 6;
			y = 4;
		}
	}
}