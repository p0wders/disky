package net.itsthesky.disky.elements.properties.soundboard;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import net.dv8tion.jda.api.entities.SoundboardSound;
import net.itsthesky.disky.api.emojis.Emote;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Soundboard Sound Emoji")
@Description("Returns the emoji associated with a soundboard sound, if any.")
@Examples("broadcast soundboard sound emoji of event-soundboardsound")
public class SoundboardEmoji extends SoundboardProperty<Emote> {

    static {
        register(SoundboardEmoji.class, Emote.class, "emoji");
    }

    @Override
    public @Nullable Emote convert(SoundboardSound sound) {
        return Emote.fromUnion(sound.getEmoji());
    }

    @Override
    public @NotNull Class<? extends Emote> getReturnType() {
        return Emote.class;
    }
}