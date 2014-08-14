package zenithmods.recall.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import zenithmods.recall.handler.ClientErrorMessageStorage;
import zenithmods.recall.registry.RecallRegistry;
import zenithmods.recall.util.Coord6D;
import zenithmods.recall.util.RecallSubtype;
import zenithmods.recall.util.RecallTransporter;

public class ItemScroll extends RecallItemWithSubtypes{
	
	
	public ItemScroll(){
		super();
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player){
		if (!player.worldObj.isRemote){
			if (stack.getItemDamage() != RecallSubtype.BLANK.ordinal()){
				String playerName = null;
				if (stack.hasTagCompound() && stack.getTagCompound().getString("name") != null){
					playerName = stack.getTagCompound().getString("name");
				} else {
					playerName = player.getDisplayName();
				}
				
				Coord6D coords = RecallRegistry.getBoundCoords(playerName, RecallSubtype.getFromMeta(stack.getItemDamage()));
				if (coords != null){
					RecallTransporter.transport(player, coords);
					stack.splitStack(1);
					if (stack.stackSize == 0){
						return null;
					}
				}
				
			}
		}
		
		
		return stack;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack){
		if (stack.getItemDamage() != RecallSubtype.BLANK.ordinal()){
			return 80;
		} else {
			return 0;
		}
	}
	
	/**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
	public EnumAction getItemUseAction(ItemStack stack){
    	
        if (stack.getItemDamage() != RecallSubtype.BLANK.ordinal()) {
        	return EnumAction.block;
        } else {
        	return EnumAction.none;
        }
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	if (stack.getItemDamage() != RecallSubtype.BLANK.ordinal()){
    		
			if (player.isSneaking()){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("name", player.getDisplayName());
				stack.setTagCompound(tag);
			} else {
				if (RecallRegistry.getBoundCoords(player.getDisplayName(), RecallSubtype.getFromMeta(stack.getItemDamage())) != null){
					player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
				} else {
					if (player.worldObj.isRemote){
						//System.out.println("adding message");
						ChatComponentTranslation msg = new ChatComponentTranslation("scroll.noLocation");
						ClientErrorMessageStorage.setMessage(player, msg);
					}
					
					
				}
				
			}
    	}
        return stack;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
    	if (!isBlank(stack)){
    		String playerName = player.getDisplayName();
    		String owner = null;
    		String location = null;
    		if (stack.getTagCompound() != null){
    			NBTTagCompound tag = stack.getTagCompound();
    			if (tag.hasKey("playerName")){
    				playerName = tag.getString("playerName");
    				owner = I18n.format("scroll.target")+": " + playerName;
    			}
    		}
    		Coord6D coords = RecallRegistry.getBoundCoords(playerName, RecallSubtype.getFromMeta(stack.getItemDamage()));
			if (coords != null){
				location = I18n.format("bindstone.boundLocation") +": "+ (int)Math.floor(coords.x) +", " + (int)Math.floor(coords.y) + ", " + (int)Math.floor(coords.z) + " (" + I18n.format("recall.dimname."+coords.dimension) + ")";
			} else {
				location = "§4" + I18n.format("bindstone.noLocation") + "§7";
			}
			
    		if (owner != null){
    			list.add(owner);
    		}
    		if (location != null){
    			list.add(location);
    		}
    	}
    	
    }
    
}
