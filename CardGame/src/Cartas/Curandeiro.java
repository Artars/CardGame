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
    public void onClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
