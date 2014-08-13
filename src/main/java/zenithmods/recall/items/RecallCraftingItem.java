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
	private int type;
	
	private ArrayList<MutablePair<String,IIcon>> subTypes = new ArrayList<MutablePair<String, IIcon>>();
	
	public RecallCraftingItem(int type){
		super();
		this.type = type;
		initializeSubtypes();
	}
	
	private void initializeSubtypes(){
		subTypes.add(MORTAR_AND_PESTLE, new MutablePair("mortarAndPestle",null));
		subTypes.add(PEARL_DUST, new MutablePair("pearlDust",null));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + subTypes.get(type).getLeft();
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
		return subTypes.get(type).getRight();
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
		MutablePair<String, IIcon> pair = subTypes.get(type);
		subTypes.set(subTypes.indexOf(pair), new MutablePair(pair.getLeft(), register.registerIcon(this.getIconString() + "/" + pair.getLeft())));
		
    }

}
