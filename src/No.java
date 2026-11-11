public class No<T> {
    T dado;
    No<T> ant;
    No<T> prox;

    No(T dado) {
        this.dado = dado;
    }
}