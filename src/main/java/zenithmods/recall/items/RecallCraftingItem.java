package zenithmods.recall.items;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;

import zenithmods.recall.Config;
import zenithmods.recall.util.RecallSubtype;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class RecallCraftingItem extends Item {
	
	public static int MORTAR_AND_PESTLE = 0;
	public static int PEARL_DUST = 1;
	
	private ArrayList<MutablePair<String,IIcon>> subTypes = new ArrayList<MutablePair<String, IIcon>>();
	
	public RecallCraftingItem(){
		super();
		this.setHasSubtypes(true);
		initializeSubtypes();
	}
	
	private void initializeSubtypes(){
		subTypes.add(MORTAR_AND_PESTLE, new MutablePair("mortarAndPestle",null));
		subTypes.add(PEARL_DUST, new MutablePair("pearlDust",null));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		for (int i = 0; i < subTypes.size(); i++){
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + subTypes.get(stack.getItemDamage()).getLeft();
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
		return subTypes.get(meta).getRight();
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
		for (MutablePair<String, IIcon> pair : subTypes){
			subTypes.set(subTypes.indexOf(pair), new MutablePair(pair.getLeft(), register.registerIcon(this.getIconString() + "/" + pair.getLeft())));
		}
    }

}
