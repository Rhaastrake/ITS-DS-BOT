package org.pisellinigvossi.itsdsbot.utils.interfaces;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

/**
 * Interfaccia che definisce la struttura base di un comando Slash per il bot.
 */
public interface CommandInterface {

    /**
     * Restituisce il nome del comando.
     *
     * @return il nome univoco del comando
     */
    String getName();

    /**
     * Restituisce la descrizione del comando.
     *
     * @return la descrizione da mostrare agli utenti
     */
    String getDescription();

    /**
     * Restituisce la lista delle opzioni (parametri) del comando.
     *
     * @return lista di {@link OptionData} che descrivono i parametri del comando
     */
    List<OptionData> getOptions();

    /**
     * Metodo eseguito quando il comando Slash viene attivato.
     *
     * @param event l'evento generato dall'interazione con il comando
     */
    void execute(SlashCommandInteractionEvent event);
}
