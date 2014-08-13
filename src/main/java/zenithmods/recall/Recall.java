package zenithmods.recall;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import zenithmods.recall.common.CommonProxy;
import zenithmods.recall.handler.ClientErrorMessageHandler;
import zenithmods.recall.handler.RecallCraftingHandler;
import zenithmods.recall.handler.RecallEventHandler;
import zenithmods.recall.util.RecallSubtype;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Config.MODID, name = Config.MOD_TITLE, version = Config.VERSION, dependencies="")
public class Recall {

	@Instance("Recall")
	public static Recall instance;
	
	public static FMLEventChannel channel;
	
	public static CreativeTabs tab;
	
	@SidedProxy(clientSide = "zenithmods.recall.client.ClientProxy", serverSide = "zenithmods.recall.common.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.load(event);
		
		tab = new CreativeTabs(Config.MODID){
			public ItemStack getIconItemStack(){
				return new ItemStack(RecallItems.bindstone, 1, RecallSubtype.BLUE.ordinal());
			}

			@Override
			public Item getTabIconItem() {
				return getIconItemStack().getItem();
			}
			
		
		};
		
		RecallItems.register();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("recall");
		MinecraftForge.EVENT_BUS.register(new RecallEventHandler());
		FMLCommonHandler.instance().bus().register(new RecallCraftingHandler());
		FMLCommonHandler.instance().bus().register(new ClientErrorMessageHandler());
		
		RecallRecipes.registerRecipes();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
