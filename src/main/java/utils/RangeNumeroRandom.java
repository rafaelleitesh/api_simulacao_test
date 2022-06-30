package utils;

public class RangeNumeroRandom {

    public static int geraNumeroRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static boolean geraBoolRandom() {
        boolean resultado = false;
        int num = geraNumeroRandom(0,1);
            if (num == 0) {
                resultado = false;
            }
            else {
                resultado = true;
            }
        return resultado;
        }

}
