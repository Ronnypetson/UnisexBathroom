/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncbanheiro;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe monitor Banheiro
 * @author ronnypetsonss
 */
public class Banheiro {
    private final int CAPACIDADE_BANHEIRO = 10;
    private int vagasNoBanheiro;
    private int mulheresEsperando;
    private int mulheresUsando;
    private int homensEsperando;
    private int homensUsando;
    private Lock lock;
    private Condition filaDasMulheres;
    private Condition filaDosHomens;
    
    public Banheiro(){
        vagasNoBanheiro = CAPACIDADE_BANHEIRO;
        mulheresEsperando = 0;
        mulheresUsando = 0;
        homensEsperando = 0;
        homensUsando = 0;
        lock = new ReentrantLock();
        filaDasMulheres = lock.newCondition();
        filaDosHomens = lock.newCondition();
    }
    
    public void mulherUsa() throws InterruptedException{
        // lock.lock();
        try {
            lock.lock();
            try {
                if(this.vagasNoBanheiro == 0 || this.homensUsando > 0){
                    this.mulheresEsperando++;
                }
                while(this.vagasNoBanheiro == 0 || this.homensUsando > 0){
                    this.filaDasMulheres.await();
                }
                if(this.mulheresEsperando > 0){
                    this.mulheresEsperando--;
                }
                this.vagasNoBanheiro--;
                this.mulheresUsando++;
            } finally {
                lock.unlock();
            }
            
            printStatus();
            sleep();
            //
            lock.lock();
            try{
                this.vagasNoBanheiro++;
                this.mulheresUsando--;
                if(this.homensEsperando > 0){
                    this.filaDosHomens.signalAll();
                } else {
                  this.filaDasMulheres.signalAll();
                }
            } finally {
                lock.unlock();
            }
        } finally {
            // lock.unlock();
        }
    }
    
    public void homemUsa() throws InterruptedException{
        // lock.lock();
        try {
            lock.lock();
            try {
                if(this.vagasNoBanheiro == 0 || this.mulheresUsando > 0){
                    this.homensEsperando++;
                }
                while(this.vagasNoBanheiro == 0 || this.mulheresUsando > 0){
                    this.filaDosHomens.await();
                }
                if(this.homensEsperando > 0){
                    this.homensEsperando--;
                }
                this.vagasNoBanheiro--;
                this.homensUsando++;
            } finally {
                lock.unlock();
            }
            
            System.out.println("Homem entrou.\n" + this.homensUsando
                        + " homens usando o banheiro.\t\t" + this.homensEsperando
                        + " homens esperando.\n" + this.mulheresUsando
                        + " mulheres usando o banheiro.\t\t" + this.mulheresEsperando
                        + " mulheres esperando.\n\n");
            sleep();
            //
            lock.lock();
            try{
                this.vagasNoBanheiro++;
                this.homensUsando--;
                if(this.mulheresEsperando > 0){
                    this.filaDasMulheres.signalAll();
                }else {
                  this.filaDosHomens.signalAll();
                }
            } finally {
                lock.unlock();
            }
        } finally {
            // lock.unlock();
        }
    }
    
    private void printStatus(){
        System.out.println("Mulher entrou.\n" + this.homensUsando
                        + " homens usando o banheiro.\t\t" + this.homensEsperando
                        + " homens esperando.\n" + this.mulheresUsando
                        + " mulheres usando o banheiro.\t\t" + this.mulheresEsperando
                        + " mulheres esperando.\n\n");
    }
    
    private void sleep(){
        long tempoDeUso, limit = 30000, i = 0, j;
        tempoDeUso = (long)(Math.random() * limit);
        while(i++ < tempoDeUso){
            j = 0;
            while(j++ < tempoDeUso){
            }
        }        
    }
}
