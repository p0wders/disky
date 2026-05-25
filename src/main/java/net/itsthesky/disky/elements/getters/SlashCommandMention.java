package net.itsthesky.disky.elements.getters;

import ch.njol.skript.config.Node;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.itsthesky.disky.api.DiSkyRegistry;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Slash Command Mention")
@Description({
        "Builds the clickable mention tag of a slash command from its full name and ID.",
        "When clicked in Discord, it pre-fills the command in the user's text input.",
        "The ID is the registered command's snowflake ID, obtained after registering it with Discord.",
        "For subcommands, provide the *full* command name with spaces, e.g. \"mod ban\" or \"mod action ban\" - "
                + "this matches JDA's getFullCommandName(), which is what the mention format uses.",
        "Note: this only works for slash commands, not user/message context commands, which cannot be mentioned."
})
@Examples({
        "set {_mention} to mention tag of slash command named \"help\" with id \"123456789012345678\"",
        "# inside a slash command trigger, using DiSky's own event expressions:",
        "# 'event-string' is the full command name, 'slash command id' is the registered id",
        "set {_mention} to mention tag of slash command named event-string with id slash command id"
})
@Since("INSERT VERSION")
public class SlashCommandMention extends SimpleExpression<String> {

    static {
        DiSkyRegistry.registerExpression(SlashCommandMention.class, String.class, ExpressionType.COMBINED,
                "mention [tag] of [the] [slash] command named %string% with [discord] id %string/number%");
    }

    private Node node;
    private Expression<String> name;
    private Expression<?> id;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern,
                        @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        id = exprs[1];
        node = getParser().getNode();
        return true;
    }

    @Override
    protected String @Nullable [] get(@NotNull Event e) {
        final String name = this.name.getSingle(e);
        final Object rawId = this.id.getSingle(e);
        if (name == null || rawId == null)
            return new String[0];

        final String id = (rawId instanceof Number)
                ? Long.toString(((Number) rawId).longValue())
                : rawId.toString();

        return new String[] { "</" + name + ":" + id + ">" };
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "mention tag of slash command named " + name.toString(e, debug)
                + " with id " + id.toString(e, debug);
    }
}