/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.tree.ClassNode
 */
package cascade.util.misc;

import cascade.util.core.AsmUtil;
import java.io.IOException;
import net.minecraft.launchwrapper.Launch;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.tree.ClassNode;

public enum Environment {
    VANILLA,
    SEARGE,
    MCP;

    private static Environment environment;
    private static boolean forge;

    public static Environment getEnvironment() {
        return environment;
    }

    public static boolean hasForge() {
        return forge;
    }

    public static void loadEnvironment() {
        Environment env = SEARGE;
        try {
            String fml = "net.minecraftforge.common.ForgeHooks";
            byte[] forgeBytes = Launch.classLoader.getClassBytes(fml);
            if (forgeBytes != null) {
                forge = true;
            } else {
                env = VANILLA;
            }
        }
        catch (IOException e) {
            env = VANILLA;
        }
        String world = "net.minecraft.world.World";
        byte[] bs = null;
        try {
            bs = Launch.classLoader.getClassBytes(world);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        if (bs != null) {
            ClassNode node = new ClassNode();
            ClassReader reader = new ClassReader(bs);
            reader.accept((ClassVisitor)node, 0);
            if (AsmUtil.findField(node, "loadedEntityList") != null) {
                env = MCP;
            }
        }
        environment = env;
    }
}

