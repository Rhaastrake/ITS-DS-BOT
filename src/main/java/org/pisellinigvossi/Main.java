package org.pisellinigvossi;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.pisellinigvossi.gui.GUI;
import org.pisellinigvossi.itsdsbot.listeners.CommandListener;
import org.pisellinigvossi.itsdsbot.listeners.StartupListener;
import org.pisellinigvossi.itsdsbot.managers.CommandManager;

public class Main {

    public static void main(String[] args) throws Exception {

        GUI.createAndShowGUI();
        System.out.println("Avvio in corso...");

        Dotenv config = Dotenv.configure().load();
        String token = config.get("DISCORD_TOKEN");

        // UNA sola istanza condivisa
        CommandManager commandManager = new CommandManager();

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(
                        new CommandListener(commandManager),
                        new StartupListener(commandManager)
                )
                .build();

        jda.awaitReady();

        System.out.println("Bot partito");
    }
}