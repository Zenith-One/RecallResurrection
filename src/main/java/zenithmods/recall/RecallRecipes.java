package zenithmods.recall;

import zenithmods.recall.util.RecallSubtype;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecallRecipes {
	public static void registerRecipes(){
		
		// mortar and pestle
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RecallItems.mortarPestle), "SBS"," S ", 'S', "stone", 'B', Items.bone));
		
		// pearl dust
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.pearlDust), RecallItems.mortarPestle, Items.ender_pearl));
		
		// empty ink vial
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), " G ", "G G", "GGG", 'G', "paneGlassColorless"));
		
		// blank scroll
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RecallItems.scroll, 1, RecallSubtype.BLANK.ordinal()), "I", "L", "I", 'I', "stickWood", 'L', Items.leather));
		
		// blank bindstone
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RecallItems.bindstone, 1, RecallSubtype.BLANK.ordinal()), " S ", "SPS", " S ", 'S', "stone", 'P', Items.ender_pearl));
		
		// inks
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.BLACK.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeBlack"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.BLUE.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeBlue"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.BROWN.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeBrown"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.CYAN.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeCyan"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.DARK_GRAY.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeGray"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.GREEN.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeGreen"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.LIGHT_BLUE.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeLightBlue"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.LIGHT_GRAY.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeLightGray"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.LIME.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeLime"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.MAGENTA.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeMagenta"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.ORANGE.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeOrange"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.PINK.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyePink"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.PURPLE.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyePurple"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.RED.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeRed"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.WHITE.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeWhite"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(RecallItems.ink, 1, RecallSubtype.YELLOW.ordinal()), RecallItems.pearlDust, new ItemStack(RecallItems.ink, 1, RecallSubtype.BLANK.ordinal()), Items.water_bucket, "dyeYellow"));
		
		for (RecallSubtype t : RecallSubtype.values()){
			if (t != RecallSubtype.BLANK){
				GameRegistry.addRecipe(new ShapelessOreRecipe(
						new ItemStack(RecallItems.bindstone, 1, t.ordinal()), 
						new ItemStack(RecallItems.bindstone, 1, RecallSubtype.BLANK.ordinal()),
						new ItemStack(RecallItems.ink, 1, t.ordinal())
				));
				GameRegistry.addRecipe(new ShapelessOreRecipe(
						new ItemStack(RecallItems.scroll, 1, t.ordinal()), 
						new ItemStack(RecallItems.scroll, 1, RecallSubtype.BLANK.ordinal()),
						new ItemStack(RecallItems.ink, 1, t.ordinal())
				));
			}
			
		}
		
	}
	//new ShapelessOreRecipe(new ItemStack(SteamcraftItems.book), Items.book, "oreZinc", "oreCopper")
	
}
