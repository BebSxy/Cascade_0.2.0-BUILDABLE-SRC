/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package cascade.mixin.mixins;

import cascade.features.modules.misc.TrueDurability;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ItemStack.class})
public abstract class MixinItemStack {
    @Shadow
    private int metadata;

    @Inject(method={"<init>(Lnet/minecraft/item/Item;IILnet/minecraft/nbt/NBTTagCompound;)V"}, at={@At(value="RETURN")})
    @Dynamic
    private void initHook(Item item, int ilove0x22, int dura, NBTTagCompound compound, CallbackInfo info) {
        this.metadata = this.checkDurability((ItemStack)ItemStack.class.cast(this), this.metadata, dura);
    }

    @Inject(method={"<init>(Lnet/minecraft/nbt/NBTTagCompound;)V"}, at={@At(value="RETURN")})
    private void initHook2(NBTTagCompound compound, CallbackInfo info) {
        this.metadata = this.checkDurability((ItemStack)ItemStack.class.cast(this), this.metadata, compound.getShort("Damage"));
    }

    private int checkDurability(ItemStack item, int damage, int meta) {
        int trueDura = damage;
        if (TrueDurability.getInstance().isEnabled() && meta < 0) {
            trueDura = meta;
        }
        return trueDura;
    }
}

