package container;

public class Main {
    public static void main(String[] args) {
        String s = "123sqr9 456";
        System.out.println(s.contains("sqr"));
        int in = s.indexOf("sqr");
        String substring = s.substring(in + 3, in + 4);
        System.out.println(Double.parseDouble(substring));
        System.out.println(s.replace("sqr", substring));
    }
}
