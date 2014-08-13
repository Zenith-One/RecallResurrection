package zenithmods.recall.util;

import java.util.Iterator;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;

public class RecallTransporter {
	
	public static void transport(EntityPlayer entity, Coord6D destination){
		
		System.out.println("Telepooooooort");
		
		if (!entity.worldObj.isRemote && !entity.isDead)
        {	
			//System.out.println(EntityList.c))
			if (entity.dimension != destination.dimension){
				// Do magical MC player dimension stoofs
				transferPlayerToDimension((EntityPlayerMP) entity, destination.dimension, destination);
			} else {
				// load the chunk preemptively
				((EntityPlayerMP)entity).getServerForPlayer().theChunkProviderServer.loadChunk((int)destination.x >> 4, (int)destination.z >> 4);
				
				// Now move the player
				entity.rotationPitch = destination.pitch;
				entity.rotationYaw = destination.yaw;
				entity.setPositionAndUpdate(destination.x, destination.y, destination.z);
			}
			
            
        } else {
        	//System.out.println("World is remote? " + entity.worldObj.isRemote);
        	//System.out.println("Entity is dead?" + entity.isDead);
        }
    
		
		
	}
	
	
	// modified for my use from net.Minecraft.server.management.ServerConfigurationManager
	public static void transferEntityToWorld(Entity entity, int dimension, WorldServer oldWorld, WorldServer newWorld, Coord6D dest)
    {
        WorldProvider pOld = oldWorld.provider;
        WorldProvider pNew = newWorld.provider;
        //oldWorld.theProfiler.startSection("moving");

        

        entity.posY = dest.y;
        entity.setLocationAndAngles(dest.x, dest.y, dest.z, dest.yaw, dest.pitch);

        if (entity.isEntityAlive())
        {
        	entity.setLocationAndAngles(dest.x, dest.y, dest.z, dest.yaw, dest.pitch);
        	newWorld.spawnEntityInWorld(entity);
        	newWorld.updateEntityWithOptionalForce(entity, false);
        }
        
        //oldWorld.theProfiler.endSection();

        entity.setWorld(newWorld);
    }
	
	// Also modified from net.Minecraft.server.management.ServerConfigurationManager
	public static void transferPlayerToDimension(EntityPlayerMP player, int newDim, Coord6D dest)
    {
        int j = player.dimension;
        ServerConfigurationManager manager = MinecraftServer.getServer().getConfigurationManager();
        WorldServer oldServer = MinecraftServer.getServer().worldServerForDimension(player.dimension);
        player.dimension = newDim;
        WorldServer newServer = MinecraftServer.getServer().worldServerForDimension(player.dimension);
        
        // Let's load the chunk for the player. That would probably be swell.
        Chunk c = newServer.getChunkFromBlockCoords((int)Math.floor(dest.x), (int)Math.floor(dest.y));
        int cX = c.xPosition;
        int cZ = c.zPosition;
        newServer.getChunkProvider().loadChunk(cX, cZ);
        
        player.playerNetServerHandler.sendPacket(new S07PacketRespawn(player.dimension, player.worldObj.difficultySetting, player.worldObj.getWorldInfo().getTerrainType(), player.theItemInWorldManager.getGameType()));
        oldServer.removePlayerEntityDangerously(player);
        player.isDead = false;
        transferEntityToWorld(player, j, oldServer, newServer, dest);
        manager.func_72375_a(player, oldServer);
        player.playerNetServerHandler.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        player.theItemInWorldManager.setWorld(newServer);
        manager.updateTimeAndWeatherForPlayer(player, newServer);
        manager.syncPlayerInventory(player);
        Iterator iterator = player.getActivePotionEffects().iterator();

        while (iterator.hasNext())
        {
            PotionEffect potioneffect = (PotionEffect)iterator.next();
            player.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(player.getEntityId(), potioneffect));
        }
        FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, j, newDim);
    }
	
}
