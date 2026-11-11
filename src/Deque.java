import java.util.NoSuchElementException;

/** DEQUE: inserção e remoção nas duas extremidades. */
public class Deque<T> {
    private final Lista<T> lista = new Lista<>();

    public void inserirInicio(T dado) {
        lista.inserirInicio(dado);
    }

    public void inserirFim(T dado) {
        lista.inserirFim(dado);
    }

    public T removerInicio() {
        if (estaVazia()) throw new NoSuchElementException("Deque vazio");
        return lista.removerInicio();
    }

    public T removerFim() {
        if (estaVazia()) throw new NoSuchElementException("Deque vazio");
        return lista.removerFim();
    }

    public T espiarInicio() {
        if (estaVazia()) throw new NoSuchElementException("Deque vazio");
        return lista.primeiro();
    }

    public T espiarFim() {
        if (estaVazia()) throw new NoSuchElementException("Deque vazio");
        return lista.ultimo();
    }

    public boolean estaVazia() {
        return lista.estaVazia();
    }

    public int tamanho() {
        return lista.tamanho();
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}