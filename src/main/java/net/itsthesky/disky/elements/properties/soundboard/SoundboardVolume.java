package net.itsthesky.disky.elements.properties.soundboard;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import net.dv8tion.jda.api.entities.SoundboardSound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Soundboard Sound Volume")
@Description("Returns the volume of a soundboard sound, as a number between 0 and 1.")
@Examples("broadcast soundboard sound volume of event-soundboardsound")
public class SoundboardVolume extends SoundboardProperty<Number> {

    static {
        register(SoundboardVolume.class, Number.class, "volume");
    }

    @Override
    public @Nullable Number convert(SoundboardSound sound) {
        return sound.getVolume();
    }

    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }
}