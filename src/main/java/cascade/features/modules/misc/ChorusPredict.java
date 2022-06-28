/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package cascade.features.modules.misc;

import cascade.event.events.PacketEvent;
import cascade.event.events.Render3DEvent;
import cascade.features.modules.Module;
import cascade.features.setting.Setting;
import cascade.util.misc.Timer;
import cascade.util.render.RenderUtil;
import java.awt.Color;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChorusPredict
extends Module {
    Setting<Integer> removeDelay = this.register(new Setting<Integer>("RemoveDelay", 4000, 0, 4000));
    Setting<Boolean> text = this.register(new Setting<Boolean>("Text", false));
    Setting<Boolean> box = this.register(new Setting<Boolean>("Box", true));
    Setting<Boolean> tracer = this.register(new Setting<Boolean>("Tracer", false));
    Setting<Color> c = this.register(new Setting<Object>("Color", new Color(-1), v -> this.box.getValue()));
    Setting<Boolean> outline = this.register(new Setting<Boolean>("Outline", true));
    Setting<Float> outlineWidth = this.register(new Setting<Object>("Width", Float.valueOf(1.5f), Float.valueOf(0.1f), Float.valueOf(3.0f), v -> this.outline.getValue()));
    Timer renderTimer = new Timer();
    BlockPos pos;
    private static ChorusPredict INSTANCE;

    public ChorusPredict() {
        super("ChorusPredict", Module.Category.MISC, "Predicts chorus pos");
        this.setInstance();
    }

    public static ChorusPredict getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ChorusPredict();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive e) {
        SPacketSoundEffect packet;
        if (e.getPacket() instanceof SPacketSoundEffect && this.isEnabled() && ((packet = (SPacketSoundEffect)e.getPacket()).getSound() == SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT || packet.getSound() == SoundEvents.ENTITY_ENDERMEN_TELEPORT)) {
            this.renderTimer.reset();
            this.pos = new BlockPos(packet.getX(), packet.getY(), packet.getZ());
        }
    }

    @Override
    public void onRender3D(Render3DEvent e) {
        if (this.pos != null) {
            if (this.renderTimer.passedMs(this.removeDelay.getValue().intValue())) {
                this.renderTimer.reset();
                this.pos = null;
                return;
            }
            if (this.box.getValue().booleanValue()) {
                RenderUtil.drawBoxESP(this.pos, new Color(this.c.getValue().getRed(), this.c.getValue().getGreen(), this.c.getValue().getBlue(), this.c.getValue().getAlpha()), this.outlineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.c.getValue().getAlpha());
            }
            if (this.text.getValue().booleanValue()) {
                RenderUtil.drawText(this.pos, "Player Teleported");
            }
            if (this.tracer.getValue().booleanValue()) {
                RenderUtil.drawLineFromPosToPos(ChorusPredict.mc.thePlayer.posX, ChorusPredict.mc.thePlayer.posY, ChorusPredict.mc.thePlayer.posZ, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 0.0, this.c.getValue().getRed(), this.c.getValue().getGreen(), this.c.getValue().getBlue(), this.c.getValue().getAlpha());
            }
        }
    }

    @Override
    public void onDisable() {
        this.renderTimer.reset();
        this.pos = null;
    }
}

