package org.pisellinigvossi.itsdsbot.listeners;

import org.pisellinigvossi.itsdsbot.utils.interfaces.CommandInterface;
import org.pisellinigvossi.itsdsbot.managers.CommandManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

/**
 * Listener che gestisce gli eventi di startup del bot relativi ai server Discord (Guild).
 * <p>
 * Quando il bot si connette o entra in un nuovo server, registra (o aggiorna) i comandi slash
 * per ogni server a cui è connesso.
 * </p>
 */
public class StartupListener extends ListenerAdapter {

    /**
     * Gestore dei comandi del bot, utilizzato per recuperare i comandi da registrare.
     */
    private final CommandManager commands;

    public StartupListener(CommandManager commands) {
        this.commands = commands;
    }

    /**
     * Evento chiamato quando un server Discord è pronto (bot connesso e guild caricata).
     * Registra o aggiorna i comandi slash per ogni server a cui il bot è connesso.
     *
     * @param event Evento GuildReady
     */
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {

        for (Guild guild : event.getJDA().getGuilds()) {

            for (CommandInterface command : commands.getCommands()) {

                var options = command.getOptions();

                if (options == null || options.isEmpty()) {
                    guild.upsertCommand(command.getName(), command.getDescription()).queue();
                } else {
                    guild.upsertCommand(
                            Commands.slash(command.getName(), command.getDescription())
                                    .addOptions(options)
                    ).queue();
                }
            }
        }
    }

    /**
     * Evento chiamato quando il bot entra in un nuovo server Discord.
     * Registra o aggiorna i comandi slash per ogni server a cui il bot è connesso.
     *
     * @param event Evento GuildJoin
     */
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        for (Guild discordServer : event.getJDA().getGuilds()) {
            for (CommandInterface comm : commands.getCommands()) {
                discordServer.upsertCommand(comm.getName(), comm.getDescription()).queue();
                if(comm.getOptions() == null) {
                    discordServer.upsertCommand(comm.getName(), comm.getDescription()).queue();
                }
                else {
                    discordServer.upsertCommand(Commands.slash(comm.getName(), comm.getDescription()).addOptions(comm.getOptions())).queue();
                }
            }
        }
    }
}