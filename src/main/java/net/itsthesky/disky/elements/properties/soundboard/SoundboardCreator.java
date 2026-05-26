package net.itsthesky.disky.elements.properties.soundboard;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import net.dv8tion.jda.api.entities.SoundboardSound;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Soundboard Sound Creator")
@Description("Returns the user who created a soundboard sound. May not be set if the user is uncached or the bot lacks permission to view it.")
@Examples("broadcast soundboard sound creator of event-soundboardsound")
public class SoundboardCreator extends SoundboardProperty<User> {

    static {
        register(SoundboardCreator.class, User.class, "creator");
    }

    @Override
    public @Nullable User convert(SoundboardSound sound) {
        return sound.getUser();
    }

    @Override
    public @NotNull Class<? extends User> getReturnType() {
        return User.class;
    }
}