package zenithmods.recall.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import zenithmods.recall.Config;
import zenithmods.recall.util.RecallSubtype;

public class ItemInk extends RecallItemWithSubtypes {
	public ItemInk(){
		super();
		this.setMaxStackSize(1);
	}
	
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
		if (stack.getItemDamage() != RecallSubtype.BLANK.ordinal()){
			NBTTagCompound tag = stack.getTagCompound();
			short uses = (short)Config.inkUses;
			if (tag != null){
				if (tag.hasKey("usesRemaining")){
					uses = tag.getShort("usesRemaining");
				}
			} else {
				tag = new NBTTagCompound();
				tag.setShort("usesRemaining", (short)Config.inkUses);
				stack.setTagCompound(tag);
			}
			list.add(I18n.format("ink.usesRemaining") + ": " + tag.getShort("usesRemaining") + "/"+Config.inkUses);
		}
	}
}
