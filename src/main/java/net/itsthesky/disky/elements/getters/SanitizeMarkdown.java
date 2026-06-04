package net.itsthesky.disky.elements.getters;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.utils.MarkdownSanitizer;
import net.dv8tion.jda.api.utils.MarkdownSanitizer.SanitizationStrategy;
import net.itsthesky.disky.api.DiSkyRegistry;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Sanitize Markdown")
@Description({
        "Sanitize Discord markdown in one or more strings.",
        "This is useful when echoing arbitrary user input back into a channel, so the input does not "
                + "render as bold, italics, spoilers, code blocks, etc.",
        "Two modes are available:",
        " - <b>stripped markdown</b>: removes the formatting tokens entirely (<code>**Hello**</code> -> <code>Hello</code>).",
        " - <b>escaped markdown</b>: escapes each markdown character so the tokens are displayed literally "
                + "(<code>**Hello**</code> -> <code>\\*\\*Hello\\*\\*</code>).",
        "You may optionally keep specific regions untouched with <code>keeping %markdownformats%</code> "
                + "(aliased as <code>ignoring</code>), e.g. to strip everything except code blocks.",
        "Note: the underlying sanitizer does not distinguish URLs from normal text, so markdown-looking "
                + "characters inside links will also be stripped/escaped, which may break those links."
})
@Examples({
        "set {_safe} to stripped markdown of event-message's content",
        "reply with escaped markdown of arg-1",
        "set {_safe} to stripped markdown of {_input} keeping code block and quote block"
})
@Since("INSERT VERSION")
public class SanitizeMarkdown extends SimpleExpression<String> {

    static {
        DiSkyRegistry.registerExpression(SanitizeMarkdown.class, String.class, ExpressionType.COMBINED,
                "[the] stripped markdown (of|from) %strings% [(keeping|ignoring) [the] %-markdownformats%]",
                "[the] escaped markdown (of|from) %strings% [(keeping|ignoring) [the] %-markdownformats%]");
    }

    private Expression<String> exprInput;
    private @Nullable Expression<MarkdownFormat> exprKept;
    private boolean escape;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        exprInput = (Expression<String>) exprs[0];
        exprKept = (Expression<MarkdownFormat>) exprs[1];
        escape = matchedPattern == 1;
        return true;
    }

    @Override
    protected String @NotNull [] get(@NotNull Event e) {
        final MarkdownFormat[] kept = exprKept == null ? null : exprKept.getArray(e);
        final int ignored = MarkdownFormat.toBitmask(kept);
        final SanitizationStrategy strategy = escape ? SanitizationStrategy.ESCAPE : SanitizationStrategy.REMOVE;
        final MarkdownSanitizer sanitizer = new MarkdownSanitizer(ignored, strategy);

        final String[] inputs = exprInput.getArray(e);
        final List<String> result = new ArrayList<>(inputs.length);
        for (String input : inputs) {
            if (input == null)
                continue;
            result.add(sanitizer.compute(input));
        }
        return result.toArray(new String[0]);
    }

    @Override
    public boolean isSingle() {
        return exprInput.isSingle();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        final String base = (escape ? "escaped" : "stripped") + " markdown of " + exprInput.toString(e, debug);
        return exprKept == null ? base : base + " keeping " + exprKept.toString(e, debug);
    }
}