package zenithmods.recall.world;

import zenithmods.recall.Config;
import zenithmods.recall.registry.RecallRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;



public class RecallWorldData extends WorldSavedData {

	private static final String ID = Config.MODID+"Data";
	
	public RecallWorldData(String id) {
		super(ID);
		//markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		RecallRegistry.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		RecallRegistry.writeToNBT(nbt);
	}
	
	public static RecallWorldData get(World world) {
		RecallWorldData data = (RecallWorldData) world.perWorldStorage.loadData(RecallWorldData.class, ID);
        if (data == null) {
        	data = new RecallWorldData(ID);
            world.perWorldStorage.setData(ID, data);
        }
        return data;
    }

}
