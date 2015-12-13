package com.gir.ga;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class Individual {

    private byte[] dna;

    public Individual(byte[] dna) {
        this.dna = dna;
    }

    public byte getGene(int index) {
        return dna[index];
    }

    public void setGene(int index, byte gene) {
        dna[index] = gene;
    }

    public byte[] getDna() {
        return dna;
    }

    public String toString() {
        StringBuffer stringDna = new StringBuffer();
        for (int i=0; i<dna.length; i++) {
            stringDna.append(dna[i]);
        }
        return stringDna.toString();
    }
}
