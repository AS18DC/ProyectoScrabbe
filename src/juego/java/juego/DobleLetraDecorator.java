package juego;

public class DobleLetraDecorator extends MultiplicadorDecorator {
    public DobleLetraDecorator(Puntaje puntajeDecorado) {
        super(puntajeDecorado);
    }

    @Override
    public int calcularPuntaje() {
        return super.calcularPuntaje() * 2;
    }
}
