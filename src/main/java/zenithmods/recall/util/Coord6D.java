package zenithmods.recall.util;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class Coord6D {
	public double x, y, z;
	public float pitch, yaw, headYaw;
	public int dimension;
	
	public Coord6D(double x, double y, double z, float pitch, float yaw, float headYaw, int dimension){
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
		this.headYaw = headYaw;
		this.dimension = dimension;
	}
	
	public Coord6D(double x, double y, double z, int dimension){
		this(x, y, z, 0f, 0f, 0f, dimension);
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		nbt.setDouble("x", x);
		nbt.setDouble("y", y);
		nbt.setDouble("z", z);
		nbt.setFloat("pitch", pitch);
		nbt.setFloat("yaw", yaw);
		nbt.setFloat("headYaw", headYaw);
		nbt.setInteger("dimension", dimension);

		return nbt;
	}
	
	public static Coord6D readFromNBT(NBTTagCompound nbt){
		double xc = nbt.getDouble("x");
		double yc = nbt.getDouble("y");
		double zc = nbt.getDouble("z");
		float  pitchIn = nbt.getFloat("pitch");
		float  yawIn = nbt.getFloat("yaw");
		float headYawIn = nbt.getFloat("headYaw");
		int d = nbt.getInteger("dimension");
		return new Coord6D(xc, yc, zc, pitchIn, yawIn, headYawIn, d);
	}
	
	public String toString(){
		return "Coord4: " + x + ", " + y + ", " + z + ", " + pitch + ", " + yaw + ", " + headYaw +" ; Dimension: " + dimension;
	}
	
	
	
	@Override
	public boolean equals(Object other){
		if (other instanceof Coord6D &&
			this.x == ((Coord6D)other).x &&
			this.y == ((Coord6D)other).y &&
			this.z == ((Coord6D)other).z &&
			this.pitch == ((Coord6D)other).pitch &&
			this.yaw == ((Coord6D)other).yaw &&
			this.headYaw == ((Coord6D)other).headYaw &&
			this.dimension == ((Coord6D)other).dimension
			){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		int hash = 1;
		hash = 31 * hash + (int)(Math.floor(x * 1000));
		hash = 31 * hash + (int)(Math.floor(y * 1000));
		hash = 31 * hash + (int)(Math.floor(z * 1000));
		hash = 31 * hash + (int)(Math.floor((double)pitch * 1000));
		hash = 31 * hash + (int)(Math.floor((double)yaw * 1000));
		hash = 31 * hash + (int)(Math.floor((double)headYaw * 1000));
		hash = 31 * hash + dimension;
		return hash;
	}
}
