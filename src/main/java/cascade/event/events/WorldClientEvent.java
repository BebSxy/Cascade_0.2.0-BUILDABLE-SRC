/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.WorldClient
 */
package cascade.event.events;

import cascade.event.EventStage;
import net.minecraft.client.multiplayer.WorldClient;

public class WorldClientEvent
extends EventStage {
    private final WorldClient client;

    private WorldClientEvent(WorldClient client) {
        this.client = client;
    }

    public WorldClient getClient() {
        return this.client;
    }

    public static class Unload
    extends WorldClientEvent {
        public Unload(WorldClient client) {
            super(client);
        }
    }

    public static class Load
    extends WorldClientEvent {
        public Load(WorldClient client) {
            super(client);
        }
    }
}

