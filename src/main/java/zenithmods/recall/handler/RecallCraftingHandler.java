package zenithmods.recall.handler;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import zenithmods.recall.Config;
import zenithmods.recall.RecallItems;
import zenithmods.recall.util.RecallSubtype;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;


public class RecallCraftingHandler {

	
	
	
	
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event){
		handleMortar(event);
		handleInkUsage(event);
		handleInkCreation(event);
	}
	
	private void handleMortar(ItemCraftedEvent event){
		IInventory grid = event.craftMatrix;
		for (int i = 0; i < grid.getSizeInventory(); i++){
			if (grid.getStackInSlot(i) != null){
				ItemStack stack = grid.getStackInSlot(i);
				if (stack.getItem() != null && stack.getItem() == RecallItems.mortarPestle){
					ItemStack newStack = new ItemStack(RecallItems.mortarPestle, 2, 0);
					grid.setInventorySlotContents(i, newStack);
				}
			}
		}
	}
	
	private void handleInkCreation(ItemCraftedEvent event){
		if (event.crafting.getItem() == RecallItems.ink && event.crafting.getItemDamage() != RecallSubtype.BLANK.ordinal()){
			NBTTagCompound tag = new NBTTagCompound();
			tag.setShort("usesRemaining", (short)Config.inkUses);
			event.crafting.setTagCompound(tag);
			
			IInventory grid = event.craftMatrix;
			for (int i = 0; i < grid.getSizeInventory(); i++){
				ItemStack stack = grid.getStackInSlot(i);
				if (stack != null && stack.getItem() == RecallItems.ink && stack.getItemDamage() == RecallSubtype.BLANK.ordinal()){
					grid.setInventorySlotContents(i, null);
				}
			}
			
		}
		
	}
	
	private void handleInkUsage(ItemCraftedEvent event){
		IInventory grid = event.craftMatrix;
		for (int i = 0; i < grid.getSizeInventory(); i++){
			ItemStack stack = grid.getStackInSlot(i);
			if (stack != null && stack.getItem() == RecallItems.ink && stack.getItemDamage() != RecallSubtype.BLANK.ordinal()){
				NBTTagCompound tag = stack.getTagCompound();
				if (tag != null){
					if (tag.hasKey("usesRemaining")){
						short uses = tag.getShort("usesRemaining");
						uses--;
						if (uses <= 0){
							// empty vial
							grid.setInventorySlotContents(i, new ItemStack(RecallItems.ink, 2, RecallSubtype.BLANK.ordinal()));
						} else {
							// decrease
							tag.setShort("usesRemaining", uses);
							ItemStack newStack = new ItemStack(RecallItems.ink, 2, stack.getItemDamage());
							newStack.setTagCompound(tag);
							grid.setInventorySlotContents(i, newStack);
						}
					}
				} else {
					tag = new NBTTagCompound();
					tag.setShort("usesRemaining", (short)(Config.inkUses -1));
					ItemStack newStack = new ItemStack(RecallItems.ink, 2, stack.getItemDamage());
					newStack.setTagCompound(tag);
					grid.setInventorySlotContents(i, newStack);
				}
			}
		}
	}
}
