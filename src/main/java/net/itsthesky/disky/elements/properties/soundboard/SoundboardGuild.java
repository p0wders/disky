package net.itsthesky.disky.elements.properties.soundboard;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.SoundboardSound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Soundboard Sound Guild")
@Description("Returns the guild a soundboard sound belongs to.")
@Examples("broadcast soundboard sound guild of event-soundboardsound")
public class SoundboardGuild extends SoundboardProperty<Guild> {

    static {
        register(SoundboardGuild.class, Guild.class, "guild");
    }

    @Override
    public @Nullable Guild convert(SoundboardSound sound) {
        return sound.getGuild();
    }

    @Override
    public @NotNull Class<? extends Guild> getReturnType() {
        return Guild.class;
    }
}