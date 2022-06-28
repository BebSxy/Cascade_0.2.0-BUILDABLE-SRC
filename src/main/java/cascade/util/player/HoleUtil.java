/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 */
package cascade.util.player;

import cascade.util.Util;
import cascade.util.player.BlockUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class HoleUtil
implements Util {
    public static BlockPos[] holeOffsets = new BlockPos[]{new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(0, -1, 0)};
    private static final Vec3i[] OFFSETS_2x2 = new Vec3i[]{new Vec3i(0, 0, 0), new Vec3i(1, 0, 0), new Vec3i(0, 0, 1), new Vec3i(1, 0, 1)};
    private static final Block[] NO_BLAST = new Block[]{Blocks.bedrock, Blocks.obsidian, Blocks.anvil, Blocks.ender_chest};

    public static boolean isHole(BlockPos pos) {
        boolean isHole = false;
        int amount = 0;
        for (BlockPos p : holeOffsets) {
            if (HoleUtil.mc.theWorld.getBlockState(pos.add((Vec3i)p)).getMaterial().isReplaceable()) continue;
            ++amount;
        }
        if (amount == 5) {
            isHole = true;
        }
        return isHole;
    }

    public static boolean isObbyHole(BlockPos pos) {
        boolean isHole = true;
        int bedrock = 0;
        for (BlockPos off : holeOffsets) {
            Block b = HoleUtil.mc.theWorld.getBlockState(pos.add((Vec3i)off)).getBlock();
            if (!HoleUtil.isSafeBlock(pos.add((Vec3i)off))) {
                isHole = false;
                continue;
            }
            if (b != Blocks.obsidian && b != Blocks.ender_chest && b != Blocks.anvil) continue;
            ++bedrock;
        }
        if (HoleUtil.mc.theWorld.getBlockState(pos.add(0, 2, 0)).getBlock() != Blocks.air || HoleUtil.mc.theWorld.getBlockState(pos.add(0, 1, 0)).getBlock() != Blocks.air) {
            isHole = false;
        }
        if (bedrock < 1) {
            isHole = false;
        }
        return isHole;
    }

    public static boolean isBedrockHoles(BlockPos pos) {
        boolean isHole = true;
        for (BlockPos off : holeOffsets) {
            Block b = HoleUtil.mc.theWorld.getBlockState(pos.add((Vec3i)off)).getBlock();
            if (b == Blocks.bedrock) continue;
            isHole = false;
        }
        if (HoleUtil.mc.theWorld.getBlockState(pos.add(0, 2, 0)).getBlock() != Blocks.air || HoleUtil.mc.theWorld.getBlockState(pos.add(0, 1, 0)).getBlock() != Blocks.air) {
            isHole = false;
        }
        return isHole;
    }

    public static Hole isDoubleHole(BlockPos pos) {
        if (HoleUtil.checkOffset(pos, 1, 0)) {
            return new Hole(false, true, pos, pos.add(1, 0, 0));
        }
        if (HoleUtil.checkOffset(pos, 0, 1)) {
            return new Hole(false, true, pos, pos.add(0, 0, 1));
        }
        return null;
    }

    public static boolean checkOffset(BlockPos pos, int offX, int offZ) {
        return HoleUtil.mc.theWorld.getBlockState(pos).getBlock() == Blocks.air && HoleUtil.mc.theWorld.getBlockState(pos.add(offX, 0, offZ)).getBlock() == Blocks.air && HoleUtil.isSafeBlock(pos.add(0, -1, 0)) && HoleUtil.isSafeBlock(pos.add(offX, -1, offZ)) && HoleUtil.isSafeBlock(pos.add(offX * 2, 0, offZ * 2)) && HoleUtil.isSafeBlock(pos.add(-offX, 0, -offZ)) && HoleUtil.isSafeBlock(pos.add(offZ, 0, offX)) && HoleUtil.isSafeBlock(pos.add(-offZ, 0, -offX)) && HoleUtil.isSafeBlock(pos.add(offX, 0, offZ).add(offZ, 0, offX)) && HoleUtil.isSafeBlock(pos.add(offX, 0, offZ).add(-offZ, 0, -offX));
    }

    static boolean isSafeBlock(BlockPos pos) {
        return HoleUtil.mc.theWorld.getBlockState(pos).getBlock() == Blocks.obsidian || HoleUtil.mc.theWorld.getBlockState(pos).getBlock() == Blocks.bedrock || HoleUtil.mc.theWorld.getBlockState(pos).getBlock() == Blocks.ender_chest;
    }

    public static List<Hole> getHoles(double range, BlockPos playerPos, boolean doubles) {
        ArrayList<Hole> holes = new ArrayList<Hole>();
        List<BlockPos> circle = HoleUtil.getSphere(range, playerPos, true, false);
        for (BlockPos pos : circle) {
            Hole dh;
            if (HoleUtil.mc.theWorld.getBlockState(pos).getBlock() != Blocks.air) continue;
            if (HoleUtil.isObbyHole(pos)) {
                holes.add(new Hole(false, false, pos));
                continue;
            }
            if (HoleUtil.isBedrockHoles(pos)) {
                holes.add(new Hole(true, false, pos));
                continue;
            }
            if (!doubles || (dh = HoleUtil.isDoubleHole(pos)) == null || HoleUtil.mc.theWorld.getBlockState(dh.pos1.add(0, 1, 0)).getBlock() != Blocks.air && HoleUtil.mc.theWorld.getBlockState(dh.pos2.add(0, 1, 0)).getBlock() != Blocks.air) continue;
            holes.add(dh);
        }
        return holes;
    }

    public static List<BlockPos> getSphere(double range, BlockPos pos, boolean sphere, boolean hollow) {
        ArrayList<BlockPos> circleblocks = new ArrayList<BlockPos>();
        int cx = pos.getX();
        int cy = pos.getY();
        int cz = pos.getZ();
        int x = cx - (int)range;
        while ((double)x <= (double)cx + range) {
            int z = cz - (int)range;
            while ((double)z <= (double)cz + range) {
                int y = sphere ? cy - (int)range : cy;
                while (true) {
                    double d2;
                    double d = y;
                    double d3 = d2 = sphere ? (double)cy + range : (double)cy + range;
                    if (!(d < d2)) break;
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
                    if (!(!(dist < range * range) || hollow && dist < (range - 1.0) * (range - 1.0))) {
                        BlockPos l = new BlockPos(x, y, z);
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

    public static boolean[] isHole(BlockPos pos, boolean above) {
        boolean[] result = new boolean[]{false, true};
        if (!BlockUtil.isAir(pos) || !BlockUtil.isAir(pos.up()) || above && !BlockUtil.isAir(pos.up(2))) {
            return result;
        }
        return HoleUtil.is1x1(pos, result);
    }

    public static boolean[] is1x1(BlockPos pos) {
        return HoleUtil.is1x1(pos, new boolean[]{false, true});
    }

    public static boolean[] is1x1(BlockPos pos, boolean[] result) {
        for (EnumFacing facing : EnumFacing.values()) {
            BlockPos offset;
            IBlockState state;
            if (facing == EnumFacing.UP || (state = HoleUtil.mc.theWorld.getBlockState(offset = pos.offset(facing))).getBlock() == Blocks.bedrock) continue;
            if (Arrays.stream(NO_BLAST).noneMatch(b -> b == state.getBlock())) {
                return result;
            }
            result[1] = false;
        }
        result[0] = true;
        return result;
    }

    public static boolean is2x1(BlockPos pos) {
        return HoleUtil.is2x1(pos, true);
    }

    public static boolean is2x1(BlockPos pos, boolean upper) {
        if (upper) {
            if (!BlockUtil.isAir(pos)) {
                return false;
            }
            if (!BlockUtil.isAir(pos.up())) {
                return false;
            }
            if (BlockUtil.isAir(pos.down())) {
                return false;
            }
        }
        int airBlocks = 0;
        for (EnumFacing facing : EnumFacing.HORIZONTALS) {
            BlockPos offset = pos.offset(facing);
            if (BlockUtil.isAir(offset)) {
                if (!BlockUtil.isAir(offset.up())) {
                    return false;
                }
                if (BlockUtil.isAir(offset.down())) {
                    return false;
                }
                for (EnumFacing offsetFacing : EnumFacing.HORIZONTALS) {
                    if (offsetFacing == facing.getOpposite()) continue;
                    IBlockState state = HoleUtil.mc.theWorld.getBlockState(offset.offset(offsetFacing));
                    if (!Arrays.stream(NO_BLAST).noneMatch(b -> b == state.getBlock())) continue;
                    return false;
                }
                ++airBlocks;
            }
            if (airBlocks <= 0) continue;
            return false;
        }
        return airBlocks == 0;
    }

    public static boolean is2x2Partial(BlockPos pos) {
        HashSet<BlockPos> positions = new HashSet<BlockPos>();
        for (Vec3i vec : OFFSETS_2x2) {
            positions.add(pos.add(vec));
        }
        boolean airBlock = false;
        for (BlockPos holePos : positions) {
            if (BlockUtil.isAir(holePos) && BlockUtil.isAir(holePos.up()) && !BlockUtil.isAir(holePos.down())) {
                if (BlockUtil.isAir(holePos.up(2))) {
                    airBlock = true;
                }
                for (EnumFacing facing : EnumFacing.HORIZONTALS) {
                    BlockPos offset = holePos.offset(facing);
                    if (positions.contains(offset)) continue;
                    IBlockState state = HoleUtil.mc.theWorld.getBlockState(offset);
                    if (!Arrays.stream(NO_BLAST).noneMatch(b -> b == state.getBlock())) continue;
                    return false;
                }
                continue;
            }
            return false;
        }
        return airBlock;
    }

    public static boolean is2x2(BlockPos pos) {
        return HoleUtil.is2x2(pos, true);
    }

    public static boolean is2x2(BlockPos pos, boolean upper) {
        if (upper && !BlockUtil.isAir(pos)) {
            return false;
        }
        if (HoleUtil.is2x2Partial(pos)) {
            return true;
        }
        BlockPos l = pos.add(-1, 0, 0);
        boolean airL = BlockUtil.isAir(l);
        if (airL && HoleUtil.is2x2Partial(l)) {
            return true;
        }
        BlockPos r = pos.add(0, 0, -1);
        boolean airR = BlockUtil.isAir(r);
        if (airR && HoleUtil.is2x2Partial(r)) {
            return true;
        }
        return (airL || airR) && HoleUtil.is2x2Partial(pos.add(-1, 0, -1));
    }

    public static class Hole {
        public boolean bedrock;
        public boolean doubleHole;
        public BlockPos pos1;
        public BlockPos pos2;

        public Hole(boolean bedrock, boolean doubleHole, BlockPos pos1, BlockPos pos2) {
            this.bedrock = bedrock;
            this.doubleHole = doubleHole;
            this.pos1 = pos1;
            this.pos2 = pos2;
        }

        public Hole(boolean bedrock, boolean doubleHole, BlockPos pos1) {
            this.bedrock = bedrock;
            this.doubleHole = doubleHole;
            this.pos1 = pos1;
        }
    }
}

