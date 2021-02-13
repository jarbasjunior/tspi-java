package numeros;

public class Numeros {
    public boolean EUmaUnidade(int meuNumero) {
        boolean eUnidade = true;
        if (meuNumero > 9) {
            eUnidade = false;
        }
        return eUnidade;
    }
}
