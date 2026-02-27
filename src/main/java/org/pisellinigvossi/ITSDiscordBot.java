package org.pisellinigvossi;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.pisellinigvossi.itsdsbot.managers.CommandManager;

public class ITSDiscordBot {

    private final ShardManager shardManager;

    public ITSDiscordBot(Dotenv config) {
        // Legge il token dal .env
        String token = config.get("DISCORD_TOKEN");

        // Builder minimale: solo intent per leggere/inviare messaggi
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token, GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.VOICE_STATE);

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("Sto scapocchiando"));

        // Costruisce lo shard manager
        shardManager = builder.build();

        // Inizializza i comandi e li registra su Discord
        CommandManager commandManager = new CommandManager();
        registerSlashCommands(commandManager);
    }

    /**
     * Registra tutti i comandi presenti in CommandManager su Discord come comandi slash.
     */
    private void registerSlashCommands(CommandManager commandManager) {
        shardManager.getShards().forEach(jda ->
                jda.updateCommands()
                        .addCommands(
                                commandManager.getCommands().stream()
                                        .map(cmd -> Commands.slash(cmd.getName(), cmd.getDescription()))
                                        .toList()
                        )
                        .queue()
        );
    }

    public ShardManager getShardManager() {
        return shardManager;
    }
}