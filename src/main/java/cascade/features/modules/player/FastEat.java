/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemBucketMilk
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemPotion
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package cascade.features.modules.player;

import cascade.event.events.PacketEvent;
import cascade.features.modules.Module;
import cascade.features.setting.Setting;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FastEat
extends Module {
    public Setting<Mode> mode = this.register(new Setting<Mode>("Mode", Mode.Packet));
    public Setting<Float> speed = this.register(new Setting<Float>("Speed", Float.valueOf(15.0f), Float.valueOf(1.0f), Float.valueOf(25.0f)));
    public Setting<Boolean> cancel = this.register(new Setting<Boolean>("Cancel", false));
    Setting<Integer> runs = this.register(new Setting<Object>("Runs", Integer.valueOf(32), Integer.valueOf(1), Integer.valueOf(64), v -> this.mode.getValue() == Mode.Packet));
    private static FastEat INSTANCE;

    public FastEat() {
        super("FastEat", Module.Category.PLAYER, "Exploits that make you fat");
        this.setInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public static FastEat getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FastEat();
        }
        return INSTANCE;
    }

    boolean isValid(ItemStack stack) {
        return stack != null && FastEat.mc.thePlayer.isHandActive() && (stack.getItem() instanceof ItemFood || stack.getItem() instanceof ItemPotion || stack.getItem() instanceof ItemBucketMilk);
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send e) {
        CPacketPlayerDigging packet;
        if (this.isDisabled() || FastEat.fullNullCheck()) {
            return;
        }
        if (this.cancel.getValue().booleanValue() && FastEat.mc.thePlayer.getActiveItemStack().getItem() instanceof ItemFood && e.getPacket() instanceof CPacketPlayerDigging && (packet = (CPacketPlayerDigging)e.getPacket()).getAction() == CPacketPlayerDigging.Action.RELEASE_USE_ITEM && packet.getFacing() == EnumFacing.DOWN && packet.getPosition().equals((Object)BlockPos.ORIGIN)) {
            e.setCanceled(true);
        }
        if (this.mode.getValue() == Mode.Update && this.isValid(FastEat.mc.thePlayer.getHeldItem(FastEat.mc.thePlayer.getActiveHand())) && e.getPacket() instanceof CPacketPlayerTryUseItem) {
            mc.getNetHandler().addToSendQueue((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
        }
    }

    @Override
    public void onUpdate() {
        if (FastEat.fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == Mode.Update && this.isValid(FastEat.mc.thePlayer.getActiveItemStack())) {
            EnumHand hand = FastEat.mc.thePlayer.getActiveHand();
            if (hand == null) {
                hand = FastEat.mc.thePlayer.getHeldItemOffhand().equals(FastEat.mc.thePlayer.getActiveItemStack()) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
            }
            mc.getNetHandler().addToSendQueue((Packet)new CPacketPlayerTryUseItem(hand));
        } else if (this.mode.getValue() == Mode.Packet && this.isValid(FastEat.mc.thePlayer.getActiveItemStack()) && (float)FastEat.mc.thePlayer.getItemInUseMaxCount() > this.speed.getValue().floatValue() - 1.0f && this.speed.getValue().floatValue() < 25.0f) {
            for (int i = 0; i < this.runs.getValue(); ++i) {
                mc.getNetHandler().addToSendQueue((Packet)new CPacketPlayer(FastEat.mc.thePlayer.onGround));
            }
            mc.getNetHandler().addToSendQueue((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
            FastEat.mc.thePlayer.stopActiveHand();
        }
    }

    public static enum Mode {
        NoDelay,
        Update,
        Packet;

    }
}

