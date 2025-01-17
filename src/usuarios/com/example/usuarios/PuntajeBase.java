package com.example.usuarios;

public class PuntajeBase implements Puntaje {
    private int puntaje;

    public PuntajeBase(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public int calcularPuntaje() {
        return puntaje;
    }
}