package net.itsthesky.disky.elements.properties.guilds;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.SoundboardSound;

@Name("All Guild Soundboard Sounds")
@Description("Returns all cached soundboard sounds of a guild.")
@Examples("all soundboard sounds of event-guild")
public class GuildSoundboardSounds extends MultipleGuildProperty<SoundboardSound> {

    static {
        register(GuildSoundboardSounds.class,
                SoundboardSound.class,
                "[all] soundboard sounds");
    }

    @Override
    public SoundboardSound[] converting(Guild guild) {
        return guild.getSoundboardSounds().toArray(new SoundboardSound[0]);
    }
}