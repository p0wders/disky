package net.itsthesky.disky.elements.properties.soundboard;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.dv8tion.jda.api.entities.SoundboardSound;
import org.jetbrains.annotations.NotNull;

public abstract class SoundboardProperty<T> extends SimplePropertyExpression<SoundboardSound, T> {

    public static <T> void register(Class<? extends SoundboardProperty<T>> clazz,
                                    Class<T> entityClass,
                                    String propertyName) {
        register(clazz, entityClass, "soundboard sound " + propertyName, "soundboardsound");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "soundboard sound property";
    }
}