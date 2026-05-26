package net.itsthesky.disky.elements.events.rework;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.SoundboardSound;
import net.dv8tion.jda.api.events.soundboard.GenericSoundboardSoundEvent;
import net.dv8tion.jda.api.events.soundboard.SoundboardSoundCreateEvent;
import net.dv8tion.jda.api.events.soundboard.SoundboardSoundDeleteEvent;
import net.dv8tion.jda.api.events.soundboard.update.SoundboardSoundUpdateEmojiEvent;
import net.dv8tion.jda.api.events.soundboard.update.SoundboardSoundUpdateNameEvent;
import net.dv8tion.jda.api.events.soundboard.update.SoundboardSoundUpdateVolumeEvent;
import net.itsthesky.disky.api.emojis.Emote;
import net.itsthesky.disky.api.events.rework.EventCategory;
import net.itsthesky.disky.api.events.rework.EventRegistryFactory;

@EventCategory(name = "Soundboard Events", description = {
        "These events are fired when a guild's soundboard sounds are created, deleted, or updated.",
})
public class SoundboardEvents {

    static {
        // Soundboard Sound Create Event
        EventRegistryFactory.builder(SoundboardSoundCreateEvent.class)
                .name("Soundboard Sound Create Event")
                .patterns("[discord] soundboard sound creat(e|ion)")
                .description("Fired when a soundboard sound is created in a guild.")
                .example("on soundboard sound creation:\n    broadcast \"%event-soundboardsound% was created in %event-guild%\"")
                .value(Guild.class, GenericSoundboardSoundEvent::getGuild)
                .value(SoundboardSound.class, GenericSoundboardSoundEvent::getSoundboardSound)
                .author(GenericSoundboardSoundEvent::getGuild)
                .register();

        // Soundboard Sound Delete Event
        EventRegistryFactory.builder(SoundboardSoundDeleteEvent.class)
                .name("Soundboard Sound Delete Event")
                .patterns("[discord] soundboard sound delet(e|ion)")
                .description("Fired when a soundboard sound is deleted from a guild.")
                .example("on soundboard sound deletion:\n    broadcast \"%event-soundboardsound% was deleted in %event-guild%\"")
                .value(Guild.class, GenericSoundboardSoundEvent::getGuild)
                .value(SoundboardSound.class, GenericSoundboardSoundEvent::getSoundboardSound)
                .author(GenericSoundboardSoundEvent::getGuild)
                .register();

        // Soundboard Sound Name Update Event
        EventRegistryFactory.builder(SoundboardSoundUpdateNameEvent.class)
                .name("Soundboard Sound Name Update Event")
                .patterns("[discord] soundboard sound name (change|update)")
                .description("Fired when a soundboard sound's name is changed.")
                .example("on soundboard sound name change:\n    broadcast \"%event-soundboardsound% was renamed from %past soundboard name% to %current soundboard name%\"")
                .customTimedExpressions("soundboard name", String.class,
                        SoundboardSoundUpdateNameEvent::getNewValue,
                        SoundboardSoundUpdateNameEvent::getOldValue)
                .value(Guild.class, GenericSoundboardSoundEvent::getGuild)
                .value(SoundboardSound.class, GenericSoundboardSoundEvent::getSoundboardSound)
                .author(GenericSoundboardSoundEvent::getGuild)
                .register();

        // Soundboard Sound Volume Update Event
        EventRegistryFactory.builder(SoundboardSoundUpdateVolumeEvent.class)
                .name("Soundboard Sound Volume Update Event")
                .patterns("[discord] soundboard sound volume (change|update)")
                .description("Fired when a soundboard sound's volume is changed.")
                .example("on soundboard sound volume change:\n    broadcast \"%event-soundboardsound% volume changed from %past soundboard volume% to %current soundboard volume%\"")
                .customTimedExpressions("soundboard volume", Number.class,
                        SoundboardSoundUpdateVolumeEvent::getNewValue,
                        SoundboardSoundUpdateVolumeEvent::getOldValue)
                .value(Guild.class, GenericSoundboardSoundEvent::getGuild)
                .value(SoundboardSound.class, GenericSoundboardSoundEvent::getSoundboardSound)
                .author(GenericSoundboardSoundEvent::getGuild)
                .register();

        // Soundboard Sound Emoji Update Event
        EventRegistryFactory.builder(SoundboardSoundUpdateEmojiEvent.class)
                .name("Soundboard Sound Emoji Update Event")
                .patterns("[discord] soundboard sound emoji (change|update)")
                .description("Fired when a soundboard sound's emoji is changed.")
                .example("on soundboard sound emoji change:\n    broadcast \"%event-soundboardsound% emoji changed from %past soundboard emoji% to %current soundboard emoji%\"")
                .customTimedExpressions("soundboard emoji", Emote.class,
                        evt -> Emote.fromUnion(evt.getNewValue()),
                        evt -> Emote.fromUnion(evt.getOldValue()))
                .value(Guild.class, GenericSoundboardSoundEvent::getGuild)
                .value(SoundboardSound.class, GenericSoundboardSoundEvent::getSoundboardSound)
                .author(GenericSoundboardSoundEvent::getGuild)
                .register();
    }
}