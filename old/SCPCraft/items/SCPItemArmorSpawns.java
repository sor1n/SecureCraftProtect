package SCPCraft.items;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import SCPCraft.mod_SCP;
import SCPCraft.tileentities.SCPTileEntityShelf;

public class SCPItemArmorSpawns extends SCPItemDocument
{
	String scpName;
	String nickname;
	String col;
	String cont = "";
	int securityValue;
	Item helmet, chest, pants, boots;
	int keycard;
	int quantity;
	/** 
	 * @param i Item id
	 * @param name Name of the SCP
	 * @param nic Nickname for the item;
	 * @param color This is the color of the text (safe is \u00a72; euclid is \u00a7e; keter is \u00a74)
	 * @param secVal Security level (1 for Level 1, 2 for Level 2, 3 for Level 3 and 4 for Omni)
	 * @param item The item to spawn
	 * @param no The quantity of the item to spawn
	 */
	public SCPItemArmorSpawns(int i, String name, String nic, String color, int secVal, Item head, Item body, Item legs, Item feet, int no)
	{
		super(i);
		maxStackSize = 1;
		scpName = name;
		col = color;
		securityValue = secVal;
		nickname = nic;
		helmet = head;
		chest = body;
		pants = legs;
		boots = feet;
		quantity = no;
		setMaxDamage(1);
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		list.add(col + scpName);
		list.add("\u00a77" + nickname);
	}

