package zenithmods.recall.items;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;

import zenithmods.recall.util.RecallSubtype;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class RecallItemWithSubtypes extends Item{
	
	
	private ArrayList<MutablePair<String,IIcon>> subTypes = new ArrayList<MutablePair<String, IIcon>>();
	
	
	public RecallItemWithSubtypes(){
		super();
		this.setHasSubtypes(true);
		for (RecallSubtype t : RecallSubtype.values()){
			subTypes.add(new MutablePair(t.title(), null));
		}
		
	}
	
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		for (RecallSubtype t : RecallSubtype.values()){
			list.add(new ItemStack(item, 1, t.ordinal()));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + RecallSubtype.getFromMeta(stack.getItemDamage()).title();
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
		return subTypes.get(meta).getRight();
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {	
		for (RecallSubtype t : RecallSubtype.values()){
			subTypes
				.get(t.ordinal())
					.setRight(register.registerIcon(
						this.getIconString() + "/" + t.title()	
					)
				);
		}
    }
	
	protected boolean isBlank(ItemStack stack){
    	return stack.getItemDamage() == RecallSubtype.BLANK.ordinal();
    }
	
}
