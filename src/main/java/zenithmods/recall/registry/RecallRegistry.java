package zenithmods.recall.registry;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import zenithmods.recall.util.Coord6D;
import zenithmods.recall.util.RecallSubtype;
import zenithmods.recall.world.RecallWorldData;

public class RecallRegistry {
	public static RecallRegistry INSTANCE = new RecallRegistry();
	private HashMap<String, HashMap<String,BoundLocation>> locations;
	
	public RecallRegistry(){
		locations = new HashMap<String, HashMap<String, BoundLocation>>();
	}
	
	public static void readFromNBT(NBTTagCompound nbt){
		HashMap<String, HashMap<String, BoundLocation>> locs = INSTANCE.locations;
		if (nbt.hasKey("locations")){
			NBTTagList playerTagList = (NBTTagList) nbt.getTag("locations");
			for (int i = 0; i < playerTagList.tagCount(); i++)
	        {
	            NBTTagCompound playerTag = (NBTTagCompound)playerTagList.getCompoundTagAt(i);
	            String playerName = playerTag.getString("player");
	            System.out.println("Reading locations for "+playerName);
	            HashMap<String, BoundLocation> playerLocs = new HashMap<String, BoundLocation>();
	            NBTTagList playerLocsList = (NBTTagList)playerTag.getTag("locations");
	            for (int j = 0; j < playerLocsList.tagCount(); j++){
	            	NBTTagCompound typeTag = (NBTTagCompound)playerLocsList.getCompoundTagAt(j);
	            	String type = typeTag.getString("type");
	            	System.out.println("Getting location for "+type);
	            	BoundLocation boundLoc = BoundLocation.readFromNBT((NBTTagCompound)typeTag.getTag("location"));
	            	playerLocs.put(type, boundLoc);
	            }
	            locs.put(playerName, playerLocs);
	        }
		}
	}
	
	public static NBTTagCompound writeToNBT(NBTTagCompound nbt){
		HashMap<String, HashMap<String, BoundLocation>> locs = INSTANCE.locations;
		System.out.println("Writing to NBT");
		if (locs.size() > 0){
			NBTTagList playerTagList = new NBTTagList();
			for (String player : locs.keySet()){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("player", player);
				System.out.println("Writing locations for "+player);
				if (locs.get(player).size() > 0){
					HashMap<String, BoundLocation> playerLocs = locs.get(player);
					NBTTagList playerLocsList = new NBTTagList();
					for (String type : playerLocs.keySet()){
						NBTTagCompound typeTag = new NBTTagCompound();
						typeTag.setString("type", type);
						System.out.println("  Writing "+type);
						NBTTagCompound locTag = new NBTTagCompound();
						locTag = playerLocs.get(type).writeToNBT(locTag);
						typeTag.setTag("location", locTag);
						playerLocsList.appendTag(typeTag);
					}
					tag.setTag("locations", playerLocsList);
				}
				playerTagList.appendTag(tag);
			}
			nbt.setTag("locations", playerTagList);
		}
		
		//System.out.println("Wrote to NBT");
		//printLocations();
		return nbt;
	}
	
	public static void bind(EntityPlayer player, RecallSubtype type, Coord6D coords, String name){
		if (player.worldObj.isRemote){
			System.out.println("server");
		} else {
			System.out.println("client");
		}
		HashMap<String, BoundLocation> playerLocations;
		if (INSTANCE.locations.containsKey(player.getDisplayName())){
			playerLocations = INSTANCE.locations.get(player.getDisplayName());
		} else {
			playerLocations = new HashMap<String, BoundLocation>();
			INSTANCE.locations.put(player.getDisplayName(), playerLocations);
		}
		
		BoundLocation loc;
		if (playerLocations.containsKey(type.title())){
			loc = playerLocations.get(type.title());
			loc.setName(name);
			loc.setCoords(coords);
			//markDirty()
		} else {
			loc = new BoundLocation(coords, name);
			playerLocations.put(type.title(), loc);
		}
		printLocations();
		markDirty();
		 
	}
	
	public static Coord6D getBoundCoords(String player, RecallSubtype type){
		Coord6D boundCoords = null;
		if (INSTANCE.locations.containsKey(player)){
			HashMap<String, BoundLocation> playerLocations = INSTANCE.locations.get(player);
			if (playerLocations.containsKey(type.title())){
				boundCoords = playerLocations.get(type.title()).getCoords();
			}
		}
		return boundCoords;
	}
	
	public static void printLocations(){
		if (INSTANCE.locations.size() > 0){
			for (String player : INSTANCE.locations.keySet()){
				System.out.println(player + ": ");
				HashMap<String, BoundLocation> playerLocs = INSTANCE.locations.get(player);
				if (! (playerLocs.size() > 0)){
					System.out.println("No locations");
				} else {
					for (String type : playerLocs.keySet()){
						Coord6D coords = playerLocs.get(type).getCoords();
						System.out.println("  " + type + ": "+coords.x + ", " + coords.y + ", " + coords.z + "; " + coords.dimension);
					}
				}
			}
		}
		
	}
	
	public static void markDirty(){
		World world = DimensionManager.getWorld(0);
		if (world == null){
			System.out.println("null world");
		}
		RecallWorldData data = RecallWorldData.get(world);
		if (data == null) System.out.println("null data");
		data.markDirty();
	}

}
