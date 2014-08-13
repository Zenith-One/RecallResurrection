package zenithmods.recall.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientErrorMessageHandler {
	
	private final int minDelay = 60;
	private int ticksElapsed = 0;
	
	
	@SubscribeEvent
	public void tickStart(TickEvent.PlayerTickEvent event){
		if (event.player.worldObj.isRemote){
			//System.out.println("tick");
			IChatComponent message = ClientErrorMessageStorage.getMessage();
			if (message != null && ticksElapsed == 0){
				ClientErrorMessageStorage.getPlayer().addChatComponentMessage(message);
				ClientErrorMessageStorage.sent();
				ticksElapsed++; 
			} else if (ticksElapsed > 0 && ticksElapsed < minDelay){
				ticksElapsed++;
			} else if (ticksElapsed >= minDelay){
				ticksElapsed = 0;
			}
		} else {
			//System.out.println("server");
		}
		
	}

}
