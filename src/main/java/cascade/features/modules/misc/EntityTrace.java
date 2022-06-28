/*
 * Decompiled with CFR 0.151.
 */
package cascade.features.modules.misc;

import cascade.features.modules.Module;
import cascade.features.setting.Setting;

public class EntityTrace
extends Module {
    private static EntityTrace INSTANCE;
    public Setting<Boolean> pickaxe = this.register(new Setting<Boolean>("Pickaxe", true));
    public Setting<Boolean> crystal = this.register(new Setting<Boolean>("Crystal", true));
    public Setting<Boolean> gapple = this.register(new Setting<Boolean>("Gapple", true));

    public EntityTrace() {
        super("EntityTrace", Module.Category.MISC, "Removes entities hitboxes");
        INSTANCE = this;
    }

    public static EntityTrace getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new EntityTrace();
        }
        return INSTANCE;
    }
}

