package zenithmods.recall;

import net.minecraft.item.Item;
import zenithmods.recall.items.ItemBindstone;
import zenithmods.recall.items.ItemInk;
import zenithmods.recall.items.ItemScroll;
import zenithmods.recall.items.RecallCraftingItem;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecallItems {
	private static final String PATH = Config.MODID + ":";
	
	public static Item bindstone;
	public static Item scroll;
	public static Item ink;
	public static Item mortarPestle;
	public static Item pearlDust;
	
	
	public static void register(){
		bindstone = new ItemBindstone().setUnlocalizedName(PATH+"bindstone").setCreativeTab(Recall.tab).setTextureName(PATH+"bindstone");
		GameRegistry.registerItem(bindstone, "bindstone");
		
		scroll = new ItemScroll().setUnlocalizedName(PATH+"scroll").setCreativeTab(Recall.tab).setTextureName(PATH+"scroll");
		GameRegistry.registerItem(scroll, "scroll");
		
		ink = new ItemInk().setUnlocalizedName(PATH+"ink").setCreativeTab(Recall.tab).setTextureName(PATH+"ink");
		//ink.setContainerItem(ink);
		GameRegistry.registerItem(ink, "ink");
		
		mortarPestle = new RecallCraftingItem(RecallCraftingItem.MORTAR_AND_PESTLE).setUnlocalizedName(PATH+"crafting").setCreativeTab(Recall.tab).setTextureName(PATH+"crafting").setMaxStackSize(1);
		//mortarPestle.setContainerItem(mortarPestle);
		GameRegistry.registerItem(mortarPestle, "mortarAndPestle");
		
		pearlDust = new RecallCraftingItem(RecallCraftingItem.PEARL_DUST).setUnlocalizedName(PATH+"crafting").setCreativeTab(Recall.tab).setTextureName(PATH+"crafting");
		GameRegistry.registerItem(pearlDust, "pearlDust");
	}
}
