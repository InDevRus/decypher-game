package org.indev.decyphergame.logic;

public interface Alphabet {
    String letters = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    static String letterByNumber(int number) {
        return letters.charAt(number) + "";
    }

    static int numberByLetter(String letter) {
        return letters.indexOf(letter);
    }
}
