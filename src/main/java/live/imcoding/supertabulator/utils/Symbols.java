package live.imcoding.supertabulator.utils;


import com.google.common.collect.ImmutableSet;

public final class Symbols {

    public static final java.util.Set<String> SYMBOLS;

    static {
        ImmutableSet.Builder<String> builder = ImmutableSet.<String>builder();

        builder.add("•", "➤", "™", "↑", "→", "↓", "∞", "░", "▲", "▶", "◀", "●", "★", "☆", "☐", "☑", "☠", "☢", "☣", "☹", "☺", "✓",
                "✔", "✘", "✚", "℻", "✠", "✡", "✦", "✧", "✩", "✪", "✮", "✯", "㋡", "❝", "❞", "ツ", "♩", "♪", "♫", "♬", "♭", "♮", "♯",
                "¶", "\u00A9", "\u00AE", "⏎", "⇧", "⇪", "ᴴᴰ", "☒", "♠", "♣", "☻", "▓", "➾", "➔", "➳", "➧", "《", "》", "︾", "︽",
                "☃", "¹", "²", "³", "≈", "℠", "\u2665", "✬", "↔", "«", "»", "☀", "♦", "₽", "☎", "☂", "←", "↖", "↗", "↘", "↙", "➲",
                "✐", "✎", "✏", "✆", "◄", "☼", "►", "↕", "▼", "①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩", "⑪", "⑫", "⑬", "⑭",
                "⑮", "⑯", "⑰", "⑱", "⑲", "⑳", "♨", "✑", "✖", "✰", "✶", "╗", "╣", "◙", "○", "╠", "┤", "║", "╝", "⌂", "┐", "❉", "⌲",
                "½", "¼", "¾", "⅓", "⅔", "№", "†", "‡", "µ", "¢", "£", "∅", "≤", "≥", "≠", "∧", "∨", "∩", "∪", "∈", "∀", "∃", "∄",
                "∑", "∏", "↺", "↻", "Ω");

        SYMBOLS = builder.build();
    }

    private Symbols() {
    }
}