
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe que cria e opera um conjunto de cartas.
 */

public class Baralho {
    
    private ArrayList<Carta> cartasJaTiradas = new ArrayList<>();
    private Carta[] cartasDaMao;

    /**
     * Construtor da classe Baralho, cria um baralho, que é um array de cartas.
     * @constructor
     */
    public Baralho() {
        Carta[] helperCarta = new Carta[5];
        for (int i = 0; i < 5; i++) {
            helperCarta[i] = new Carta();
        }
        this.cartasDaMao = helperCarta;
    }

    /**
     * Sorteia 5 cartas diferentes.
     * @return cartasDaMao Um array de cartas (da classe Carta)
     * @throws InterruptedException
     */
    public Carta[] sortear() throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            this.cartasDaMao[i].sortearNovaCarta();
            while (this.cartasJaTiradas.contains(this.cartasDaMao[i])) {
                this.cartasDaMao[i].sortearNovaCarta();
            }
            this.cartasJaTiradas.add(new Carta(this.cartasDaMao[i].getNumero(), this.cartasDaMao[i].getNaipe()));
        }
        return cartasDaMao;
    }

    /**
     * Sorteia apenas as cartas escolhidas a patir de um vetor de booleanos, em que false representa uma carta que não deve ser re-sorteada e true uma carta que deve ser re-sorteada.
     * @param quais Um vetor de booleanos
     * @throws InterruptedException
     */
    public void sortear(boolean[] quais) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            if (quais[i] == true) {
                this.cartasDaMao[i].sortearNovaCarta();
                while (this.cartasJaTiradas.contains(this.cartasDaMao[i])) {
                    this.cartasDaMao[i].sortearNovaCarta();
                }
                this.cartasJaTiradas.add(new Carta(this.cartasDaMao[i].getNumero(), this.cartasDaMao[i].getNaipe()));
            }
        }
    }

    /**
     * Transforma a string de números recebidos e os tranforma em inteiros. Gera um array de booleanos que recebe true nas posições dos inteiros recebebidas na String.
     * @param s Uma String contendo os números de 1 a 5 das cartas que devem ser re-sorteadas.
     * @throws InterruptedException
     */
    public void sortear(String s) throws InterruptedException {


        boolean[] quais = new boolean[5];
        Arrays.fill(quais, Boolean.FALSE);

        String[] separado = s.split(" ");

        int tamanho = separado.length;

        for (int i = 0; i < tamanho; i++) {
            int pos = Integer.valueOf(separado[i]) - 1;
            quais[pos] = true;
        }

        this.sortear(quais);
    }

    /**
     * Gera um array de inteiros com os números das cartas da mão.
     * @return numerosCarta Um array de inteiros contendo os numeros das cartas da mão.
     */
    public int[] getNumerosCartas(){

        int[] numerosCarta = new int[5];
        for (int i=0; i<5; i++) {
            numerosCarta[i] = this.cartasDaMao[i].getNumero();
        }

        return numerosCarta;
    }

    /**
     * Gera um array de inteiros com os naipes das cartas da mão.
     * @return naipesCarta Um array de inteiros contendo os naipes das cartas da mão.
     */
    public int[] getNaipesCartas(){

        int[] naipesCarta = new int[5];
        for(int i=0; i<5; i++){
            naipesCarta[i] = this.cartasDaMao[i].getNaipe();
        }
        return naipesCarta;
    }

    /**
     * Método que reseta o array de cartas já tiradas, usado ao final da rodada para as cartas descartadas voltarem ao baralho.
     */
    public void resetaCartasJáTiradas(){
        this.cartasJaTiradas = new ArrayList<>();
    }

    /**
     * <pre>
     * Override do método toString para imprimir um conjunto de cartas.
     * Imprime as cartas da seguinte forma:
     * +------+    +------+    +------+    +------+    +------+
     * |      |    |      |    |      |    |      |    |      |
     * |  K♠  |    |  5♦  |    |  5♣  |    |  J♣  |    |  J♥  |
     * |      |    |      |    |      |    |      |    |      |
     * +------+    +------+    +------+    +------+    +------+
     * @return String Uma String contendo os valores associados ao baralho.
     * </pre>
     */
    @Override
    public String toString() {

        String s = "";
        String bordaCimaBaixo = "+------+    ";
        String bordaLateral = "|      |    ";
        String meio1 = "|  ";
        String meio2 = "  |    ";
        String meio3 = "|    ";
        String meio4 = "| ";

        for(int i = 0; i < 5; i++) {
            s += bordaCimaBaixo;
        }
        s += "\n";

        for(int i = 0; i < 5; i++) {
            s += bordaLateral;
        }
        s += "\n";


        for(int i = 0; i < 5; i++) {
            if (cartasDaMao[i].getNumero() == 8){
                s += meio4;
            }else {
                s += meio1;
            }
                switch (cartasDaMao[i].getNumero()) {
                    case 0:
                        s += "2";
                        break;
                    case 1:
                        s += "3";
                        break;
                    case 2:
                        s += "4";
                        break;
                    case 3:
                        s += "5";
                        break;
                    case 4:
                        s += "6";
                        break;
                    case 5:
                        s += "7";
                        break;
                    case 6:
                        s += "8";
                        break;
                    case 7:
                        s += "9";
                        break;
                    case 8:
                        s += "10";
                        break;
                    case 9:
                        s += "J";
                        break;
                    case 10:
                        s += "Q";
                        break;
                    case 11:
                        s += "K";
                        break;
                    case 12:
                        s += "A";
                        break;
                }



            if (cartasDaMao[i].getNumero() == 8){
                switch(cartasDaMao[i].getNaipe()) {
                    case 0:
                        s += "♥";
                        break;
                    case 1:
                        s += "♦";
                        break;
                    case 2:
                        s += "♣";
                        break;
                    case 3:
                        s += "♠";
                        break;
                }
            } else{
                switch(cartasDaMao[i].getNaipe()) {
                    case 0:
                        s += "♥" + "";
                        break;
                    case 1:
                        s += "♦" + "";
                        break;
                    case 2:
                        s += "♣" + "";
                        break;
                    case 3:
                        s += "♠" + "";
                        break;
                }
            }
            if(cartasDaMao[i].getNaipe() == 8){
                s += meio3;
            } else{
                s += meio2;
            }
        }
        s += "\n";

        for(int i = 0; i < 5; i++) {
            s += bordaLateral;
        }
        s += "\n";

        for(int i = 0; i < 5; i++) {
            s += bordaCimaBaixo;
        }

        return s;

    }


}

