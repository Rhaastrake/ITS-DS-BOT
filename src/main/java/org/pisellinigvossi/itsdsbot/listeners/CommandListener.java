package org.pisellinigvossi.itsdsbot.listeners;

import org.pisellinigvossi.itsdsbot.managers.CommandManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {

    private final CommandManager manager;

    public CommandListener(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        // Ignora eventuali comandi non provenienti da server (opzionale ma consigliato)
        if (!event.isFromGuild()) {
            event.reply("❌ Questo comando può essere usato solo in un server.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        manager.handle(event);
    }
}