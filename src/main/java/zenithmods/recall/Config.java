package zenithmods.recall;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
	public static final String VERSION = "0.0.1";
	public static final String MODID = "RecallResurrection";
	public static final String MOD_TITLE = "Recall Resurrection";
	
	public static int inkUses;
		
	public static void load(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		inkUses = config.get("Balance", "Number of items you can craft with ink", 8).getInt();
		
		config.save();
	}
}
