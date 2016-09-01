/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

/**
 *
 * @author Arthur
 */
public class Curandeiro extends Carta {

    private int cura;

    public Curandeiro(int n) {
        super(n);
        this.cura = n;
    }
    
    @Override
    public boolean Acao(Object o) {
        if (!realizouAcao) {
            if (o instanceof Atacavel){
                if(((Carta)o).isEnabled()){
                    ((Atacavel)o).RecuperarVida(cura * multiplicador);
                    realizouAcao = true;
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
    
}
