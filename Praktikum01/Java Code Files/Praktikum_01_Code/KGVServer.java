package Praktikum_01_Code;

public class KGVServer implements CommandExecutor {

    @Override
    public String execute(String s) {
        String[] numbers = s.split(" ");
        int a = Integer.parseInt(numbers[0]); int b = Integer.parseInt(numbers[1]); return Integer.toString(kgv(a,b));
    }

    public int kgv(int a, int b){
        return Math.abs(a*b) / ggt(a, b);
    }
    private int ggt(int a, int b){
        while (a != b) {
            if (a > b) {
                a  = a -b;
            } else {
                b = b -a;
            }
        }
        return a;
    }
}
