/** The class Terrain represents a block of terrain in the game Animal Chess
	@author Chyle Andrei lee
*/
import javax.swing.*;
public class Terrain{
	/**This constructor initializes the type of terrain the block should be generated.
	 	@param t Type of terrain{<br>
		&nbsp;Type 0: river<br>
		&nbsp;Type 1: trap<br>
		&nbsp;Type 2: den<br>
		&nbsp;Type 3: land}
	*/
	public Terrain(int t){
		ICON = new ImageIcon("res/"+t+".png");
		TERRAIN_TYPE = t;
	}
	/**Gets the terrain type
	 	@return type of terrain
	*/
	public int getTerrain(){
		return TERRAIN_TYPE;
	}

	/**Gets the icon of the current terrain
	 	@return ImageIcon of the terrain
	*/
	public ImageIcon getIcon(){
		return ICON;
	}

	/**Type of terrain:
	 -Type 0: river
	 -Type 1: trap
	 -Type 2: den
	 -Type 3: land
	*/
	private final int TERRAIN_TYPE;

	/** The Image icon of the current terrain*/
	private final ImageIcon ICON;

	/**Current animal that occupies this block*/
	public Animal animal;
}