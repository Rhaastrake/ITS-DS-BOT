package org.pisellinigvossi.itsdsbot.commands;

import org.pisellinigvossi.itsdsbot.utils.interfaces.CommandInterface;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class TestCommand implements CommandInterface {

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getDescription() {
        return "Il bot risponderà con un messaggio di test";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of();
    }

    // Eseguito dal comando slash
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Messaggio di test riuscito").queue();
    }

    // Metodo chiamabile anche da EventListener
    public void executeByEvent(Guild guild, AudioChannel channel) {
        //
    }
}