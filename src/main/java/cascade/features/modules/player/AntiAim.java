/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package cascade.features.modules.player;

import cascade.event.events.PacketEvent;
import cascade.features.modules.Module;
import cascade.features.setting.Setting;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiAim
extends Module {
    Setting<Mode> mode = this.register(new Setting<Mode>("Mode", Mode.Spin));
    Setting<Integer> spinSpeed = this.register(new Setting<Object>("SpinSpeed", Integer.valueOf(10), Integer.valueOf(0), Integer.valueOf(50), v -> this.mode.getValue() == Mode.Spin));
    Setting<Boolean> clientside = this.register(new Setting<Boolean>("ClientSide", false));
    int nextValue;

    public AntiAim() {
        super("AntiAim", Module.Category.PLAYER, "ion kno");
    }

    @Override
    public void onUpdate() {
        this.nextValue += this.spinSpeed.getValue().intValue();
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send e) {
        if (this.isDisabled()) {
            return;
        }
        if (e.getPacket() instanceof CPacketPlayer && !AntiAim.mc.thePlayer.isHandActive() && !AntiAim.mc.gameSettings.keyBindUseItem.getIsKeyPressed()) {
            if (this.mode.getValue() == Mode.Spin) {
                ((CPacketPlayer)e.getPacket()).yaw = this.nextValue;
                ((CPacketPlayer)e.getPacket()).pitch = this.nextValue;
            } else {
                double cos = Math.cos(Math.toRadians(AntiAim.mc.thePlayer.rotationYaw + 90.0f));
                double sin = Math.sin(Math.toRadians(AntiAim.mc.thePlayer.rotationYaw + 90.0f));
                ((CPacketPlayer)e.getPacket()).yaw = this.angleCalc();
                if (this.clientside.getValue().booleanValue()) {
                    AntiAim.mc.thePlayer.rotationYaw = this.angleCalc();
                }
            }
        }
    }

    float angleCalc() {
        double forward = AntiAim.mc.thePlayer.movementInput.moveForward;
        double strafe = AntiAim.mc.thePlayer.movementInput.moveStrafe;
        float yaw = AntiAim.mc.thePlayer.rotationYaw;
        if (forward != 0.0 && strafe != 0.0) {
            if (strafe > 0.0) {
                return yaw += (float)(forward > 0.0 ? -45 : 45);
            }
            if (strafe < 0.0) {
                return yaw += (float)(forward > 0.0 ? 45 : -45);
            }
            if (forward > 0.0) {
                forward = 1.0;
            }
            if (forward < 0.0) {
                forward = -1.0;
            }
        }
        return AntiAim.mc.thePlayer.rotationYaw;
    }

    public static enum Mode {
        Spin,
        FakeAngle;

    }
}

