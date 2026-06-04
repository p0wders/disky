package net.itsthesky.disky.elements.getters;

import net.dv8tion.jda.api.utils.MarkdownSanitizer;

/**
 * A Skript-friendly enum that maps each JDA {@link MarkdownSanitizer} region flag to a single
 * selectable value. This lets users pick which markdown regions to keep when stripping or escaping
 * markdown, without having to deal with the underlying integer bitmask.
 *
 * @author DiSky contributors
 */
public enum MarkdownFormat {

    BOLD(MarkdownSanitizer.BOLD),
    ITALICS_ASTERISK(MarkdownSanitizer.ITALICS_A),
    ITALICS_UNDERSCORE(MarkdownSanitizer.ITALICS_U),
    UNDERLINE(MarkdownSanitizer.UNDERLINE),
    STRIKETHROUGH(MarkdownSanitizer.STRIKE),
    SPOILER(MarkdownSanitizer.SPOILER),
    MONOSPACE(MarkdownSanitizer.MONO),
    MONOSPACE_DOUBLE(MarkdownSanitizer.MONO_TWO),
    CODE_BLOCK(MarkdownSanitizer.BLOCK),
    QUOTE(MarkdownSanitizer.QUOTE),
    QUOTE_BLOCK(MarkdownSanitizer.QUOTE_BLOCK);

    private final int flag;

    MarkdownFormat(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    /**
     * Combines the given formats into a single bitmask understood by {@link MarkdownSanitizer}.
     * Returns {@link MarkdownSanitizer#NORMAL} (0) when the array is null or empty.
     *
     * @param formats the formats to combine (null entries are skipped)
     * @return the combined bitmask of all provided region flags
     */
    public static int toBitmask(MarkdownFormat... formats) {
        if (formats == null)
            return MarkdownSanitizer.NORMAL;
        int mask = MarkdownSanitizer.NORMAL;
        for (MarkdownFormat format : formats)
            if (format != null)
                mask |= format.getFlag();
        return mask;
    }
}