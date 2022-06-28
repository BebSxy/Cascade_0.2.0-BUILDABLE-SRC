/*
 * Decompiled with CFR 0.151.
 */
package cascade.util.player;

import cascade.mixin.mixins.accessor.IEntityLivingBase;
import cascade.mixin.mixins.accessor.IEntityPlayerSP;
import cascade.util.Util;

public class PhysicsUtil
implements Util {
    public static void runPhysicsTick() {
        int lastSwing = ((IEntityLivingBase)PhysicsUtil.mc.thePlayer).getTicksSinceLastSwing();
        int useCount = ((IEntityLivingBase)PhysicsUtil.mc.thePlayer).getActiveItemStackUseCount();
        int hurtTime = PhysicsUtil.mc.thePlayer.hurtTime;
        float prevSwingProgress = PhysicsUtil.mc.thePlayer.prevSwingProgress;
        float swingProgress = PhysicsUtil.mc.thePlayer.swingProgress;
        int swingProgressInt = PhysicsUtil.mc.thePlayer.swingProgressInt;
        boolean isSwingInProgress = PhysicsUtil.mc.thePlayer.isSwingInProgress;
        float rotationYaw = PhysicsUtil.mc.thePlayer.rotationYaw;
        float prevRotationYaw = PhysicsUtil.mc.thePlayer.prevRotationYaw;
        float renderYawOffset = PhysicsUtil.mc.thePlayer.renderYawOffset;
        float prevRenderYawOffset = PhysicsUtil.mc.thePlayer.prevRenderYawOffset;
        float rotationYawHead = PhysicsUtil.mc.thePlayer.rotationYawHead;
        float prevRotationYawHead = PhysicsUtil.mc.thePlayer.prevRotationYawHead;
        float cameraYaw = PhysicsUtil.mc.thePlayer.cameraYaw;
        float prevCameraYaw = PhysicsUtil.mc.thePlayer.prevCameraYaw;
        float renderArmYaw = PhysicsUtil.mc.thePlayer.renderArmYaw;
        float prevRenderArmYaw = PhysicsUtil.mc.thePlayer.prevRenderArmYaw;
        float renderArmPitch = PhysicsUtil.mc.thePlayer.renderArmPitch;
        float prevRenderArmPitch = PhysicsUtil.mc.thePlayer.prevRenderArmPitch;
        float walk = PhysicsUtil.mc.thePlayer.distanceWalkedModified;
        float prevWalk = PhysicsUtil.mc.thePlayer.prevDistanceWalkedModified;
        double chasingPosX = PhysicsUtil.mc.thePlayer.chasingPosX;
        double prevChasingPosX = PhysicsUtil.mc.thePlayer.prevChasingPosX;
        double chasingPosY = PhysicsUtil.mc.thePlayer.chasingPosY;
        double prevChasingPosY = PhysicsUtil.mc.thePlayer.prevChasingPosY;
        double chasingPosZ = PhysicsUtil.mc.thePlayer.chasingPosZ;
        double prevChasingPosZ = PhysicsUtil.mc.thePlayer.prevChasingPosZ;
        float limbSwingAmount = PhysicsUtil.mc.thePlayer.limbSwingAmount;
        float prevLimbSwingAmount = PhysicsUtil.mc.thePlayer.prevLimbSwingAmount;
        float limbSwing = PhysicsUtil.mc.thePlayer.limbSwing;
        ((IEntityPlayerSP)PhysicsUtil.mc.thePlayer).superUpdate();
        ((IEntityLivingBase)PhysicsUtil.mc.thePlayer).setTicksSinceLastSwing(lastSwing);
        ((IEntityLivingBase)PhysicsUtil.mc.thePlayer).setActiveItemStackUseCount(useCount);
        PhysicsUtil.mc.thePlayer.hurtTime = hurtTime;
        PhysicsUtil.mc.thePlayer.prevSwingProgress = prevSwingProgress;
        PhysicsUtil.mc.thePlayer.swingProgress = swingProgress;
        PhysicsUtil.mc.thePlayer.swingProgressInt = swingProgressInt;
        PhysicsUtil.mc.thePlayer.isSwingInProgress = isSwingInProgress;
        PhysicsUtil.mc.thePlayer.rotationYaw = rotationYaw;
        PhysicsUtil.mc.thePlayer.prevRotationYaw = prevRotationYaw;
        PhysicsUtil.mc.thePlayer.renderYawOffset = renderYawOffset;
        PhysicsUtil.mc.thePlayer.prevRenderYawOffset = prevRenderYawOffset;
        PhysicsUtil.mc.thePlayer.rotationYawHead = rotationYawHead;
        PhysicsUtil.mc.thePlayer.prevRotationYawHead = prevRotationYawHead;
        PhysicsUtil.mc.thePlayer.cameraYaw = cameraYaw;
        PhysicsUtil.mc.thePlayer.prevCameraYaw = prevCameraYaw;
        PhysicsUtil.mc.thePlayer.renderArmYaw = renderArmYaw;
        PhysicsUtil.mc.thePlayer.prevRenderArmYaw = prevRenderArmYaw;
        PhysicsUtil.mc.thePlayer.renderArmPitch = renderArmPitch;
        PhysicsUtil.mc.thePlayer.prevRenderArmPitch = prevRenderArmPitch;
        PhysicsUtil.mc.thePlayer.distanceWalkedModified = walk;
        PhysicsUtil.mc.thePlayer.prevDistanceWalkedModified = prevWalk;
        PhysicsUtil.mc.thePlayer.chasingPosX = chasingPosX;
        PhysicsUtil.mc.thePlayer.prevChasingPosX = prevChasingPosX;
        PhysicsUtil.mc.thePlayer.chasingPosY = chasingPosY;
        PhysicsUtil.mc.thePlayer.prevChasingPosY = prevChasingPosY;
        PhysicsUtil.mc.thePlayer.chasingPosZ = chasingPosZ;
        PhysicsUtil.mc.thePlayer.prevChasingPosZ = prevChasingPosZ;
        PhysicsUtil.mc.thePlayer.limbSwingAmount = limbSwingAmount;
        PhysicsUtil.mc.thePlayer.prevLimbSwingAmount = prevLimbSwingAmount;
        PhysicsUtil.mc.thePlayer.limbSwing = limbSwing;
        ((IEntityPlayerSP)PhysicsUtil.mc.thePlayer).invokeOnUpdateWalkingPlayer();
    }
}

