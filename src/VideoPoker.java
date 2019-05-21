import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Classe VideoPoker que contém apenas o método main para executar o jogo.
 * 
 * @author Michelle Schmitt Gmurczyk - 9424315
 * @author Ana Clara Amorim Andrade - 10691992
 *
 */
public class VideoPoker {

    /**
     * Método main responsável pela execução do jogo. e possui um laço, no qual cada iteração representa uma rodada do jogo.
     * Em cada rodada, o jogador define sua aposta e pode, até 3 vezes, descartar entre 0 e 5 cartas da mão e tirar novas do baralho.
     * Após cada rodada a combinação de cartas é verificada e seus créditos atualizados.
     * O jogo termina se o jogador ficar sem créditos para apostar ou se ele quiser (apostando 0).
     * No final das rodadas a quatidade de créditos acumulados é exibida.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {

     
        VideoPokerGUI frame = new VideoPokerGUI();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
//        frame.setLayout(new GridLayout(2,1));
        
        JPanel jPanel = (JPanel) frame.getContentPane();
        jPanel.setLayout(new GridLayout(5,1));

        JPanel jp = (JPanel) frame.getContentPane();
   
        
        
        
        Baralho baralho = new Baralho();
        Creditos saldo = new Creditos();
        int[] numeroCartas;
        int[] naipesCartas;

        int aposta; 
        int saldoAnterior;

        while(saldo.getSaldo()>0){

            saldoAnterior = saldo.getSaldo();

           

            /* Tentativa de resetar as informações da janela, funciona mas ele tipo cria uma janela nova,
            não sei se isso é um problema.
             */
           /* frame.revalidate();
            frame.repaint();
            */
            
        
            jPanel.add(new JLabel("Feche a janela para sair.\n"));
            jPanel.add(new JLabel("Saldo atual: " + saldo.getSaldo()));
            jPanel.add(new JLabel("Digite o valor da aposta:"));

    
            
            
            JTextField nome = new JTextField();
            JButton buttonOK = new JButton("CONFIRMA");
                       
            
            /* Pra esperar o click do botão pra continuar o programa
            */
            buttonOK.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                synchronized (buttonOK) {
                    buttonOK.notify();
                }
            }
        });
            
            buttonOK.setText("CONFIRMA");
            
            jp.add(nome);
            jp.add(buttonOK);
            
            jPanel.validate();
            jp.validate();            
            synchronized(buttonOK) {
                try {
                    buttonOK.wait();
                } catch (InterruptedException ex) {
                    System.out.println("Interrupted");
                }
            }

                
            aposta = Integer.parseInt(nome.getText());
            System.out.println("Aposta: " + aposta);
            System.out.println("Saldo:" + saldo);

            saldo.setAposta(aposta);
            if(aposta == 0 ){
                System.out.println("Aposta inválida");
            }else if(aposta > saldoAnterior){
                 System.out.println("Valor da aposta maior que o valor do saldo");
            }else{
                baralho.sortear();
                System.out.println(baralho.toString());
                
                System.out.println("Insira as cartas que quer descartar separadas por espaço.");
                String segundaVez = EntradaTeclado.leString();
                try{
                    baralho.sortear(segundaVez);
                    System.out.println(baralho.toString());
                } catch (NumberFormatException e) {
                    System.out.println(baralho.toString());
                }
                
                System.out.println("Insira as cartas que quer descartar separadas por espaço.");
                String terceiraVez = EntradaTeclado.leString();
                try {
                    baralho.sortear(terceiraVez);
                    System.out.println(baralho.toString());
                } catch (NumberFormatException e) {
                    System.out.println(baralho.toString());
                }
                
                
                numeroCartas = baralho.getNumerosCartas();
                naipesCartas = baralho.getNaipesCartas();
                System.out.print("\n");
                System.out.println(saldo.pontuacao(numeroCartas,naipesCartas));
                baralho.resetaCartasJáTiradas();
            }
            
            /*Limpa os panels
            */            
            jPanel.removeAll();
            jp.removeAll();
        }
                
    }

}


