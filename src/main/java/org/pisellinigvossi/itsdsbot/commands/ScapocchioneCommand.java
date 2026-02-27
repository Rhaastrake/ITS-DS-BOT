package org.pisellinigvossi.itsdsbot.commands;

import org.pisellinigvossi.itsdsbot.utils.interfaces.CommandInterface;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class ScapocchioneCommand implements CommandInterface {

    @Override
    public String getName() {
        return "scapocchione";
    }

    @Override
    public String getDescription() {
        return "Dai dello scapocchione a qualcuno";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.USER, "utente", "L'utente da insultare", true)
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member target = event.getOption("utente").getAsMember();

        if (target == null) {
            event.reply("Utente non valido").setEphemeral(true).queue();
            return;
        }

        event.reply(target.getAsMention() + ", sei uno scapocchione!").queue();
    }

    public void executeByEvent(Guild guild, AudioChannel channel) {
        //
    }
}