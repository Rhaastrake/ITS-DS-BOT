package org.pisellinigvossi.itsdsbot.managers;

import org.pisellinigvossi.itsdsbot.commands.*;
import org.pisellinigvossi.itsdsbot.utils.interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final Map<String, CommandInterface> commands = new HashMap<>();

    /**
     * Costruttore che inizializza e registra i comandi disponibili.
     */
    public CommandManager() {
        addCommand(new TestCommand());
        addCommand(new ScapocchioneCommand());
    }

    /**
     * Aggiunge un comando alla mappa dei comandi registrati.
     *
     * @param command il comando da registrare
     */
    public void addCommand(CommandInterface command) {
        commands.put(command.getName(), command);
    }

    /**
     * Restituisce la collezione di comandi registrati.
     *
     * @return collezione dei comandi disponibili
     */
    public Collection<CommandInterface> getCommands() {
        return commands.values();
    }

    /**
     * Gestisce l'evento di interazione con un comando Slash,
     * eseguendo il comando se presente.
     *
     * @param event evento generato dall'interazione con il comando
     */
    public void handle(SlashCommandInteractionEvent event) {
        System.out.println("Comando ricevuto: " + event.getName());
        String commandName = event.getName();
        if (!commands.containsKey(commandName)) {
            event.reply("❌ Comando non presente.").setEphemeral(true).queue();
            return;
        }
        CommandInterface command = commands.get(commandName);
        if (command != null) {
            command.execute(event);
        } else {
            event.reply("❌ Comando non riconosciuto.").setEphemeral(true).queue();
        }
    }
}