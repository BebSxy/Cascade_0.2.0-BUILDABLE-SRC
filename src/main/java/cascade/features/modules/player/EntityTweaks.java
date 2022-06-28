/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MovementInput
 */
package cascade.features.modules.player;

import cascade.features.modules.Module;
import cascade.features.setting.Setting;
import cascade.util.player.MovementUtil;
import net.minecraft.util.MovementInput;

public class EntityTweaks
extends Module {
    private static EntityTweaks INSTANCE;
    public Setting<Boolean> control = this.register(new Setting<Boolean>("Control", true));
    public Setting<Boolean> speedMod = this.register(new Setting<Boolean>("SpeedModifier", false));
    public Setting<Float> speed = this.register(new Setting<Object>("Speed", Float.valueOf(3.0f), Float.valueOf(0.1f), Float.valueOf(10.0f), v -> this.speedMod.getValue()));
    public Setting<Boolean> accelerate = this.register(new Setting<Object>("Accelerate", Boolean.valueOf(false), v -> this.speedMod.getValue()));
    public Setting<Float> acceleration = this.register(new Setting<Object>("Acceleration", Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(2.0f), v -> this.speedMod.getValue() != false && this.accelerate.getValue() != false));
    private long accelerationTime = 0L;

    public EntityTweaks() {
        super("EntityTweaks", Module.Category.PLAYER, "does cool shit");
        this.setInstance();
    }

    public static EntityTweaks getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EntityTweaks();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        this.accelerationTime = System.currentTimeMillis();
    }

    @Override
    public void onUpdate() {
        if (EntityTweaks.fullNullCheck()) {
            return;
        }
        if (EntityTweaks.mc.thePlayer.getRidingEntity() != null && this.isEnabled() && this.speedMod.getValue().booleanValue()) {
            MovementInput movementInput = EntityTweaks.mc.thePlayer.movementInput;
            double forward = movementInput.moveForward;
            double strafe = movementInput.moveStrafe;
            float yaw = EntityTweaks.mc.thePlayer.rotationYaw;
            if (forward == 0.0 && strafe == 0.0) {
                MovementUtil.setMotion(0.0, EntityTweaks.mc.thePlayer.getRidingEntity().motionY, 0.0);
                this.accelerationTime = System.currentTimeMillis();
            } else {
                if (forward != 0.0) {
                    if (strafe > 0.0) {
                        yaw += (float)(forward > 0.0 ? -45 : 45);
                    } else if (strafe < 0.0) {
                        yaw += (float)(forward > 0.0 ? 45 : -45);
                    }
                    strafe = 0.0;
                    if (forward > 0.0) {
                        forward = 1.0;
                    } else if (forward < 0.0) {
                        forward = -1.0;
                    }
                }
                float spd = this.speed.getValue().floatValue();
                if (this.accelerate.getValue().booleanValue()) {
                    spd *= Math.min(1.0f, (float)(System.currentTimeMillis() - this.accelerationTime) / (1000.0f * this.acceleration.getValue().floatValue()));
                }
                double sin = Math.sin(Math.toRadians(yaw + 90.0f));
                double cos = Math.cos(Math.toRadians(yaw + 90.0f));
                MovementUtil.setMotion(forward * (double)spd * cos + strafe * (double)spd * sin, EntityTweaks.mc.thePlayer.getRidingEntity().motionY, forward * (double)spd * sin - strafe * (double)spd * cos);
            }
        }
    }
}

