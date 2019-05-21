/**
 * Classe que gerencia os créditos, realizando apostas e recebendo prêmios.
 */
public class Creditos {
	
	private int saldo,aposta;

	/**
	 * Metodo construtor padrão.
	 * Seta saldo de 200 créditos e aposta como 0.
	 */
	public Creditos(){
		this.saldo = 200;
		this.aposta = 0;
	}

	/**
	 * Construtor que permite setar saldo de créditos inicial.
	 * @param n Saldo de créditos desejado para começar o jogo.
	 */
	public Creditos(int n){
		this.saldo = n;
		this.aposta = 0;
	}

	/**
	 * Permite setar o valor da aposta da rodada.
	 * @param n Valor de créditos que serão apostados.
	 */
	public void setAposta(int n){
	    if(n<=this.saldo){
	        this.aposta = n;
		    this.saldo = this.saldo - this.aposta;
	    }
	
	}
	
	public int getAposta(){
		return this.aposta;
	}

	/**
	 * Permite obter o valor do saldo atual.
	 * @return this.saldo Valor do saldo atual.
	 */
	public int getSaldo(){
		return this.saldo;
	}

	/**
	 * Testa cada combinação de cartas e com base na combinação obtida
	 * e atualiza o saldo de créditos.
	 * <pre>
     * Possível pontuação:
	 *   - Dois pares - Valor da aposta
	 *   - Trinca - 2X valor da aposta
	 *   - Straight (5 cartas seguidas de naipes diferentes) - 5X valor da aposta
	 *   - Flush (5 cartas do mesmo naipe não seguidas) - 10X valor da aposta
	 *   - Full hand (uma trinca e um par) - 20X valor da aposta
	 *   - Quadra - 50X valor da aposta
	 *   - Straight Flush (5 cartas seguidas do mesmo naipe) (straight + fush ao 
     *   mesmo tempo) - 100X valor da aposta
	 *   - Royal Straight Flush (5 cartas seguidas do mesmo naipe de 10 até o As)
     *   (Straight Flush de 10 a Ás) - 200X valor da aposta
	 * @param maoNumeros Array de inteiros que corresponde aos numeros das cartas.
	 * @param maoNaipes Array de inteiros que corresponde aos naipes das cartas.
	 * @return combinação Uma string com a combinação de cartas feita.
     * <pre>
	 */
	public String pontuacao( int []maoNumeros, int []maoNaipes){
	    
		int cartasNum[] = new int[13];
		/*Vetor de cartas: 
		 * Posição     Valor
		 * 0 			2
		 * 1 			3
		 * 2			4
		 * 3			5
		 * 4			6
		 * 5			7
		 * 6			8
		 * 7			9
		 * 8			10
		 * 9			J
		 * 10			Q
		 * 11			K
		 * 12			A
		*/
		
		int cartasNaipe[] = new int[4];
		
		/*
		 * Vetor de naipes:
		 * Posição     Naipe
		 * 0			copas
		 * 1			ouro
		 * 2			paus
		 * 3			espada
		 */
		
		int cartasMao[] = new int[5];
		String combinação = "";
		//Zerar o vetor
		for(int i=0;i<13;i++){
			cartasNum[i]=0;
		}
		
		for(int i=0;i<4;i++){
			cartasNaipe[i] = 0;
		}
		
		//Guarda o valor das cartas na posição do vetor
		for(int i=0;i<5;i++){
			cartasNum[maoNumeros[i]]++;
		}
		
		for(int i=0;i<5;i++){
			cartasNaipe[maoNaipes[i]]++;
		}
		
		boolean sequenciaAlta = false,sequencia = false,quadra = false,trinca = false, par = false;
		int indicePar = -1; //guarda qual a carta que tem par na mão para ser capaz de calcular o dois par
		//Testa se tem sequencia de cartas do numero 10 a A
		if(cartasNum[8] == 1 && cartasNum[9] == 1 && cartasNum[10] == 1 && cartasNum[11] == 1 && cartasNum[12] == 1){
			sequenciaAlta = true;
			
		} else{
			// Cada if testa sequencia do numero correspondente e mais 5
			for(int i = 0;i<9;i++){
				if(cartasNum[i] == 1 && cartasNum[i+1] == 1 && cartasNum[i+2] == 1 && cartasNum[i+3] == 1 && cartasNum[i+4] == 1 ){
					sequencia = true;
				}
			}
		}
		
		//Testa a quadra
		for(int i = 0;i<13;i++){
		    if(cartasNum[i] == 4){
		        quadra = true;
		    } else if(cartasNum[i] == 3){
		        trinca = true;
		    } else if(cartasNum[i] == 2){
		        par = true;
		        indicePar = i;
		    }
		}
		
		boolean mesmoNaipe = false;
		//Testa se as 5 cartas sao do mesmo naipe
		for(int i= 0;i<4;i++){
			if(cartasNaipe[i] == 5){
				mesmoNaipe = true;
				break;
			}
		}
		
		if(sequenciaAlta && mesmoNaipe){
		    //royal straight flush
		    combinação +="Royal Straight Flush";
		    this.saldo = this.saldo + (200*this.aposta);
		}else if(sequencia && mesmoNaipe){
		    //straight flush
		    combinação +="Straight Flush";
		    this.saldo = this.saldo + (100*this.aposta);
		} else if(quadra){
		    //Quadra
		    combinação +="Quadra";
		    this.saldo = this.saldo + (50*this.aposta);
		} else if( trinca && par){
		    //Full hand
		    combinação +="Full Hand";
		    this.saldo = this.saldo + (20*this.aposta);
		} else if(mesmoNaipe){
		    //flush
		    combinação +="Flush";
		    this.saldo = this.saldo + (10*this.aposta);
		} else if(sequencia || sequenciaAlta){
		    //straight
		    combinação +="Straight";
		     this.saldo = this.saldo + (5*this.aposta);
		} else if(trinca){
		    //trinca
		    combinação +="Trinca";
		    this.saldo = this.saldo + (2*this.aposta);
		} else if(par){
		    
		    boolean parDois = false;
		    for(int i=0;i<13;i++){
		        if(cartasNum[i] == 2 && i!=indicePar){
		            parDois = true;
		            break;
		        }
		    }
		    if(parDois){
		        //Dois par
		        combinação +="Dois Pares";
		        this.saldo = this.saldo + this.aposta;
		    }else{
		        combinação +="Nenhuma combinação";
		    }
		} else{
		     combinação +="Nenhuma combinação";
		}
		return combinação;
	}

	@Override
	public String toString() {
		return "Creditos{" +
				"saldo=" + saldo +
				'}';
	}
}