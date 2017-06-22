package io.vntr.rest.token;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robertlindquist on 6/21/17.
 */
@Component
public class TokenEncoder {
    private static Map<String, Integer> encodeMap = new HashMap<>();
    private static Map<Integer, String> decodeMap = new HashMap<>();

    private static final int p = 65537;
    private static final int q = 524287;
    private static final BigDecimal modulus = BigDecimal.valueOf(p*q);
    private static final long totient = ((long)p-1)*((long)q-1);
    private static final BigDecimal _64 = BigDecimal.valueOf(64);

    private static final long[] bitmasks = {
            0b0000000000000000000000000000000000000000000000000000000000111111L,
            0b0000000000000000000000000000000000000000000000000000111111000000L,
            0b0000000000000000000000000000000000000000000000111111000000000000L,
            0b0000000000000000000000000000000000000000111111000000000000000000L,
            0b0000000000000000000000000000000000111111000000000000000000000000L,
            0b0000000000000000000000000000111111000000000000000000000000000000L,
            0b0000000000000000000000111111000000000000000000000000000000000000L,
            0b0000000000000000111111000000000000000000000000000000000000000000L,
            0b0000000000111111000000000000000000000000000000000000000000000000L,
            0b0000111111000000000000000000000000000000000000000000000000000000L
    };

    private static final BigDecimal twoToThe64th = BigDecimal.valueOf(Long.MAX_VALUE).add(BigDecimal.ONE).multiply(BigDecimal.ONE.add(BigDecimal.ONE));

    static{
        encodeMap.put("-", 0);
        encodeMap.put("A", 1);
        encodeMap.put("B", 2);
        encodeMap.put("C", 3);
        encodeMap.put("D", 4);
        encodeMap.put("E", 5);
        encodeMap.put("F", 6);
        encodeMap.put("G", 7);
        encodeMap.put("H", 8);
        encodeMap.put("I", 9);
        encodeMap.put("J", 10);
        encodeMap.put("K", 11);
        encodeMap.put("L", 12);
        encodeMap.put("M", 13);
        encodeMap.put("N", 14);
        encodeMap.put("O", 15);
        encodeMap.put("P", 16);
        encodeMap.put("Q", 17);
        encodeMap.put("R", 18);
        encodeMap.put("S", 19);
        encodeMap.put("T", 20);
        encodeMap.put("U", 21);
        encodeMap.put("V", 22);
        encodeMap.put("W", 23);
        encodeMap.put("X", 24);
        encodeMap.put("Y", 25);
        encodeMap.put("Z", 26);
        encodeMap.put("a", 27);
        encodeMap.put("b", 28);
        encodeMap.put("c", 29);
        encodeMap.put("d", 30);
        encodeMap.put("e", 31);
        encodeMap.put("f", 32);
        encodeMap.put("g", 33);
        encodeMap.put("h", 34);
        encodeMap.put("i", 35);
        encodeMap.put("j", 36);
        encodeMap.put("k", 37);
        encodeMap.put("l", 38);
        encodeMap.put("m", 39);
        encodeMap.put("n", 40);
        encodeMap.put("o", 41);
        encodeMap.put("p", 42);
        encodeMap.put("q", 43);
        encodeMap.put("r", 44);
        encodeMap.put("s", 45);
        encodeMap.put("t", 46);
        encodeMap.put("u", 47);
        encodeMap.put("v", 48);
        encodeMap.put("w", 49);
        encodeMap.put("x", 50);
        encodeMap.put("y", 51);
        encodeMap.put("z", 52);
        encodeMap.put("0", 53);
        encodeMap.put("1", 54);
        encodeMap.put("2", 55);
        encodeMap.put("3", 56);
        encodeMap.put("4", 57);
        encodeMap.put("5", 58);
        encodeMap.put("6", 59);
        encodeMap.put("7", 60);
        encodeMap.put("8", 61);
        encodeMap.put("9", 62);
        encodeMap.put("_", 63);

        decodeMap.put(0, "-");
        decodeMap.put(1, "A");
        decodeMap.put(2, "B");
        decodeMap.put(3, "C");
        decodeMap.put(4, "D");
        decodeMap.put(5, "E");
        decodeMap.put(6, "F");
        decodeMap.put(7, "G");
        decodeMap.put(8, "H");
        decodeMap.put(9, "I");
        decodeMap.put(10, "J");
        decodeMap.put(11, "K");
        decodeMap.put(12, "L");
        decodeMap.put(13, "M");
        decodeMap.put(14, "N");
        decodeMap.put(15, "O");
        decodeMap.put(16, "P");
        decodeMap.put(17, "Q");
        decodeMap.put(18, "R");
        decodeMap.put(19, "S");
        decodeMap.put(20, "T");
        decodeMap.put(21, "U");
        decodeMap.put(22, "V");
        decodeMap.put(23, "W");
        decodeMap.put(24, "X");
        decodeMap.put(25, "Y");
        decodeMap.put(26, "Z");
        decodeMap.put(27, "a");
        decodeMap.put(28, "b");
        decodeMap.put(29, "c");
        decodeMap.put(30, "d");
        decodeMap.put(31, "e");
        decodeMap.put(32, "f");
        decodeMap.put(33, "g");
        decodeMap.put(34, "h");
        decodeMap.put(35, "i");
        decodeMap.put(36, "j");
        decodeMap.put(37, "k");
        decodeMap.put(38, "l");
        decodeMap.put(39, "m");
        decodeMap.put(40, "n");
        decodeMap.put(41, "o");
        decodeMap.put(42, "p");
        decodeMap.put(43, "q");
        decodeMap.put(44, "r");
        decodeMap.put(45, "s");
        decodeMap.put(46, "t");
        decodeMap.put(47, "u");
        decodeMap.put(48, "v");
        decodeMap.put(49, "w");
        decodeMap.put(50, "x");
        decodeMap.put(51, "y");
        decodeMap.put(52, "z");
        decodeMap.put(53, "0");
        decodeMap.put(54, "1");
        decodeMap.put(55, "2");
        decodeMap.put(56, "3");
        decodeMap.put(57, "4");
        decodeMap.put(58, "5");
        decodeMap.put(59, "6");
        decodeMap.put(60, "7");
        decodeMap.put(61, "8");
        decodeMap.put(62, "9");
        decodeMap.put(63, "_");
    }


