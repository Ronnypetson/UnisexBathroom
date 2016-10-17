/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncbanheiro;

/**
 *
 * @author ronnypetsonss
 */
public class SyncBanheiro {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Banheiro banheiro = new Banheiro();
        int numPessoas = 100;
        Thread pessoas[] = new Thread[numPessoas];
        for(int i = 0; i < numPessoas; i++){
            if((i/10)%2 == 0){
                pessoas[i] = new ThreadHomem(banheiro);
            } else {
                pessoas[i] = new ThreadMulher(banheiro);
            }
            pessoas[i].start();
        }
    }
}
