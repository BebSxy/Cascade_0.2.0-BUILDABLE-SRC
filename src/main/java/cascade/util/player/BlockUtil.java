/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockAnvil
 *  net.minecraft.block.BlockBed
 *  net.minecraft.block.BlockButton
 *  net.minecraft.block.BlockCake
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.BlockDoor
 *  net.minecraft.block.BlockEnderChest
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.block.BlockRedstoneDiode
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockTrapDoor
 *  net.minecraft.block.BlockWeb
 *  net.minecraft.block.BlockWorkbench
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.CombatRules
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package cascade.util.player;

import cascade.util.Util;
import cascade.util.player.InventoryUtil;
import cascade.util.player.RotationUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockUtil
implements Util {
    public static final List<Block> blackList = Arrays.asList(Blocks.ender_chest, Blocks.chest, Blocks.trapped_chest, Blocks.crafting_table, Blocks.anvil, Blocks.brewing_stand, Blocks.hopper, Blocks.dropper, Blocks.dispenser, Blocks.trapdoor, Blocks.enchanting_table);
    public static final List<Block> shulkerList = Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
    public static final List<Block> unSafeBlocks = Arrays.asList(Blocks.obsidian, Blocks.bedrock, Blocks.ender_chest, Blocks.anvil);
    public static List<Block> unSolidBlocks = Arrays.asList(Blocks.flowing_lava, Blocks.flower_pot, Blocks.snow, Blocks.carpet, Blocks.END_ROD, Blocks.skull, Blocks.flower_pot, Blocks.tripwire, Blocks.tripwire_hook, Blocks.wooden_button, Blocks.lever, Blocks.stone_button, Blocks.ladder, Blocks.unpowered_comparator, Blocks.powered_comparator, Blocks.unpowered_repeater, Blocks.powered_repeater, Blocks.unlit_redstone_torch, Blocks.redstone_torch, Blocks.redstone_wire, Blocks.air, Blocks.portal, Blocks.end_portal, Blocks.water, Blocks.flowing_water, Blocks.lava, Blocks.flowing_lava, Blocks.sapling, Blocks.red_flower, Blocks.yellow_flower, Blocks.brown_mushroom, Blocks.red_mushroom, Blocks.wheat, Blocks.carrots, Blocks.potatoes, Blocks.BEETROOTS, Blocks.reeds, Blocks.pumpkin_stem, Blocks.melon_stem, Blocks.waterlily, Blocks.nether_wart, Blocks.cocoa, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, Blocks.tallgrass, Blocks.deadbush, Blocks.vine, Blocks.fire, Blocks.rail, Blocks.activator_rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.torch);
    public static List<Block> emptyBlocks = Arrays.asList(Blocks.air, Blocks.flowing_lava, Blocks.lava, Blocks.flowing_water, Blocks.water, Blocks.vine, Blocks.snow_layer, Blocks.tallgrass, Blocks.fire);
    public static List<Block> rightclickableBlocks = Arrays.asList(Blocks.chest, Blocks.trapped_chest, Blocks.ender_chest, Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.anvil, Blocks.wooden_button, Blocks.stone_button, Blocks.unpowered_comparator, Blocks.unpowered_repeater, Blocks.powered_repeater, Blocks.powered_comparator, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.brewing_stand, Blocks.dispenser, Blocks.dropper, Blocks.lever, Blocks.noteblock, Blocks.jukebox, Blocks.beacon, Blocks.bed, Blocks.furnace, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.BIRCH_DOOR, Blocks.JUNGLE_DOOR, Blocks.ACACIA_DOOR, Blocks.DARK_OAK_DOOR, Blocks.cake, Blocks.enchanting_table, Blocks.dragon_egg, Blocks.hopper, Blocks.REPEATING_COMMAND_BLOCK, Blocks.command_block, Blocks.CHAIN_COMMAND_BLOCK, Blocks.crafting_table);
    public static List<Block> nullHitboxBlocks = Arrays.asList(Blocks.air, Blocks.tripwire, Blocks.tripwire_hook, Blocks.wooden_button, Blocks.lever, Blocks.stone_button, Blocks.unlit_redstone_torch, Blocks.redstone_torch, Blocks.redstone_wire, Blocks.sapling, Blocks.red_flower, Blocks.yellow_flower, Blocks.brown_mushroom, Blocks.red_mushroom, Blocks.wheat, Blocks.carrots, Blocks.potatoes, Blocks.BEETROOTS, Blocks.reeds, Blocks.pumpkin_stem, Blocks.melon_stem, Blocks.tallgrass, Blocks.deadbush, Blocks.vine, Blocks.fire, Blocks.rail, Blocks.activator_rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.torch);
    static List<BlockPos> tickCache = new ArrayList<BlockPos>();

    public static float calculateDamage(double posX, double posY, double posZ, Entity entity) {
        float doubleExplosionSize = 12.0f;
        double distancedsize = entity.getDistance(posX, posY, posZ) / (double)doubleExplosionSize;
        Vec3d vec3d = new Vec3d(posX, posY, posZ);
        double blockDensity = 0.0;
        try {
            blockDensity = BlockUtil.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        }
        catch (Exception exception) {
            // empty catch block
        }
        double v = (1.0 - distancedsize) * blockDensity;
        float damage = (int)((v * v + v) / 2.0 * 7.0 * (double)doubleExplosionSize + 1.0);
        double finald = 1.0;
        if (entity instanceof EntityLivingBase) {
            try {
                finald = BlockUtil.getBlastReduction((EntityLivingBase)entity, BlockUtil.getDamageMultiplied(damage), new Explosion((World)BlockUtil.mc.theWorld, null, posX, posY, posZ, 6.0f, false, true));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return (float)finald;
    }

    private static float getDamageMultiplied(float damage) {
        int diff = BlockUtil.mc.theWorld.getDifficulty().getDifficultyId();
        return damage * (diff == 0 ? 0.0f : (diff == 2 ? 1.0f : (diff == 1 ? 0.5f : 1.5f)));
    }

    private static float getBlockDensity(Vec3d vec, AxisAlignedBB bb) {
        double d0 = 1.0 / ((bb.maxX - bb.minX) * 2.0 + 1.0);
        double d2 = 1.0 / ((bb.maxY - bb.minY) * 2.0 + 1.0);
        double d3 = 1.0 / ((bb.maxZ - bb.minZ) * 2.0 + 1.0);
        double d4 = (1.0 - Math.floor(1.0 / d0) * d0) / 2.0;
        double d5 = (1.0 - Math.floor(1.0 / d3) * d3) / 2.0;
        if (d0 >= 0.0 && d2 >= 0.0 && d3 >= 0.0) {
            int j2 = 0;
            int k2 = 0;
            for (float f = 0.0f; f <= 1.0f; f += (float)d0) {
                for (float f2 = 0.0f; f2 <= 1.0f; f2 += (float)d2) {
                    for (float f3 = 0.0f; f3 <= 1.0f; f3 += (float)d3) {
                        double d6 = bb.minX + (bb.maxX - bb.minX) * (double)f;
                        double d7 = bb.minY + (bb.maxY - bb.minY) * (double)f2;
                        double d8 = bb.minZ + (bb.maxZ - bb.minZ) * (double)f3;
                        if (BlockUtil.rayTraceBlocks(new Vec3d(d6 + d4, d7, d8 + d5), vec, false, false, false, true) == null) {
                            ++j2;
                        }
                        ++k2;
                    }
                }
            }
            return (float)j2 / (float)k2;
        }
        return 0.0f;
    }

    private static RayTraceResult rayTraceBlocks(Vec3d vec31, Vec3d vec32, boolean stopOnLiquid, boolean ignoreNoBox, boolean returnLastUncollidableBlock, boolean ignoreWebs) {
        if (Double.isNaN(vec31.xCoord) || Double.isNaN(vec31.yCoord) || Double.isNaN(vec31.zCoord)) {
            return null;
        }
        if (!(Double.isNaN(vec32.xCoord) || Double.isNaN(vec32.yCoord) || Double.isNaN(vec32.zCoord))) {
            RayTraceResult raytraceresult;
            int x1 = MathHelper.floor_double((double)vec31.xCoord);
            int y1 = MathHelper.floor_double((double)vec31.yCoord);
            int z1 = MathHelper.floor_double((double)vec31.zCoord);
            int x2 = MathHelper.floor_double((double)vec32.xCoord);
            int y2 = MathHelper.floor_double((double)vec32.yCoord);
            int z2 = MathHelper.floor_double((double)vec32.zCoord);
            BlockPos pos = new BlockPos(x1, y1, z1);
            IBlockState state = BlockUtil.mc.theWorld.getBlockState(pos);
            Block block = state.getBlock();
            if (!(ignoreNoBox && state.getCollisionBoundingBox((IBlockAccess)BlockUtil.mc.theWorld, pos) == Block.NULL_AABB || !block.canCollideCheck(state, stopOnLiquid) || ignoreWebs && block instanceof BlockWeb || (raytraceresult = state.collisionRayTrace((World)BlockUtil.mc.theWorld, pos, vec31, vec32)) == null)) {
                return raytraceresult;
            }
            RayTraceResult raytraceresult2 = null;
            int k1 = 200;
            while (k1-- >= 0) {
                EnumFacing enumfacing;
                if (Double.isNaN(vec31.xCoord) || Double.isNaN(vec31.yCoord) || Double.isNaN(vec31.zCoord)) {
                    return null;
                }
                if (x1 == x2 && y1 == y2 && z1 == z2) {
                    return returnLastUncollidableBlock ? raytraceresult2 : null;
                }
                boolean flag2 = true;
                boolean flag3 = true;
                boolean flag4 = true;
                double d0 = 999.0;
                double d2 = 999.0;
                double d3 = 999.0;
                if (x2 > x1) {
                    d0 = (double)x1 + 1.0;
                } else if (x2 < x1) {
                    d0 = (double)x1 + 0.0;
                } else {
                    flag2 = false;
                }
                if (y2 > y1) {
                    d2 = (double)y1 + 1.0;
                } else if (y2 < y1) {
                    d2 = (double)y1 + 0.0;
                } else {
                    flag3 = false;
                }
                if (z2 > z1) {
                    d3 = (double)z1 + 1.0;
                } else if (z2 < z1) {
                    d3 = (double)z1 + 0.0;
                } else {
                    flag4 = false;
                }
                double d4 = 999.0;
                double d5 = 999.0;
                double d6 = 999.0;
                double d7 = vec32.xCoord - vec31.xCoord;
                double d8 = vec32.yCoord - vec31.yCoord;
                double d9 = vec32.zCoord - vec31.zCoord;
                if (flag2) {
                    d4 = (d0 - vec31.xCoord) / d7;
                }
                if (flag3) {
                    d5 = (d2 - vec31.yCoord) / d8;
                }
                if (flag4) {
                    d6 = (d3 - vec31.zCoord) / d9;
                }
                if (d4 == -0.0) {
                    d4 = -1.0E-4;
                }
                if (d5 == -0.0) {
                    d5 = -1.0E-4;
                }
                if (d6 == -0.0) {
                    d6 = -1.0E-4;
                }
                if (d4 < d5 && d4 < d6) {
                    enumfacing = x2 > x1 ? EnumFacing.WEST : EnumFacing.EAST;
                    vec31 = new Vec3d(d0, vec31.yCoord + d8 * d4, vec31.zCoord + d9 * d4);
                } else if (d5 < d6) {
                    enumfacing = y2 > y1 ? EnumFacing.DOWN : EnumFacing.UP;
                    vec31 = new Vec3d(vec31.xCoord + d7 * d5, d2, vec31.zCoord + d9 * d5);
                } else {
                    enumfacing = z2 > z1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
                    vec31 = new Vec3d(vec31.xCoord + d7 * d6, vec31.yCoord + d8 * d6, d3);
                }
                x1 = MathHelper.floor_double((double)vec31.xCoord) - (enumfacing == EnumFacing.EAST ? 1 : 0);
                y1 = MathHelper.floor_double((double)vec31.yCoord) - (enumfacing == EnumFacing.UP ? 1 : 0);
                z1 = MathHelper.floor_double((double)vec31.zCoord) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
                pos = new BlockPos(x1, y1, z1);
                IBlockState state2 = BlockUtil.mc.theWorld.getBlockState(pos);
                Block block2 = state2.getBlock();
                if (ignoreNoBox && state2.getMaterial() != Material.portal && state2.getCollisionBoundingBox((IBlockAccess)BlockUtil.mc.theWorld, pos) == Block.NULL_AABB) continue;
                if (!(!block2.canCollideCheck(state2, stopOnLiquid) || ignoreWebs && block2 instanceof BlockWeb)) {
                    RayTraceResult raytraceresult3 = state2.collisionRayTrace((World)BlockUtil.mc.theWorld, pos, vec31, vec32);
                    if (raytraceresult3 == null) continue;
                    return raytraceresult3;
                }
                raytraceresult2 = new RayTraceResult(RayTraceResult.Type.MISS, vec31, enumfacing, pos);
            }
            return returnLastUncollidableBlock ? raytraceresult2 : null;
        }
        return null;
    }

    private static float getBlastReduction(EntityLivingBase entity, float damageI, Explosion explosion) {
        float damage = damageI;
        if (entity instanceof EntityPlayer) {
            EntityPlayer ep = (EntityPlayer)entity;
            DamageSource ds = DamageSource.setExplosionSource((Explosion)explosion);
            damage = CombatRules.getDamageAfterAbsorb((float)damage, (float)ep.getTotalArmorValue(), (float)((float)ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
            int k = 0;
            try {
                k = EnchantmentHelper.getEnchantmentModifierDamage((Iterable)ep.getArmorInventoryList(), (DamageSource)ds);
            }
            catch (Exception exception) {
                // empty catch block
            }
            float f = MathHelper.clamp_float((float)k, (float)0.0f, (float)20.0f);
            damage *= 1.0f - f / 25.0f;
            if (entity.isPotionActive(MobEffects.resistance)) {
                damage -= damage / 4.0f;
            }
            damage = Math.max(damage, 0.0f);
            return damage;
        }
        damage = CombatRules.getDamageAfterAbsorb((float)damage, (float)entity.getTotalArmorValue(), (float)((float)entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
        return damage;
    }

    public static boolean rayTraceCheckPos(BlockPos pos) {
        return BlockUtil.mc.theWorld.rayTraceBlocks(new Vec3d(BlockUtil.mc.thePlayer.posX, BlockUtil.mc.thePlayer.posY + (double)BlockUtil.mc.thePlayer.getEyeHeight(), BlockUtil.mc.thePlayer.posZ), new Vec3d((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), false, true, false) != null;
    }

    public static boolean isPlayerSafe(EntityPlayer target) {
        BlockPos playerPos = new BlockPos(Math.floor(target.posX), Math.floor(target.posY), Math.floor(target.posZ));
        return !(BlockUtil.mc.theWorld.getBlockState(playerPos.down()).getBlock() != Blocks.obsidian && BlockUtil.mc.theWorld.getBlockState(playerPos.down()).getBlock() != Blocks.bedrock || BlockUtil.mc.theWorld.getBlockState(playerPos.north()).getBlock() != Blocks.obsidian && BlockUtil.mc.theWorld.getBlockState(playerPos.north()).getBlock() != Blocks.bedrock || BlockUtil.mc.theWorld.getBlockState(playerPos.east()).getBlock() != Blocks.obsidian && BlockUtil.mc.theWorld.getBlockState(playerPos.east()).getBlock() != Blocks.bedrock || BlockUtil.mc.theWorld.getBlockState(playerPos.south()).getBlock() != Blocks.obsidian && BlockUtil.mc.theWorld.getBlockState(playerPos.south()).getBlock() != Blocks.bedrock || BlockUtil.mc.theWorld.getBlockState(playerPos.west()).getBlock() != Blocks.obsidian && BlockUtil.mc.theWorld.getBlockState(playerPos.west()).getBlock() != Blocks.bedrock);
    }

    public static boolean isPosValidForCrystal(BlockPos pos, boolean onepointthirteen) {
        if (BlockUtil.mc.theWorld.getBlockState(pos).getBlock() != Blocks.bedrock && BlockUtil.mc.theWorld.getBlockState(pos).getBlock() != Blocks.obsidian) {
            return false;
        }
        if (BlockUtil.mc.theWorld.getBlockState(pos.up()).getBlock() != Blocks.air || !onepointthirteen && BlockUtil.mc.theWorld.getBlockState(pos.up().up()).getBlock() != Blocks.air) {
            return false;
        }
        for (Entity entity : BlockUtil.mc.theWorld.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.up()))) {
            if (entity.isDead || entity instanceof EntityEnderCrystal) continue;
            return false;
        }
        for (Entity entity : BlockUtil.mc.theWorld.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.up().up()))) {
            if (entity.isDead || entity instanceof EntityEnderCrystal) continue;
            return false;
        }
        return true;
    }

    public static List<BlockPos> getSphereAutoCrystal(double radius, boolean noAir) {
        ArrayList<BlockPos> posList = new ArrayList<BlockPos>();
        BlockPos pos = new BlockPos(Math.floor(BlockUtil.mc.thePlayer.posX), Math.floor(BlockUtil.mc.thePlayer.posY), Math.floor(BlockUtil.mc.thePlayer.posZ));
        int x = pos.getX() - (int)radius;
        while ((double)x <= (double)pos.getX() + radius) {
            int y = pos.getY() - (int)radius;
            while ((double)y < (double)pos.getY() + radius) {
                int z = pos.getZ() - (int)radius;
                while ((double)z <= (double)pos.getZ() + radius) {
                    double distance = (pos.getX() - x) * (pos.getX() - x) + (pos.getZ() - z) * (pos.getZ() - z) + (pos.getY() - y) * (pos.getY() - y);
                    BlockPos position = new BlockPos(x, y, z);
                    if (distance < radius * radius && noAir && !BlockUtil.mc.theWorld.getBlockState(position).getBlock().equals(Blocks.air)) {
                        posList.add(position);
                    }
                    ++z;
                }
                ++y;
            }
            ++x;
        }
        return posList;
    }

    public static List<EnumFacing> getPossibleSides(BlockPos pos) {
        ArrayList<EnumFacing> facings = new ArrayList<EnumFacing>();
        for (EnumFacing side : EnumFacing.values()) {
            IBlockState blockState;
            BlockPos neighbour = pos.offset(side);
            if (!BlockUtil.mc.theWorld.getBlockState(neighbour).getBlock().canCollideCheck(BlockUtil.mc.theWorld.getBlockState(neighbour), false) || (blockState = BlockUtil.mc.theWorld.getBlockState(neighbour)).getMaterial().isReplaceable()) continue;
            facings.add(side);
        }
        return facings;
    }

    public static Vec3d[] getHelpingBlocks(Vec3d vec3d) {
        return new Vec3d[]{new Vec3d(vec3d.xCoord, vec3d.yCoord - 1.0, vec3d.zCoord), new Vec3d(vec3d.xCoord != 0.0 ? vec3d.xCoord * 2.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord != 0.0 ? vec3d.zCoord : vec3d.zCoord * 2.0), new Vec3d(vec3d.xCoord == 0.0 ? vec3d.xCoord + 1.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord == 0.0 ? vec3d.zCoord : vec3d.zCoord + 1.0), new Vec3d(vec3d.xCoord == 0.0 ? vec3d.xCoord - 1.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord == 0.0 ? vec3d.zCoord : vec3d.zCoord - 1.0), new Vec3d(vec3d.xCoord, vec3d.yCoord + 1.0, vec3d.zCoord)};
    }

    public static List<BlockPos> getSphere(BlockPos pos, float r, int h, boolean hollow, boolean sphere, int plus_y) {
        ArrayList<BlockPos> circleblocks = new ArrayList<BlockPos>();
        int cx = pos.getX();
        int cy = pos.getY();
        int cz = pos.getZ();
        int x = cx - (int)r;
        while ((float)x <= (float)cx + r) {
            int z = cz - (int)r;
            while ((float)z <= (float)cz + r) {
                int y = sphere ? cy - (int)r : cy;
                while (true) {
                    float f2;
                    float f = y;
                    float f3 = f2 = sphere ? (float)cy + r : (float)(cy + h);
                    if (!(f < f2)) break;
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
                    if (dist < (double)(r * r) && (!hollow || dist >= (double)((r - 1.0f) * (r - 1.0f)))) {
                        BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                    ++y;
                }
                ++z;
            }
            ++x;
        }
        return circleblocks;
    }

    public static BlockPos[] toBlockPos(Vec3d[] vec3ds) {
        BlockPos[] list = new BlockPos[vec3ds.length];
        for (int i = 0; i < vec3ds.length; ++i) {
            list[i] = new BlockPos(vec3ds[i]);
        }
        return list;
    }

    public static boolean isBlockUnSolid(Block block) {
        return unSolidBlocks.contains(block);
    }

    public static boolean placeBlock(BlockPos pos, EnumHand hand, boolean rotate, boolean packet, boolean isSneaking, boolean swing) {
        boolean sneaking = false;
        EnumFacing side = BlockUtil.getFirstFacing(pos);
        if (side == null) {
            return isSneaking;
        }
        BlockPos neighbour = pos.offset(side);
        EnumFacing opposite = side.getOpposite();
        Vec3d hitVec = new Vec3d((Vec3i)neighbour).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
        Block neighbourBlock = BlockUtil.mc.theWorld.getBlockState(neighbour).getBlock();
        if (!BlockUtil.mc.thePlayer.isSneaking() && (blackList.contains(neighbourBlock) || shulkerList.contains(neighbourBlock))) {
            mc.getNetHandler().addToSendQueue((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.thePlayer, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil.mc.thePlayer.setSneaking(true);
            sneaking = true;
        }
        if (rotate) {
            RotationUtil.faceVector(hitVec, true);
        }
        BlockUtil.rightClickBlock(neighbour, hitVec, hand, opposite, packet, swing);
        BlockUtil.mc.rightClickDelayTimer = 4;
        return sneaking || isSneaking;
    }

    public static EnumFacing getFirstFacing(BlockPos pos) {
        Iterator<EnumFacing> iterator = BlockUtil.getPossibleSides(pos).iterator();
        if (iterator.hasNext()) {
            EnumFacing facing = iterator.next();
            return facing;
        }
        return null;
    }

    public static boolean rayTracePlaceCheck(BlockPos pos, boolean shouldCheck, float height) {
        return !shouldCheck || BlockUtil.mc.theWorld.rayTraceBlocks(new Vec3d(BlockUtil.mc.thePlayer.posX, BlockUtil.mc.thePlayer.posY + (double)BlockUtil.mc.thePlayer.getEyeHeight(), BlockUtil.mc.thePlayer.posZ), new Vec3d((double)pos.getX(), (double)((float)pos.getY() + height), (double)pos.getZ()), false, true, false) == null;
    }

    public static boolean rayTracePlaceCheck(BlockPos pos, boolean shouldCheck) {
        return BlockUtil.rayTracePlaceCheck(pos, shouldCheck, 1.0f);
    }

    public static boolean rayTracePlaceCheck(BlockPos pos) {
        return BlockUtil.rayTracePlaceCheck(pos, true);
    }

    public static boolean canBreak(BlockPos pos) {
        IBlockState blockState = BlockUtil.mc.theWorld.getBlockState(pos);
        Block block = blockState.getBlock();
        return block.getBlockHardness(blockState, (World)BlockUtil.mc.theWorld, pos) != -1.0f;
    }

    public static Boolean isPosInFov(BlockPos pos) {
        int dirnumber = RotationUtil.getDirection4D();
        if (dirnumber == 0 && (double)pos.getZ() - BlockUtil.mc.thePlayer.getPositionVector().zCoord < 0.0) {
            return false;
        }
        if (dirnumber == 1 && (double)pos.getX() - BlockUtil.mc.thePlayer.getPositionVector().xCoord > 0.0) {
            return false;
        }
        if (dirnumber == 2 && (double)pos.getZ() - BlockUtil.mc.thePlayer.getPositionVector().zCoord > 0.0) {
            return false;
        }
        return dirnumber != 3 || (double)pos.getX() - BlockUtil.mc.thePlayer.getPositionVector().xCoord >= 0.0;
    }

    public static boolean isBlockUnSafe(Block block) {
        return unSafeBlocks.contains(block);
    }

    public static void faceVectorPacketInstant(Vec3d vec) {
        float[] rotations = RotationUtil.getLegitRotations(vec);
        mc.getNetHandler().addToSendQueue((Packet)new CPacketPlayer.Rotation(rotations[0], rotations[1], BlockUtil.mc.thePlayer.onGround));
    }

    public static boolean placeBlock(BlockPos pos) {
        if (BlockUtil.isBlockEmpty(pos)) {
            EnumFacing[] values;
            EnumFacing[] facings = values = EnumFacing.values();
            for (EnumFacing f : values) {
                Block neighborBlock = BlockUtil.mc.theWorld.getBlockState(pos.offset(f)).getBlock();
                Vec3d vec = new Vec3d((double)pos.getX() + 0.5 + (double)f.getFrontOffsetX() * 0.5, (double)pos.getY() + 0.5 + (double)f.getFrontOffsetY() * 0.5, (double)pos.getZ() + 0.5 + (double)f.getFrontOffsetZ() * 0.5);
                if (emptyBlocks.contains(neighborBlock) || !(BlockUtil.mc.thePlayer.getPositionEyes(mc.getRenderPartialTicks()).distanceTo(vec) <= 4.25)) continue;
                float[] rot = new float[]{BlockUtil.mc.thePlayer.rotationYaw, BlockUtil.mc.thePlayer.rotationPitch};
                if (rightclickableBlocks.contains(neighborBlock)) {
                    BlockUtil.mc.thePlayer.sendQueue.addToSendQueue((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.thePlayer, CPacketEntityAction.Action.START_SNEAKING));
                }
                BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.thePlayer, BlockUtil.mc.theWorld, pos.offset(f), f.getOpposite(), new Vec3d((Vec3i)pos), EnumHand.MAIN_HAND);
                if (rightclickableBlocks.contains(neighborBlock)) {
                    BlockUtil.mc.thePlayer.sendQueue.addToSendQueue((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.thePlayer, CPacketEntityAction.Action.STOP_SNEAKING));
                }
                BlockUtil.mc.thePlayer.swingArm(EnumHand.MAIN_HAND);
                return true;
            }
        }
        return false;
    }

    public static boolean isBlockEmpty(BlockPos pos) {
        try {
            if (emptyBlocks.contains(BlockUtil.mc.theWorld.getBlockState(pos).getBlock())) {
                AxisAlignedBB box = new AxisAlignedBB(pos);
                for (Entity e : BlockUtil.mc.theWorld.loadedEntityList) {
                    if (!(e instanceof EntityLivingBase) || !box.intersectsWith(e.getEntityBoundingBox())) continue;
                    return false;
                }
                return true;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return false;
    }

    public static boolean isScaffoldPos(BlockPos pos) {
        return BlockUtil.mc.theWorld.isAirBlock(pos) || BlockUtil.mc.theWorld.getBlockState(pos).getBlock() == Blocks.snow_layer || BlockUtil.mc.theWorld.getBlockState(pos).getBlock() == Blocks.tallgrass || BlockUtil.mc.theWorld.getBlockState(pos).getBlock() instanceof BlockLiquid;
    }

    public static boolean isValidBlock(BlockPos pos) {
        Block block = BlockUtil.mc.theWorld.getBlockState(pos).getBlock();
        return !(block instanceof BlockLiquid) && block.getMaterial(null) != Material.air;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos) {
        BlockPos boost = blockPos.add(0, 1, 0);
        BlockPos boost2 = blockPos.add(0, 2, 0);
        try {
            return (BlockUtil.mc.theWorld.getBlockState(blockPos).getBlock() == Blocks.bedrock || BlockUtil.mc.theWorld.getBlockState(blockPos).getBlock() == Blocks.obsidian) && BlockUtil.mc.theWorld.getBlockState(boost).getBlock() == Blocks.air && BlockUtil.mc.theWorld.getBlockState(boost2).getBlock() == Blocks.air && BlockUtil.mc.theWorld.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(boost)).isEmpty() && BlockUtil.mc.theWorld.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(boost2)).isEmpty();
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean isInHole() {
        BlockPos blockPos = new BlockPos(BlockUtil.mc.thePlayer.posX, BlockUtil.mc.thePlayer.posY, BlockUtil.mc.thePlayer.posZ);
        IBlockState blockState = BlockUtil.mc.theWorld.getBlockState(blockPos);
        return BlockUtil.isBlockValid(blockState, blockPos);
    }

    public static boolean isInHoleTest() {
        BlockPos blockPos = new BlockPos(BlockUtil.mc.thePlayer.posX, BlockUtil.mc.thePlayer.posY - 1.0, BlockUtil.mc.thePlayer.posZ);
        IBlockState blockState = BlockUtil.mc.theWorld.getBlockState(blockPos);
        return BlockUtil.isBlockValid(blockState, blockPos);
    }

    public static boolean isBlockValid(IBlockState blockState, BlockPos blockPos) {
        return blockState.getBlock() == Blocks.air && BlockUtil.mc.thePlayer.getDistanceSq(blockPos) >= 1.0 && BlockUtil.mc.theWorld.getBlockState(blockPos.up()).getBlock() == Blocks.air && BlockUtil.mc.theWorld.getBlockState(blockPos.up(2)).getBlock() == Blocks.air && (BlockUtil.isBedrockHole(blockPos) || BlockUtil.isObbyHole(blockPos) || BlockUtil.isBothHole(blockPos) || BlockUtil.isElseHole(blockPos));
    }

    public static boolean isObbyHole(BlockPos blockPos) {
        for (BlockPos pos : BlockUtil.getTouchingBlocks(blockPos)) {
            IBlockState touchingState = BlockUtil.mc.theWorld.getBlockState(pos);
            if (touchingState.getBlock() != Blocks.air && touchingState.getBlock() == Blocks.obsidian) continue;
            return false;
        }
        return true;
    }

    public static boolean isBedrockHole(BlockPos blockPos) {
        for (BlockPos pos : BlockUtil.getTouchingBlocks(blockPos)) {
            IBlockState touchingState = BlockUtil.mc.theWorld.getBlockState(pos);
            if (touchingState.getBlock() != Blocks.air && touchingState.getBlock() == Blocks.bedrock) continue;
            return false;
        }
        return true;
    }

    public static boolean isBothHole(BlockPos blockPos) {
        for (BlockPos pos : BlockUtil.getTouchingBlocks(blockPos)) {
            IBlockState touchingState = BlockUtil.mc.theWorld.getBlockState(pos);
            if (touchingState.getBlock() != Blocks.air && (touchingState.getBlock() == Blocks.bedrock || touchingState.getBlock() == Blocks.obsidian)) continue;
            return false;
        }
        return true;
    }

    public static boolean isElseHole(BlockPos blockPos) {
        for (BlockPos pos : BlockUtil.getTouchingBlocks(blockPos)) {
            IBlockState touchingState = BlockUtil.mc.theWorld.getBlockState(pos);
            if (touchingState.getBlock() != Blocks.air && touchingState.isFullBlock()) continue;
            return false;
        }
        return true;
    }

    public static BlockPos[] getTouchingBlocks(BlockPos blockPos) {
        return new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
    }

    public static double getNearestBlockBelow() {
        for (double y = BlockUtil.mc.thePlayer.posY; y > 0.0; y -= 0.001) {
            if (BlockUtil.mc.theWorld.getBlockState(new BlockPos(BlockUtil.mc.thePlayer.posX, y, BlockUtil.mc.thePlayer.posZ)).getBlock() instanceof BlockSlab || BlockUtil.mc.theWorld.getBlockState(new BlockPos(BlockUtil.mc.thePlayer.posX, y, BlockUtil.mc.thePlayer.posZ)).getBlock().getDefaultState().getCollisionBoundingBox((IBlockAccess)BlockUtil.mc.theWorld, new BlockPos(0, 0, 0)) == null) continue;
            return y;
        }
        return -1.0;
    }

    public static boolean isAir(BlockPos pos) {
        return BlockUtil.mc.theWorld.getBlockState(pos).getBlock() == Blocks.air;
    }

    public static double getDistanceSq(BlockPos pos) {
        return BlockUtil.getDistanceSq((Entity)RotationUtil.getRotationPlayer(), pos);
    }

    public static double getDistanceSq(Entity from, BlockPos to) {
        return from.getDistanceSqToCenter(to);
    }

    public static boolean placeBlock(BlockPos pos, boolean sneak) {
        Block block = BlockUtil.mc.theWorld.getBlockState(pos).getBlock();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
            return false;
        }
        EnumFacing side = BlockUtil.getPlaceableSide(pos);
        if (side == null) {
            return false;
        }
        BlockPos neighbour = pos.offset(side);
        EnumFacing opposite = side.getOpposite();
        Vec3d hitVec = new Vec3d((Vec3i)neighbour).addVector(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
        if (!BlockUtil.mc.thePlayer.isSneaking()) {
            mc.getNetHandler().addToSendQueue((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.thePlayer, CPacketEntityAction.Action.START_SNEAKING));
        }
        EnumActionResult action = BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.thePlayer, BlockUtil.mc.theWorld, neighbour, opposite, hitVec, EnumHand.MAIN_HAND);
        BlockUtil.mc.thePlayer.swingArm(EnumHand.MAIN_HAND);
        mc.getNetHandler().addToSendQueue((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.thePlayer, CPacketEntityAction.Action.STOP_SNEAKING));
        tickCache.add(pos);
        return action == EnumActionResult.SUCCESS;
    }

    public static EnumFacing getPlaceableSide(BlockPos pos) {
        for (EnumFacing side : EnumFacing.values()) {
            IBlockState blockState;
            BlockPos neighbour = pos.offset(side);
            if (!BlockUtil.mc.theWorld.getBlockState(neighbour).getBlock().canCollideCheck(BlockUtil.mc.theWorld.getBlockState(neighbour), false) && tickCache.contains(neighbour) || (blockState = BlockUtil.mc.theWorld.getBlockState(neighbour)).getMaterial().isReplaceable()) continue;
            return side;
        }
        return null;
    }

    public void onUpdate() {
        tickCache = new ArrayList<BlockPos>();
    }

    public static boolean placeBlock(BlockPos pos, boolean packet, boolean rotate, boolean allowEc) {
        if (BlockUtil.mc.theWorld.getBlockState(pos).getMaterial().isReplaceable()) {
            int obbySlot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
            int ecSlot = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
            if (obbySlot == -1 && ecSlot == -1) {
                return true;
            }
            if (obbySlot != -1) {
                InventoryUtil.packetSwap(obbySlot);
            }
            if (obbySlot == -1 && ecSlot != -1 && allowEc) {
                InventoryUtil.packetSwap(ecSlot);
            }
            EnumFacing side = BlockUtil.getFirstFacing(pos);
            BlockPos currentPos = pos.offset(side);
            EnumFacing currentFace = side.getOpposite();
            if (BlockUtil.mc.thePlayer.isSprinting()) {
                mc.getNetHandler().addToSendQueue((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.thePlayer, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            if (BlockUtil.shouldSneakWhileRightClicking(currentPos)) {
                mc.getNetHandler().addToSendQueue((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.thePlayer, CPacketEntityAction.Action.START_SNEAKING));
            }
            Vec3d hitVec = new Vec3d((Vec3i)currentPos).addVector(0.5, 0.5, 0.5).add(new Vec3d(currentFace.getDirectionVec()).scale(0.5));
            if (rotate) {
                RotationUtil.faceVector(hitVec, true);
            }
            BlockUtil.rightClickBlock(currentPos, hitVec, EnumHand.MAIN_HAND, currentFace, packet, false);
            if (BlockUtil.shouldSneakWhileRightClicking(currentPos)) {
                mc.getNetHandler().addToSendQueue((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.thePlayer, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            if (BlockUtil.mc.thePlayer.isSprinting()) {
                mc.getNetHandler().addToSendQueue((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.thePlayer, CPacketEntityAction.Action.START_SPRINTING));
            }
        }
        return false;
    }

    public static void rightClickBlock(BlockPos pos, Vec3d vec, EnumHand hand, EnumFacing direction, boolean packet, boolean swing) {
        if (packet) {
            float f = (float)(vec.xCoord - (double)pos.getX());
            float f1 = (float)(vec.yCoord - (double)pos.getY());
            float f2 = (float)(vec.zCoord - (double)pos.getZ());
            mc.getNetHandler().addToSendQueue((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f1, f2));
        } else {
            BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.thePlayer, BlockUtil.mc.theWorld, pos, direction, vec, hand);
        }
        if (swing) {
            BlockUtil.mc.thePlayer.swingArm(EnumHand.MAIN_HAND);
        } else {
            mc.getNetHandler().addToSendQueue((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
        }
        BlockUtil.mc.rightClickDelayTimer = 4;
    }

    public static boolean shouldSneakWhileRightClicking(BlockPos pos) {
        Block block = BlockUtil.mc.theWorld.getBlockState(pos).getBlock();
        TileEntity tileEntity = null;
        for (TileEntity e : BlockUtil.mc.theWorld.loadedTileEntityList) {
            if (e.getPos() != pos) continue;
            tileEntity = e;
            break;
        }
        return tileEntity != null || block instanceof BlockBed || block instanceof BlockContainer || block instanceof BlockDoor || block instanceof BlockTrapDoor || block instanceof BlockFenceGate || block instanceof BlockButton || block instanceof BlockAnvil || block instanceof BlockWorkbench || block instanceof BlockCake || block instanceof BlockRedstoneDiode;
    }
}

