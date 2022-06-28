/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 */
package cascade.event.events;

import cascade.event.EventStage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class CollisionEvent
extends EventStage {
    private final Entity entity;
    private final BlockPos pos;
    private final Block block;
    private AxisAlignedBB bb;

    public CollisionEvent(BlockPos pos, AxisAlignedBB bb, Entity entity, Block block) {
        this.pos = pos;
        this.bb = bb;
        this.entity = entity;
        this.block = block;
    }

    public AxisAlignedBB getBB() {
        return this.bb;
    }

    public void setBB(AxisAlignedBB bb) {
        this.bb = bb;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public Block getBlock() {
        return this.block;
    }

    public static interface Listener {
        public void onCollision(CollisionEvent var1);
    }
}

