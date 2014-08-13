package zenithmods.recall.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import zenithmods.recall.handler.ClientErrorMessageStorage;
import zenithmods.recall.registry.RecallRegistry;
import zenithmods.recall.util.Coord6D;
import zenithmods.recall.util.RecallSubtype;

public class ItemBindstone extends RecallItemWithSubtypes{
	
	
	public ItemBindstone(){
		super();
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player){
		if (stack.getItemDamage() != RecallSubtype.BLANK.ordinal()){
			if (stack.getTagCompound() != null){
				NBTTagCompound tag = stack.getTagCompound();
				if (tag.hasKey("playerName")){
					if (tag.getString("playerName").equals(player.getDisplayName())){
						RecallRegistry.bind(player, RecallSubtype.getFromMeta(stack.getItemDamage()), new Coord6D(player.posX, player.posY - 1.5D, player.posZ, player.rotationPitch, player.rotationYaw, player.rotationYawHead, player.worldObj.provider.dimensionId), null);
						ChatComponentTranslation msg = new ChatComponentTranslation("bindstone.locationBound");
						ClientErrorMessageStorage.setMessage(player, msg);
					} else {
						// sanity check
						ChatComponentTranslation msg = new ChatComponentTranslation("bindstone.boundToOther");
						ClientErrorMessageStorage.setMessage(player, msg);
					}
				} else {
					tag.setString("playerName", player.getDisplayName());
					stack.setTagCompound(tag);
				}
			}
		}
		
		return stack;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack){
		if (stack.getItemDamage() != RecallSubtype.BLANK.ordinal()){
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null){
				stack.setTagCompound(new NBTTagCompound());
				tag = stack.getTagCompound();
			}
			if ( tag.hasKey("playerName")){
				return 80;
			} else {
				return 1;
			}
		}
		return 0;
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
    	if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("playerName")){
    		if (stack.getTagCompound().getString("playerName").equals(player.getDisplayName())){
    			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
    		} else {
    			ChatComponentTranslation msg = new ChatComponentTranslation("recall.boundToOther");
    			ClientErrorMessageStorage.setMessage(player, msg);
    		}
    	} else {
    		//System.out.println("No bound player. Binding?");
    		NBTTagCompound tag = new NBTTagCompound();
    		tag.setString("playerName", player.getDisplayName());
    		stack.setTagCompound(tag);
    		player.setItemInUse(stack, this.getMaxDamage(stack));
    	}
        
        return stack;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
    	if (!isBlank(stack)){
    		String owner = null;
    		String location = null;
    		if (stack.getTagCompound() != null){
    			NBTTagCompound tag = stack.getTagCompound();
    			if (!tag.hasKey("playerName")){
    				owner = I18n.format("bindstone.notOwned");
    			} else {
    				String playerName = tag.getString("playerName");
    				String colorCode = playerName.equals(player.getDisplayName()) ? "§2" : "§4";
    				Coord6D coords = RecallRegistry.getBoundCoords(playerName, RecallSubtype.getFromMeta(stack.getItemDamage()));
    				playerName = playerName.equals(player.getDisplayName()) ? I18n.format("bindstone.you") : playerName;
    				owner = colorCode + I18n.format("bindstone.ownedBy") +": " + playerName + "§7";
    				if (coords != null){
    					location = I18n.format("bindstone.boundLocation") +": "+ (int)Math.floor(coords.x) +", " + (int)Math.floor(coords.y) + ", " + (int)Math.floor(coords.z) + " (" + I18n.format("recall.dimname."+coords.dimension) + ")";
    				} else {
    					location = "§4" + I18n.format("bindstone.noLocation") + "§7";
    				}
    			}
    			
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    		}
    		list.add(owner);
    		if (location != null){
    			list.add(location);
    		}
    		
    		
    	}
    	
    }
	
}
