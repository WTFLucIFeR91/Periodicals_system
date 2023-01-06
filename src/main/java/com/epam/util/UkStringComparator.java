package com.epam.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

public class UkStringComparator
        implements Comparator {

    public static final String UkAlphabetStr =
            "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ" +
                    "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    public static final String UkAlphabet =
            "\u0410\u0411\u0412\u0413\u0490\u0414\u0415\u0404\u0416\u0417\u0418" +
                    "\u0406\u0407\u0419\u041a\u041b\u041c\u041d\u041e\u041f\u0420\u0421" +
                    "\u0422\u0423\u0424\u0425\u0426\u0427\u0428\u0429\u042c\u042e\u042f" +
                    "\u0430\u0431\u0432\u0433\u0491\u0434\u0435\u0454\u0436\u0437\u0438" +
                    "\u0456\u0457\u0439\u043a\u043b\u043c\u043d\u043e\u043f\u0440\u0441" +
                    "\u0442\u0443\u0444\u0445\u0446\u0447\u0448\u0449\u044c\u044e\u044f" ;

    public UkStringComparator() {
    }



    public int compare(Object thisOString, Object anotherOString) {
        return compareStr(thisOString, anotherOString);
    }

    public static int compareStr(Object thisOString, Object anotherOString) {

        String thisString = thisOString.toString();
        String anotherString = anotherOString.toString();

        int len1 = thisString.length();
        int len2 = anotherString.length();
        int n = Math.min(len1, len2);

        char v1[] = thisString.toCharArray();
        char v2[] = anotherString.toCharArray();

        int i = 0; //thisString.offset;
        int j = 0; //anotherString.offset;

        int k = i;
        int lim = n + i;
        int p1 = -1, p2 = -1;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                if ((p1 = UkAlphabet.indexOf(c1)) >= 0 && (p2 = UkAlphabet.indexOf(c2)) >= 0)
                    return UkAlphabet.indexOf(c1) - UkAlphabet.indexOf(c2);
                else
                    return c1 - c2;
            }
            k++;
        }

        return len1 - len2;

    }
}
