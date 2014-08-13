package zenithmods.recall.handler;

import zenithmods.recall.world.RecallWorldData;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RecallEventHandler {
	
	@SubscribeEvent
	public void handleWorldLoad(WorldEvent.Load event) {
		if (!event.world.isRemote){
			if (event.world.provider.dimensionId == 0){
				RecallWorldData.get(event.world);
			}
		}
		
	}
}
