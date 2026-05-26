package net.itsthesky.disky.elements.properties.soundboard;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import net.dv8tion.jda.api.entities.SoundboardSound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Soundboard Sound URL")
@Description("Returns the CDN URL of a soundboard sound's audio file.")
@Examples("broadcast soundboard sound url of event-soundboardsound")
public class SoundboardURL extends SoundboardProperty<String> {

    static {
        register(SoundboardURL.class, String.class, "url");
    }

    @Override
    public @Nullable String convert(SoundboardSound sound) {
        return sound.getUrl();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }
}