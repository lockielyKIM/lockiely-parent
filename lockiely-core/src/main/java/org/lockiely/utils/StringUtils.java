package org.lockiely.utils;

public abstract class StringUtils {

//    public static boolean hasText(CharSequence str) {
//        return (hasLength(str) && containsText(str));
//    }

    public static boolean hasText(String str) {
        return (hasLength(str) && containsText(str));
    }

    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        } else {
            int len = str.length();
            StringBuilder sb = new StringBuilder(str.length());

            for(int i = 0; i < len; ++i) {
                char c = str.charAt(i);
                if (!Character.isWhitespace(c)) {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasLength(String str) {
        return (str != null && !str.isEmpty());
    }

//    public static boolean hasText(CharSequence str) {
//        return (hasLength(str) && containsText(str));
//    }

    public static boolean isEmpty(Object obj){
        return obj == null || "".equals(obj);
    }
}
