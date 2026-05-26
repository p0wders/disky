package net.itsthesky.disky.elements.properties.members;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Member Online Status")
@Description({"Returns the online status of a member: online, idle, do not disturb, invisible or offline.",
        "Requires the GUILD_PRESENCES intent and the ONLINE_STATUS cache flag to be enabled - "
                + "without them this always returns offline.",
        "Note: this is the presence status, NOT whether the member is streaming. "
                + "A streaming member appears as an activity of type 'streaming', not an online status."})
@Examples({"if member online status of event-member is online:",
        "    broadcast \"%event-member% is online\""})
@Since("INSERT VERSION")
public class MemberOnlineStatus extends MemberProperty<OnlineStatus> {

    static {
        register(
                MemberOnlineStatus.class,
                OnlineStatus.class,
                "online status[es]"
        );
    }

    @Override
    public @Nullable OnlineStatus convert(Member member) {
        return member.getOnlineStatus();
    }

    @Override
    public @NotNull Class<? extends OnlineStatus> getReturnType() {
        return OnlineStatus.class;
    }
}