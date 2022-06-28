/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package cascade.manager;

import cascade.event.events.PacketEvent;
import cascade.features.Feature;
import cascade.util.player.RotationUtil;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PositionManager
extends Feature {
    private double x;
    private double y;
    private double z;
    private boolean onground;
    private volatile int teleportID;

    @SubscribeEvent
    void onPacketReceive(PacketEvent.Receive e) {
        SPacketPlayerPosLook packet = (SPacketPlayerPosLook)e.getPacket();
        this.teleportID = packet.getTeleportId();
    }

    public void updatePosition() {
        this.x = PositionManager.mc.thePlayer.posX;
        this.y = PositionManager.mc.thePlayer.posY;
        this.z = PositionManager.mc.thePlayer.posZ;
        this.onground = PositionManager.mc.thePlayer.onGround;
    }

    public void restorePosition() {
        PositionManager.mc.thePlayer.posX = this.x;
        PositionManager.mc.thePlayer.posY = this.y;
        PositionManager.mc.thePlayer.posZ = this.z;
        PositionManager.mc.thePlayer.onGround = this.onground;
    }

    public void setPlayerPosition(double x, double y, double z) {
        PositionManager.mc.thePlayer.posX = x;
        PositionManager.mc.thePlayer.posY = y;
        PositionManager.mc.thePlayer.posZ = z;
    }

    public void setPlayerPosition(double x, double y, double z, boolean onground) {
        PositionManager.mc.thePlayer.posX = x;
        PositionManager.mc.thePlayer.posY = y;
        PositionManager.mc.thePlayer.posZ = z;
        PositionManager.mc.thePlayer.onGround = onground;
    }

    public void setPositionPacket(double x, double y, double z, boolean onGround, boolean setPos, boolean noLagBack) {
        PositionManager.mc.thePlayer.sendQueue.addToSendQueue((Packet)new CPacketPlayer.Position(x, y, z, onGround));
        if (setPos) {
            PositionManager.mc.thePlayer.setPosition(x, y, z);
            if (noLagBack) {
                this.updatePosition();
            }
        }
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public static BlockPos getPosition() {
        return PositionManager.getPosition((Entity)RotationUtil.getRotationPlayer());
    }

    public static BlockPos getPosition(Entity entity) {
        return PositionManager.getPosition(entity, 0.0);
    }

    public static BlockPos getPosition(Entity entity, double yOffset) {
        double y = entity.posY + yOffset;
        if (entity.posY - Math.floor(entity.posY) > 0.5) {
            y = Math.ceil(entity.posY);
        }
        return new BlockPos(entity.posX, y, entity.posZ);
    }

    public int getTeleportID() {
        return this.teleportID;
    }
}

