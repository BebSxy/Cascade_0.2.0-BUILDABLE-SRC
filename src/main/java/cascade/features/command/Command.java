/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentBase
 *  net.minecraft.util.text.TextComponentString
 */
package cascade.features.command;

import cascade.Cascade;
import cascade.features.Feature;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;

public abstract class Command
extends Feature {
    protected String name;
    protected String[] commands;

    public Command(String name) {
        super(name);
        this.name = name;
        this.commands = new String[]{""};
    }

    public Command(String name, String[] commands) {
        super(name);
        this.name = name;
        this.commands = commands;
    }

    public static void sendMessage(String message, Boolean client, Boolean bold) {
        Command.sendSilentMessage((client != false ? Cascade.chatManager.getClientMessage() : "") + (bold != false ? ChatFormatting.BOLD : "") + " " + message);
    }

    public static void sendMessage(String message) {
        Command.sendSilentMessage(Cascade.chatManager.getClientMessage() + " " + message);
    }

    public static void sendRemovableMessage(String message, int id) {
        Command.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(message), id);
    }

    public static void sendSilentMessage(String message) {
        if (Command.nullCheck()) {
            return;
        }
        Command.mc.thePlayer.addChatMessage((ITextComponent)new ChatMessage(message));
    }

    public static String getCommandPrefix() {
        return Cascade.chatManager.getPrefix();
    }

    public abstract void execute(String[] var1);

    @Override
    public String getName() {
        return this.name;
    }

    public String[] getCommands() {
        return this.commands;
    }

    public static class ChatMessage
    extends TextComponentBase {
        private final String text;

        public ChatMessage(String text) {
            Pattern pattern = Pattern.compile("&[0123456789abcdefrlosmk]");
            Matcher matcher = pattern.matcher(text);
            StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                String replacement = matcher.group().substring(1);
                matcher.appendReplacement(stringBuffer, replacement);
            }
            matcher.appendTail(stringBuffer);
            this.text = stringBuffer.toString();
        }

        public String getUnformattedTextForChat() {
            return this.text;
        }

        public ITextComponent createCopy() {
            return null;
        }

        public ITextComponent shallowCopy() {
            return new ChatMessage(this.text);
        }
    }
}

