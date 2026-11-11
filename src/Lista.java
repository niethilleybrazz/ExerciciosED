import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Lista<T> implements Iterable<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    // ---------- estado ----------

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void limpar() {
        inicio = fim = null;
        tamanho = 0;
    }

    // ---------- inserção ----------

    public void inserirInicio(T dado) {
        No<T> novo = new No<>(dado);
        if (estaVazia()) {
            inicio = fim = novo;
        } else {
            novo.prox = inicio;
            inicio.ant = novo;
            inicio = novo;
        }
        tamanho++;
    }

    public void inserirFim(T dado) {
        No<T> novo = new No<>(dado);
        if (estaVazia()) {
            inicio = fim = novo;
        } else {
            novo.ant = fim;
            fim.prox = novo;
            fim = novo;
        }
        tamanho++;
    }

    public void inserirEm(int pos, T dado) {
        if (pos < 0 || pos > tamanho)
            throw new IndexOutOfBoundsException("Posição inválida: " + pos);
        if (pos == 0) {
            inserirInicio(dado);
        } else if (pos == tamanho) {
            inserirFim(dado);
        } else {
            No<T> ref = noEm(pos);
            No<T> novo = new No<>(dado);
            novo.ant = ref.ant;
            novo.prox = ref;
            ref.ant.prox = novo;
            ref.ant = novo;
            tamanho++;
        }
    }

    // ---------- remoção ----------

    public T removerInicio() {
        if (estaVazia()) throw new NoSuchElementException("Lista vazia");
        return desligar(inicio);
    }

    public T removerFim() {
        if (estaVazia()) throw new NoSuchElementException("Lista vazia");
        return desligar(fim);
    }

    public T removerEm(int pos) {
        checarIndice(pos);
        return desligar(noEm(pos));
    }

    public boolean remover(T dado) {
        for (No<T> n = inicio; n != null; n = n.prox) {
            if (Objects.equals(n.dado, dado)) {
                desligar(n);
                return true;
            }
        }
        return false;
    }


    public T obter(int pos) {
        checarIndice(pos);
        return noEm(pos).dado;
    }

    public T primeiro() {
        if (estaVazia()) throw new NoSuchElementException("Lista vazia");
        return inicio.dado;
    }

    public T ultimo() {
        if (estaVazia()) throw new NoSuchElementException("Lista vazia");
        return fim.dado;
    }

    public int indiceDe(T dado) {
        int i = 0;
        for (No<T> n = inicio; n != null; n = n.prox, i++) {
            if (Objects.equals(n.dado, dado)) return i;
        }
        return -1;
    }

    public boolean contem(T dado) {
        return indiceDe(dado) != -1;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private No<T> atual = inicio;

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public T next() {
                if (atual == null) throw new NoSuchElementException();
                T dado = atual.dado;
                atual = atual.prox;
                return dado;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (No<T> n = inicio; n != null; n = n.prox) {
            sb.append(n.dado);
            if (n.prox != null) sb.append(" <-> ");
        }
        return sb.append("]").toString();
    }

    public String toStringReverso() {
        StringBuilder sb = new StringBuilder("[");
        for (No<T> n = fim; n != null; n = n.ant) {
            sb.append(n.dado);
            if (n.ant != null) sb.append(" <-> ");
        }
        return sb.append("]").toString();
    }


    private void checarIndice(int pos) {
        if (pos < 0 || pos >= tamanho)
            throw new IndexOutOfBoundsException("Posição inválida: " + pos);
    }

    private No<T> noEm(int pos) {
        No<T> atual;
        if (pos < tamanho / 2) {
            atual = inicio;
            for (int i = 0; i < pos; i++) atual = atual.prox;
        } else {
            atual = fim;
            for (int i = tamanho - 1; i > pos; i--) atual = atual.ant;
        }
        return atual;
    }

    private T desligar(No<T> no) {
        if (no.ant == null) inicio = no.prox; else no.ant.prox = no.prox;
        if (no.prox == null) fim = no.ant;    else no.prox.ant = no.ant;
        no.ant = no.prox = null;
        tamanho--;
        return no.dado;
    }
}