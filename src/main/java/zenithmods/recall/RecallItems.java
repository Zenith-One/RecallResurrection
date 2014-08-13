package zenithmods.recall;

import net.minecraft.item.Item;
import zenithmods.recall.items.ItemBindstone;
import zenithmods.recall.items.ItemInk;
import zenithmods.recall.items.ItemMortarAndPestle;
import zenithmods.recall.items.ItemScroll;
import zenithmods.recall.items.RecallCraftingItem;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecallItems {
	private static final String PATH = Config.MODID + ":";
	
	public static Item bindstone;
	public static Item scroll;
	public static Item ink;
	public static Item craftingItem;
	
	
	public static void register(){
		bindstone = new ItemBindstone().setUnlocalizedName(PATH+"bindstone").setCreativeTab(Recall.tab).setTextureName(PATH+"bindstone");
		GameRegistry.registerItem(bindstone, "bindstone");
		
		scroll = new ItemScroll().setUnlocalizedName(PATH+"scroll").setCreativeTab(Recall.tab).setTextureName(PATH+"scroll");
		GameRegistry.registerItem(scroll, "scroll");
		
		ink = new ItemInk().setUnlocalizedName(PATH+"ink").setCreativeTab(Recall.tab).setTextureName(PATH+"ink");
		GameRegistry.registerItem(ink, "ink");
		
		craftingItem = new RecallCraftingItem().setUnlocalizedName(PATH+"crafting").setCreativeTab(Recall.tab).setTextureName(PATH+"crafting");
		GameRegistry.registerItem(craftingItem, "craftingItem");
	}
}
