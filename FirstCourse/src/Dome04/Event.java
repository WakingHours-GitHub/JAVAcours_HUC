package Dome04;

/**
 * 使用java eval 计算字符串表达式
 *
 * @paramstr 字符串表达式
 * @return double 类型的结果
 */
public class Event {
    public static void main(String[] args) {
        String str = "1*5*8+45";
        System.out.println(getResult(str));
    }

    /**
     * 判断表达式是不是只有一个数字
     *
     * @param str 原值
     * @return 数字非法性校验结果，失败返回false;
     */
    private static boolean isNumber(String str) {

        for (int i = 0; i < str.length(); i++) {// 判断
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
        if (str.contains("/")) {
            int index = str.lastIndexOf("/");
            return getResult(str.substring(0, index)) / getResult(str.substring(index + 1));
        }

        // 出错
        return null;
    }
}