import java.util.NoSuchElementException;

public class Fila<T> {
    private final Lista<T> lista = new Lista<>();

    public void enfileirar(T dado) {
        lista.inserirFim(dado);
    }

    public T desenfileirar() {
        if (estaVazia()) throw new NoSuchElementException("Fila vazia");
        return lista.removerInicio();
    }

    public T frente() {
        if (estaVazia()) throw new NoSuchElementException("Fila vazia");
        return lista.primeiro();
    }

    public boolean estaVazia() {
        return lista.estaVazia();
    }

    public int tamanho() {
        return lista.tamanho();
    }

    @Override
    public String toString() {
        return "(frente -> trás) " + lista;
    }
}