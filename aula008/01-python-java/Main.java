public class Main {
    static void zera(int[] v, int n) {
        v[0] = 0;
        n = 0;
    }

    public static void main(String[] args) {
        int[] v = {5, 5};
        int n = 5;
        zera(v, n);
        System.out.println(v[0] + " " + n);
    }
}
