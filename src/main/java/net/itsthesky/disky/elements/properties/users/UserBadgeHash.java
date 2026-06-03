package net.itsthesky.disky.elements.properties.users;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

@Name("User Badge Hash")
@Description({
        "Get the badge hash of the primary guild of a user. This will return a long string.",
        "May be null if the user has no primary guild/no selected tag"
})
@Examples({
        "set {_tag} to user badge hash of event-user"
})
@Since("INSERT VERSION")
public class UserBadgeHash extends SimplePropertyExpression<User, String> {

    static {
        register(
                UserBadgeHash.class,
                String.class,
                "user [primary guild] badge hash",
                "user"
        );
    }

    @Override
    public @Nullable String convert(User user) {
        return user.getPrimaryGuild() == null ? null : user.getPrimaryGuild().getBadgeHash();
    }

    @Override
    protected String getPropertyName() {
        return "user badge hash text";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

}
