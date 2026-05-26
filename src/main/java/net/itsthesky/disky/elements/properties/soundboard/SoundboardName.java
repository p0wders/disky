package net.itsthesky.disky.elements.properties.soundboard;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import net.dv8tion.jda.api.entities.SoundboardSound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Soundboard Sound Name")
@Description("Returns the name of a soundboard sound.")
@Examples("broadcast soundboard sound name of event-soundboardsound")
public class SoundboardName extends SoundboardProperty<String> {

    static {
        register(SoundboardName.class, String.class, "name");
    }

    @Override
    public @Nullable String convert(SoundboardSound sound) {
        return sound.getName();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }
}