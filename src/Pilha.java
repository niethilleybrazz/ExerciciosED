import java.util.NoSuchElementException;

public class Pilha<T> {
    private final Lista<T> lista = new Lista<>();

    public void empilhar(T dado) {
        lista.inserirFim(dado);
    }

    public T desempilhar() {
        if (estaVazia()) throw new NoSuchElementException("Pilha vazia");
        return lista.removerFim();
    }

    public T topo() {
        if (estaVazia()) throw new NoSuchElementException("Pilha vazia");
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
        return "(base -> topo) " + lista;
    }
}