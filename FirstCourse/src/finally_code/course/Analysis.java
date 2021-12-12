package finally_code.course;


public  class Analysis {
    /**
     * 判断表达式是不是只有一个数字
     *
     * @param str 原值
     * @return 数字非法性校验结果，失败返回false;
     */

    public static double isSqr(String str){
        int index = str.indexOf("sqr");
        return Math.sqrt(Double.parseDouble(str.substring(index+3,index+4)));
    }


    private static boolean isNumber(String str) {
        // 判断表达式是不是只有一个数字
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.' && str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * 解析算式，并计算算式结果；
     *
     * @param str 算式的字符串
     * @return double类型的算式结果
     */
    public static Double getResult(String str) {

        // 递归头
        if (str.isEmpty() || isNumber(str)) {
            return str.isEmpty() ? 0 : Double.parseDouble(str);
        }

        //递归体
        if (str.contains(")")) {
            // 最后一个左括号
            int lIndex = str.lastIndexOf("(");
            // 对于的右括号
            int rIndex = str.indexOf(")", lIndex);
            return getResult(str.substring(0, lIndex) + getResult(str.substring(lIndex + 1, rIndex)) + str.substring(rIndex + 1));
        }

        // 对于各种运算符的分析
        if (str.contains("+")) {
            int index = str.lastIndexOf("+");
            return getResult(str.substring(0, index)) + getResult(str.substring(index + 1));
        }
        if (str.contains("-")) {
            int index = str.lastIndexOf("-");
            return getResult(str.substring(0, index)) - getResult(str.substring(index + 1));
        }
        if (str.contains("*")) {
            int index = str.lastIndexOf("*");
            return getResult(str.substring(0, index)) * getResult(str.substring(index + 1));
        }
        if (str.contains("÷")) {
            int index = str.lastIndexOf("÷");
            return getResult(str.substring(0, index)) / getResult(str.substring(index + 1));
        }
        if (str.contains("%")) {
            int index = str.lastIndexOf("%");
            return getResult(str.substring(0, index)) % getResult(str.substring(index + 1));
        }
        if (str.contains("sqr")) {
            int index = str.lastIndexOf("sqr");
            return getResult(str.substring(0, index));
        }
        // 出错
        return null;
    }
}
