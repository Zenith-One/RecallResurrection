package zenithmods.recall.registry;

import net.minecraft.nbt.NBTTagCompound;
import zenithmods.recall.util.Coord6D;

public class BoundLocation {
	private Coord6D coords;
	private String name;
	
	public BoundLocation(Coord6D coords, String name){
		this.coords = coords;
		this.name = name;
	}
	
	public static BoundLocation readFromNBT(NBTTagCompound nbt){
		String theName = null;
		if (nbt.hasKey("name")){
			theName = nbt.getString("name");
		}
		Coord6D theCoords = Coord6D.readFromNBT((NBTTagCompound)nbt.getTag("coords"));
		
		return new BoundLocation(theCoords, theName);
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		
		if (name != null){
			nbt.setString("name", name);
		}
		NBTTagCompound coordsNBT = new NBTTagCompound();
		coordsNBT = coords.writeToNBT(coordsNBT);
		nbt.setTag("coords", coordsNBT);
		return nbt;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Coord6D getCoords(){
		return this.coords;
	}
	
	public void setCoords(Coord6D coords2){
		this.coords = coords2;
	}
}
