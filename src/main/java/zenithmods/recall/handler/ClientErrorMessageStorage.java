package zenithmods.recall.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;

public class ClientErrorMessageStorage {
	
	public static final ClientErrorMessageStorage INSTANCE = new ClientErrorMessageStorage();
	
	private IChatComponent message;
	private EntityPlayer player;
	
	
	public static void setMessage(EntityPlayer player, IChatComponent message){
		System.out.println("Setting message");
		INSTANCE.player  = player;
		INSTANCE.message = message;
	}
	
	public static EntityPlayer getPlayer(){
		return INSTANCE.player;
	}
	
	public static IChatComponent getMessage(){
		return INSTANCE.message;
	}
	
	public static void sent(){
		INSTANCE.message = null;
		INSTANCE.player = null;
	}
}