	public boolean isFull3D()
	{
		return true;
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{		
		if (par3World.isRemote)
		{
			return true;
		}
		else
		{
	        int var11 = par3World.getBlockId(par4, par5, par6);
	        int metadata = MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3;
			if (var11 == Block.snow.blockID)
	        {
	            par7 = 1;
	        }
	        else if (var11 != Block.vine.blockID && var11 != Block.tallGrass.blockID && var11 != Block.deadBush.blockID
	                && (Block.blocksList[var11] == null || !Block.blocksList[var11].isBlockReplaceable(par3World, par4, par5, par6))) par5 +=1;

			for(int height = -1; height <= 4; height++)
				for(int length = -2; length <= 2; length++)
					for(int width = -1; width <= 3; width++)
					{					
						par3World.setBlockMetadataWithNotify(par4 + length, par5 + height, par6 + width, Block.blockSteel.blockID, 0);					
					}
			for(int height = 0; height <= 3; height++)
				for(int length = -1; length <= 1; length++)
					for(int width = 0; width <= 2; width++)
					{
						par3World.setBlockMetadataWithNotify(par4 + length, par5 + height, par6 + width, 0, 0);				
					}
			if(securityValue == 1){
				cont = "Safe";
				keycard = mod_SCP.KeycardSlot.blockID;
			}
			if(securityValue == 2){
				cont = "Euclid";
				keycard = mod_SCP.KeycardSlotLv2.blockID;
			}
			if(securityValue == 3){
				cont = "Keter";
				keycard = mod_SCP.KeycardSlotLv3.blockID;
			}
			SCPTileEntityShelf shelf = new SCPTileEntityShelf();
			SCPTileEntityShelf shelf1 = new SCPTileEntityShelf();
			SCPTileEntityShelf shelf2 = new SCPTileEntityShelf();
			SCPTileEntityShelf shelf3 = new SCPTileEntityShelf();
			if(metadata == 0){
				SCPItemSlideDoor.placeDoorBlock(par3World, par4, par5, par6 + 3, 3);
				par3World.setBlockMetadataWithNotify(par4 - 1, par5 + 1, par6 + 2, keycard, 2);
				par3World.setBlockMetadataWithNotify(par4 + 1, par5 + 1, par6 + 4, keycard, 4);
				par3World.setBlockMetadataWithNotify(par4, par5, par6, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4, par5, par6, shelf);
				shelf.setItem(new ItemStack(boots, quantity));

				par3World.setBlockMetadataWithNotify(par4, par5 + 1, par6, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4, par5 + 1, par6, shelf1);
				shelf1.setItem(new ItemStack(pants, quantity));
				
				par3World.setBlockMetadataWithNotify(par4, par5 + 2, par6, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4, par5 + 2, par6, shelf2);
				shelf2.setItem(new ItemStack(chest, quantity));

				par3World.setBlockMetadataWithNotify(par4, par5 + 3, par6, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4, par5 + 3, par6, shelf3);
				shelf3.setItem(new ItemStack(helmet, quantity));
			}
			if(metadata == 1){
				SCPItemSlideDoor.placeDoorBlock(par3World, par4 - 2, par5, par6 + 1, 0);
				par3World.setBlockMetadataWithNotify(par4 - 1, par5 + 1, par6, keycard, 3);
				par3World.setBlockMetadataWithNotify(par4 - 3, par5 + 1, par6 + 2, keycard, 1);
				par3World.setBlockMetadataWithNotify(par4 + 1, par5, par6 + 1, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4 + 1, par5, par6 + 1, shelf);
				shelf.setItem(new ItemStack(boots, quantity));

				par3World.setBlockMetadataWithNotify(par4 + 1, par5 + 1, par6 + 1, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4 + 1, par5 + 1, par6 + 1, shelf1);
				shelf1.setItem(new ItemStack(pants, quantity));

				par3World.setBlockMetadataWithNotify(par4 + 1, par5 + 2, par6 + 1, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4 + 1, par5 + 2, par6 + 1, shelf2);
				shelf2.setItem(new ItemStack(chest, quantity));

				par3World.setBlockMetadataWithNotify(par4 + 1, par5 + 3, par6 + 1, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4 + 1, par5 + 3, par6 + 1, shelf3);
				shelf3.setItem(new ItemStack(helmet, quantity));
			}
			if(metadata == 2){
				SCPItemSlideDoor.placeDoorBlock(par3World, par4, par5, par6 - 1, 1);
				par3World.setBlockMetadataWithNotify(par4 - 1, par5 + 1, par6 - 2, keycard, 2);
				par3World.setBlockMetadataWithNotify(par4 + 1, par5 + 1, par6, keycard, 4);
				par3World.setBlockMetadataWithNotify(par4, par5, par6 + 2, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4, par5, par6 + 2, shelf);
				shelf.setItem(new ItemStack(boots, quantity));
				
				par3World.setBlockMetadataWithNotify(par4, par5 + 1, par6 + 2, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4, par5 + 1, par6 + 2, shelf1);
				shelf1.setItem(new ItemStack(pants, quantity));

				par3World.setBlockMetadataWithNotify(par4, par5 + 2, par6 + 2, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4, par5 + 2, par6 + 2, shelf2);
				shelf2.setItem(new ItemStack(chest, quantity));

				par3World.setBlockMetadataWithNotify(par4, par5 + 3, par6 + 2, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4, par5 + 3, par6 + 2, shelf3);
				shelf3.setItem(new ItemStack(helmet, quantity));
			}
			if(metadata == 3){
				SCPItemSlideDoor.placeDoorBlock(par3World, par4 + 2, par5, par6 + 1, 2);
				par3World.setBlockMetadataWithNotify(par4 + 1, par5 + 1, par6 + 2, keycard, 1);
				par3World.setBlockMetadataWithNotify(par4 + 3, par5 + 1, par6, keycard, 3);
				par3World.setBlockMetadataWithNotify(par4 - 1, par5, par6 + 1, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4 - 1, par5, par6 + 1, shelf);
				shelf.setItem(new ItemStack(boots, quantity));
				
				par3World.setBlockMetadataWithNotify(par4 - 1, par5 + 1, par6 + 1, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4 - 1, par5 + 1, par6 + 1, shelf1);
				shelf1.setItem(new ItemStack(pants, quantity));

				par3World.setBlockMetadataWithNotify(par4 - 1, par5 + 2, par6 + 1, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4 - 1, par5 + 2, par6 + 1, shelf2);
				shelf2.setItem(new ItemStack(chest, quantity));

				par3World.setBlockMetadataWithNotify(par4 - 1, par5 + 3, par6 + 1, mod_SCP.Shelf.blockID, MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw * 4F) / 360F) + 2.5D) & 3);
				par3World.setBlockTileEntity(par4 - 1, par5 + 3, par6 + 1, shelf3);
				shelf3.setItem(new ItemStack(helmet, quantity));
			}
			--par1ItemStack.stackSize;
			par2EntityPlayer.addChatMessage(scpName + " Spawned." + " | [Type: " + col + cont + "\u00a7f]");

			return true;
		}
	}
}