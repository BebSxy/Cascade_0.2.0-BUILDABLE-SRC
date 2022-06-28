/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityShulkerBox
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package cascade.util.player;

import cascade.util.Util;
import cascade.util.entity.EntityUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class PlayerUtil
implements Util {
    public static boolean isElytraEquipped() {
        return PlayerUtil.mc.thePlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA;
    }

    public static void packetJump(boolean onGround) {
        mc.getNetHandler().addToSendQueue((Packet)new CPacketPlayer.Position(PlayerUtil.mc.thePlayer.posX, PlayerUtil.mc.thePlayer.posY + 0.41999998688698, PlayerUtil.mc.thePlayer.posZ, onGround));
        mc.getNetHandler().addToSendQueue((Packet)new CPacketPlayer.Position(PlayerUtil.mc.thePlayer.posX, PlayerUtil.mc.thePlayer.posY + 0.7531999805212, PlayerUtil.mc.thePlayer.posZ, onGround));
        mc.getNetHandler().addToSendQueue((Packet)new CPacketPlayer.Position(PlayerUtil.mc.thePlayer.posX, PlayerUtil.mc.thePlayer.posY + 1.00133597911214, PlayerUtil.mc.thePlayer.posZ, onGround));
        mc.getNetHandler().addToSendQueue((Packet)new CPacketPlayer.Position(PlayerUtil.mc.thePlayer.posX, PlayerUtil.mc.thePlayer.posY + 1.16610926093821, PlayerUtil.mc.thePlayer.posZ, onGround));
    }

    public static boolean isBoxColliding() {
        return PlayerUtil.mc.theWorld.getCollisionBoxes((Entity)PlayerUtil.mc.thePlayer, PlayerUtil.mc.thePlayer.getEntityBoundingBox().offset(0.0, 0.21, 0.0)).size() > 0;
    }

    public static boolean isClipping() {
        return !PlayerUtil.mc.theWorld.getCollisionBoxes((Entity)PlayerUtil.mc.thePlayer, PlayerUtil.mc.thePlayer.getEntityBoundingBox()).isEmpty();
    }

    public static boolean isOffset() {
        Vec3d center = EntityUtil.getCenter(PlayerUtil.mc.thePlayer.posX, PlayerUtil.mc.thePlayer.posY, PlayerUtil.mc.thePlayer.posZ);
        return PlayerUtil.mc.thePlayer.getDistance(center.xCoord, center.yCoord, center.zCoord) > 0.2;
    }

    public static boolean isPushable(double x, double y, double z) {
        Block temp = PlayerUtil.mc.theWorld.getBlockState(new BlockPos(x, y += 1.0, z)).getBlock();
        if (temp == Blocks.piston_head || temp == Blocks.PISTON_EXTENSION) {
            return true;
        }
        for (TileEntity entity : PlayerUtil.mc.theWorld.loadedTileEntityList) {
            AxisAlignedBB axisAlignedBB;
            TileEntityShulkerBox tileEntityShulkerBox;
            if (!(entity instanceof TileEntityShulkerBox)) continue;
            TileEntityShulkerBox tempShulker = (TileEntityShulkerBox)entity;
            if (!(tileEntityShulkerBox.getProgress(mc.getRenderPartialTicks()) > 0.0f)) continue;
            AxisAlignedBB tempAxis = tempShulker.getRenderBoundingBox();
            if (!(axisAlignedBB.minY <= y && tempAxis.maxY >= y && (double)((int)tempAxis.minX) <= x && tempAxis.maxX >= x) && (!((double)((int)tempAxis.minZ) <= z) || !(tempAxis.maxZ >= z))) continue;
            return true;
        }
        return false;
    }

    public static boolean isBurrow() {
        Block block = PlayerUtil.mc.theWorld.getBlockState(new BlockPos(PlayerUtil.mc.thePlayer.getPositionVector().addVector(0.0, 0.2, 0.0))).getBlock();
        return block == Blocks.obsidian || block == Blocks.ender_chest;
    }

    public static boolean isChestBelow() {
        return !PlayerUtil.isBurrow() && EntityUtil.isOnChest((Entity)PlayerUtil.mc.thePlayer);
    }

    public static boolean isInLiquidF() {
        if (PlayerUtil.mc.thePlayer.fallDistance >= 3.0f) {
            return false;
        }
        boolean inLiquid = false;
        AxisAlignedBB bb = PlayerUtil.mc.thePlayer.getRidingEntity() != null ? PlayerUtil.mc.thePlayer.getRidingEntity().getEntityBoundingBox() : PlayerUtil.mc.thePlayer.getEntityBoundingBox();
        int y = (int)bb.minY;
        for (int x = MathHelper.floor_double((double)bb.minX); x < MathHelper.floor_double((double)bb.maxX) + 1; ++x) {
            for (int z = MathHelper.floor_double((double)bb.minZ); z < MathHelper.floor_double((double)bb.maxZ) + 1; ++z) {
                Block block = PlayerUtil.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (block instanceof BlockAir) continue;
                if (!(block instanceof BlockLiquid)) {
                    return false;
                }
                inLiquid = true;
            }
        }
        return inLiquid;
    }

    public static boolean inLiquid() {
        return PlayerUtil.inLiquid(MathHelper.floor_double((double)(PlayerUtil.mc.thePlayer.getEntityBoundingBox().minY + 0.01)));
    }

    public static boolean inLiquid(boolean feet) {
        return PlayerUtil.inLiquid(MathHelper.floor_double((double)(PlayerUtil.mc.thePlayer.getEntityBoundingBox().minY - (feet ? 0.03 : 0.2))));
    }

    private static boolean inLiquid(int y) {
        return PlayerUtil.findState(BlockLiquid.class, y) != null;
    }

    private static IBlockState findState(Class<? extends Block> block, int y) {
        int startX = MathHelper.floor_double((double)PlayerUtil.mc.thePlayer.getEntityBoundingBox().minX);
        int startZ = MathHelper.floor_double((double)PlayerUtil.mc.thePlayer.getEntityBoundingBox().minZ);
        int endX = MathHelper.ceiling_double_int((double)PlayerUtil.mc.thePlayer.getEntityBoundingBox().maxX);
        int endZ = MathHelper.ceiling_double_int((double)PlayerUtil.mc.thePlayer.getEntityBoundingBox().maxZ);
        for (int x = startX; x < endX; ++x) {
            for (int z = startZ; z < endZ; ++z) {
                IBlockState s = PlayerUtil.mc.theWorld.getBlockState(new BlockPos(x, y, z));
                if (!block.isInstance(s.getBlock())) continue;
                return s;
            }
        }
        return null;
    }

    public static boolean isAbove(BlockPos pos) {
        return PlayerUtil.mc.thePlayer.getEntityBoundingBox().minY >= (double)pos.getY();
    }

    public static boolean isMovementBlocked() {
        IBlockState state = PlayerUtil.findState(Block.class, MathHelper.floor_double((double)(PlayerUtil.mc.thePlayer.getEntityBoundingBox().minY - 0.01)));
        return state != null && state.getMaterial().blocksMovement();
    }

    public static boolean isAboveLiquid() {
        if (PlayerUtil.mc.thePlayer != null) {
            double n = PlayerUtil.mc.thePlayer.posY + 0.01;
            for (int i = MathHelper.floor_double((double)PlayerUtil.mc.thePlayer.posX); i < MathHelper.ceiling_double_int((double)PlayerUtil.mc.thePlayer.posX); ++i) {
                for (int j = MathHelper.floor_double((double)PlayerUtil.mc.thePlayer.posZ); j < MathHelper.ceiling_double_int((double)PlayerUtil.mc.thePlayer.posZ); ++j) {
                    if (!(EntityUtil.mc.theWorld.getBlockState(new BlockPos(i, (int)n, j)).getBlock() instanceof BlockLiquid)) continue;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean checkForLiquid(boolean b) {
        if (PlayerUtil.mc.thePlayer != null) {
            double posY = PlayerUtil.mc.thePlayer.posY;
            double n = b ? 0.03 : (PlayerUtil.mc.thePlayer instanceof EntityPlayer ? 0.2 : 0.5);
            double n2 = posY - n;
            for (int i = MathHelper.floor_double((double)PlayerUtil.mc.thePlayer.posX); i < MathHelper.ceiling_double_int((double)PlayerUtil.mc.thePlayer.posX); ++i) {
                for (int j = MathHelper.floor_double((double)PlayerUtil.mc.thePlayer.posZ); j < MathHelper.ceiling_double_int((double)PlayerUtil.mc.thePlayer.posZ); ++j) {
                    if (!(EntityUtil.mc.theWorld.getBlockState(new BlockPos(i, MathHelper.floor_double((double)n2), j)).getBlock() instanceof BlockLiquid)) continue;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean isAboveBlock(BlockPos blockPos) {
        return PlayerUtil.mc.thePlayer.posY >= (double)blockPos.getY();
    }
}

