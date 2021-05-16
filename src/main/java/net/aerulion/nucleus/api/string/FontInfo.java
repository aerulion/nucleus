package net.aerulion.nucleus.api.string;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public enum FontInfo {
    AMPERSAND('&', 5),
    ASTERISK('*', 5),
    AT_SYMBOL('@', 6),
    BACK_SLASH('\\', 5),
    COLON(':', 1),
    COMMA(',', 1),
    DEFAULT('a', 4),
    DOLLAR_SIGN('$', 5),
    DOUBLE_QUOTE('"', 3),
    EQUALS_SIGN('=', 5),
    ESZETT('ß', 5),
    EURO_SIGN('€', 6),
    EXCLAMATION_POINT('!', 1),
    LEFT_ARROW('<', 4),
    LEFT_BRACKET('[', 3),
    LEFT_CURL_BRACE('{', 4),
    LEFT_PARENTHESIS('(', 4),
    LETTER_A('A', 5),
    LETTER_AE('Ä', 5),
    LETTER_B('B', 5),
    LETTER_C('C', 5),
    LETTER_D('D', 5),
    LETTER_E('E', 5),
    LETTER_F('F', 5),
    LETTER_G('G', 5),
    LETTER_H('H', 5),
    LETTER_I('I', 3),
    LETTER_J('J', 5),
    LETTER_K('K', 5),
    LETTER_L('L', 5),
    LETTER_M('M', 5),
    LETTER_N('N', 5),
    LETTER_O('O', 5),
    LETTER_OE('Ö', 5),
    LETTER_P('P', 5),
    LETTER_Q('Q', 5),
    LETTER_R('R', 5),
    LETTER_S('S', 5),
    LETTER_T('T', 5),
    LETTER_U('U', 5),
    LETTER_UE('Ü', 5),
    LETTER_V('V', 5),
    LETTER_W('W', 5),
    LETTER_X('X', 5),
    LETTER_Y('Y', 5),
    LETTER_Z('Z', 5),
    LETTER_a('a', 5),
    LETTER_ae('ä', 5),
    LETTER_b('b', 5),
    LETTER_c('c', 5),
    LETTER_d('d', 5),
    LETTER_e('e', 5),
    LETTER_f('f', 4),
    LETTER_g('g', 5),
    LETTER_h('h', 5),
    LETTER_i('i', 1),
    LETTER_j('j', 5),
    LETTER_k('k', 4),
    LETTER_l('l', 1),
    LETTER_m('m', 5),
    LETTER_n('n', 5),
    LETTER_o('o', 5),
    LETTER_oe('ö', 5),
    LETTER_p('p', 5),
    LETTER_q('q', 5),
    LETTER_r('r', 5),
    LETTER_s('s', 5),
    LETTER_t('t', 4),
    LETTER_u('u', 5),
    LETTER_ue('ü', 5),
    LETTER_v('v', 5),
    LETTER_w('w', 5),
    LETTER_x('x', 5),
    LETTER_y('y', 5),
    LETTER_z('z', 5),
    LINE('|', 1),
    MINUS('-', 5),
    NUM_0('0', 5),
    NUM_1('1', 5),
    NUM_2('2', 5),
    NUM_3('3', 5),
    NUM_4('4', 5),
    NUM_5('5', 5),
    NUM_6('6', 5),
    NUM_7('7', 5),
    NUM_8('8', 5),
    NUM_9('9', 5),
    NUM_SIGN('#', 5),
    PERCENT('%', 5),
    PERIOD('.', 1),
    PLUS_SIGN('+', 5),
    QUESTION_MARK('?', 5),
    RIGHT_ARROW('>', 4),
    RIGHT_BRACKET(']', 3),
    RIGHT_CURL_BRACE('}', 4),
    RIGHT_PARENTHESIS(')', 4),
    SEMI_COLON(';', 1),
    SINGLE_QUOTE('\'', 1),
    SLASH('/', 5),
    SPACE(' ', 3),
    TICK('`', 2),
    TILDE('~', 5),
    UNDERSCORE('_', 5),
    UP_ARROW('^', 5),
    UTF8_25B2('▲', 5),
    UTF8_25B6('▶', 6),
    UTF8_25BC('▼', 5),
    UTF8_25C0('◀', 6),
    UTF8_2600('☀', 8),
    UTF8_2601('☁', 8),
    UTF8_2620('☠', 7),
    UTF8_2708('✈', 7);

    private final char character;
    private final int length;

    FontInfo(char character, int length) {
        this.character = character;
        this.length = length;
    }

    public char getCharacter() {
        return this.character;
    }

    public int getLength(boolean bold) {
        return bold ? this.length + 1 : this.length;
    }

    public static @NotNull FontInfo getFontInfo(char c) {
        return Arrays.stream(values())
                .filter(fontInfo -> fontInfo.getCharacter() == c).findFirst()
                .orElse(FontInfo.DEFAULT);
    }
}