    String encode(String username, String password, Long tokenExpirationEpoch) {
        BigDecimal usernameEncoded = BigDecimal.valueOf(stringToLong(username));
        BigDecimal passwordEncoded = BigDecimal.valueOf(stringToLong(password)).multiply(twoToThe64th);
        BigDecimal expirationEncoded = BigDecimal.valueOf(tokenExpirationEpoch).multiply(twoToThe64th).multiply(twoToThe64th);

        BigDecimal fullThing = usernameEncoded.add(passwordEncoded).add(expirationEncoded);

        BigDecimal encoded = fullThing.pow(p).divideAndRemainder(modulus)[1];

        StringBuilder builder = new StringBuilder();
        while(encoded.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal[] divideAndRemainder = encoded.divideAndRemainder(_64);
            int chunk = divideAndRemainder[1].intValue();
            String s = decodeMap.get(chunk);
            builder.append(s);
            encoded = divideAndRemainder[0];
        }

        return builder.toString();
    }

    String[] decode(String token) {
        BigDecimal number = BigDecimal.ZERO;
        for(int i=0; i<token.length(); i++) {
            int encodedCharacter = encodeMap.get("" + token.charAt(i));
            BigDecimal shifter = _64.pow(i);
            number = number.add(BigDecimal.valueOf(encodedCharacter).multiply(shifter));
        }

        BigDecimal decoded = number.pow(q).divideAndRemainder(modulus)[1];
        BigDecimal[] decodedDividedByTwoToThe64th = decoded.divideAndRemainder(twoToThe64th);
        BigDecimal[] decodedDividedByTwoToThe128th = decodedDividedByTwoToThe64th[0].divideAndRemainder(twoToThe64th);

        BigDecimal usernameChunk = decodedDividedByTwoToThe64th[1];
        BigDecimal passwordChunk = decodedDividedByTwoToThe128th[1];
        BigDecimal expirationChunk = decodedDividedByTwoToThe128th[0];

        String username = longToString(usernameChunk.longValue());
        String password = longToString(passwordChunk.longValue());
        long expirationEpoch = expirationChunk.longValue();

        return new String[]{username, password, "" + expirationEpoch};
    }

    private long stringToLong(String str) {
        long l = 0L;
        for(int i=0; i<str.length(); i++) {
            long encoded = (long) (int) encodeMap.get("" + str.charAt(i));
            l += (encoded << (6 * i));
        }
        return l;
    }

    private String longToString(long l) {
        StringBuilder s = new StringBuilder();
        for(int i=0; i<10; i++) {
            long raw = l & bitmasks[i];
            long shifted = raw >> (6*i);
            String decoded = decodeMap.get((int) shifted);
            s.append(decoded);
        }
        return s.toString();
    }
}
