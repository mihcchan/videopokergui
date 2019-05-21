/**
 * Classe que representa uma carta. Cria a carta com um numero e um naipe.
 */
public class Carta {


    private int numero, naipe;

    /**
     * Contrutor padrão da classe Carta, cria uma carta sem nada.
     * @constructor
     */
    public Carta(){

    }

    /**
     * Contrutor da classe Carta que cria uma carta com um número e um naipe aleatório;
     * @constructor
     * @param numero um inteiro de 0 a 12 que representa uma carta entre 2 e A.
     * @param naipe um inteiro que representa um dos 4 naipes;
     */
    public Carta(int numero, int naipe){
        this.numero = numero;
        this.naipe = naipe;
    }

    /**
     * Sorteia um novo numero e um novo naipe para uma carta.
     */
    public void sortearNovaCarta(){
        /*Random random = new Random();
        this.numero = random.getIntRand(13) - 1;*/
        this.numero = sortearNumero();
       /* Random random2 = new Random();
        this.naipe = random2.getIntRand(4) -1;*/
       this.naipe = sortearNaipe();
    }

    /**
     * Sorteia um número para a carta.
     * @return this.numero Um número inteiro que representa o número da carta.
     */
    public int sortearNumero(){
        Random random = new Random();
        this.numero = random.getIntRand(13);
        return this.numero;
    }

    /**
     * Sorteia um naipe para a carta.
     * @return this.naipe Um número inteiro que representa o naipe da carta.
     */
    public int sortearNaipe(){
        Random random2 = new Random();
        this.naipe = random2.getIntRand(4);
        return naipe;
    }

    /**
     * Retorna o número da carta
     * @return this.numero
     */
    public int getNumero(){
        return this.numero;
    }

    /**
     * Retorna o naipe da carta
     * @return this.naipe
     */
    public int getNaipe(){
        return this.naipe;
    }

    /**
     * Override do método equals para comprar o conteúdo de um objeto com outro, ao invés de comparar se eles são o mesmo objeto.
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Carta carta = (Carta) o;
        return this.numero == carta.numero &&
                this.naipe == carta.naipe;
    }
    
    /**
     * Override do método toString para imprimir uma carta
     * @return String Uma String com o número e o naipe da carta
     */
    @Override
    public String toString() {
        return "Carta{" +
                "numero=" + numero +
                ", naipe=" + naipe +
                '}';
    }
}
