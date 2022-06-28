/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.Display
 */
package cascade;

import cascade.features.modules.core.ClientManagement;
import cascade.manager.ChatManager;
import cascade.manager.ColorManager;
import cascade.manager.ConfigManager;
import cascade.manager.EventManager;
import cascade.manager.FriendManager;
import cascade.manager.HoleManager;
import cascade.manager.InventoryManager;
import cascade.manager.ModuleManager;
import cascade.manager.PacketManager;
import cascade.manager.PopManager;
import cascade.manager.PositionManager;
import cascade.manager.PotionManager;
import cascade.manager.RotationManager;
import cascade.manager.ServerManager;
import cascade.manager.SpeedManager;
import cascade.manager.TextManager;
import cascade.manager.TimerManager;
import cascade.util.core.DumpUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid="cascade", name="Cascade", version="0.2.0")
public class Cascade {
    public static final String MODNAME = "Cascade";
    public static final String MODVER = "0.2.0";
    public static final Logger LOGGER = LogManager.getLogger((String)"Cascade");
    public static ChatManager chatManager;
    public static FriendManager friendManager;
    public static ModuleManager moduleManager;
    public static ColorManager colorManager;
    public static PopManager popManager;
    public static InventoryManager inventoryManager;
    public static PacketManager packetManager;
    public static PotionManager potionManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;
    public static SpeedManager speedManager;
    public static ConfigManager configManager;
    public static ServerManager serverManager;
    public static EventManager eventManager;
    public static TimerManager timerManager;
    public static HoleManager holeManager;
    public static TextManager textManager;
    public static Minecraft mc;
    @Mod.Instance
    public static Cascade INSTANCE;
    private static boolean unloaded;

    public static void load() {
        LOGGER.info("\n\nLoading Cascade");
        unloaded = false;
        textManager = new TextManager();
        chatManager = new ChatManager();
        friendManager = new FriendManager();
        moduleManager = new ModuleManager();
        rotationManager = new RotationManager();
        eventManager = new EventManager();
        timerManager = new TimerManager();
        speedManager = new SpeedManager();
        potionManager = new PotionManager();
        inventoryManager = new InventoryManager();
        packetManager = new PacketManager();
        serverManager = new ServerManager();
        holeManager = new HoleManager();
        colorManager = new ColorManager();
        popManager = new PopManager();
        positionManager = new PositionManager();
        configManager = new ConfigManager();
        moduleManager.init();
        configManager.init();
        eventManager.init();
        textManager.init(true);
        moduleManager.onLoad();
        packetManager.load();
        LOGGER.info("Cascade successfully loaded!\n");
    }

    public static void onUnload() {
        if (!unloaded) {
            eventManager.onUnload();
            timerManager.unload();
            moduleManager.onUnload();
            configManager.saveConfig(Cascade.configManager.config.replaceFirst("cascade/", ""));
            moduleManager.onUnloadPost();
            packetManager.unload();
            unloaded = true;
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        Display.setTitle((String)(ClientManagement.getInstance().title.getValueAsString() == null ? "Cascade 0.2.0" : ClientManagement.getInstance().title.getValueAsString()));
        Cascade.load();
    }

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent e) {
        DumpUtil.check();
    }

    static {
        mc = Minecraft.getMinecraft();
        unloaded = false;
    }
}

