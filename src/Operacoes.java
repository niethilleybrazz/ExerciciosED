import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public final class Operacoes {
    private Operacoes() {
    }


    // 1. Inserção ordenada por atributo chave
    public static <T> void inserirOrdenado(Lista<T> lista, T elemento, Comparator<? super T> porChave) {
        int pos = 0;
        for (T atual : lista) {
            if (porChave.compare(atual, elemento) > 0) break;
            pos++;
        }
        lista.inserirEm(pos, elemento);
    }

    // 2. Partição de uma lista em duas outras
    public static class Particao<T> {
        public final Lista<T> atendem = new Lista<>();
        public final Lista<T> naoAtendem = new Lista<>();
    }

    public static <T> Particao<T> particionar(Lista<T> origem, Predicate<? super T> criterio) {
        Particao<T> p = new Particao<>();
        for (T x : origem) {
            if (criterio.test(x)) p.atendem.inserirFim(x);
            else p.naoAtendem.inserirFim(x);
        }
        return p;
    }

    // 3. Inversão de uma lista
    public static <T> Lista<T> inverter(Lista<T> original) {
        Pilha<T> pilha = new Pilha<>();
        for (T x : original) pilha.empilhar(x);

        Lista<T> invertida = new Lista<>();
        while (!pilha.estaVazia()) invertida.inserirFim(pilha.desempilhar());
        return invertida;
    }

    // 4. Combinação de diversas
    @SafeVarargs
    public static <T> Lista<T> concatenar(Lista<T>... listas) {
        Lista<T> resultado = new Lista<>();
        for (Lista<T> l : listas)
            for (T x : l) resultado.inserirFim(x);
        return resultado;
    }

    @SafeVarargs
    public static <T> Lista<T> intercalar(Lista<T>... listas) {
        Fila<Iterator<T>> fila = new Fila<>();
        for (Lista<T> l : listas) fila.enfileirar(l.iterator());

        Lista<T> resultado = new Lista<>();
        while (!fila.estaVazia()) {
            Iterator<T> it = fila.desenfileirar();
            if (it.hasNext()) {
                resultado.inserirFim(it.next());
                if (it.hasNext()) fila.enfileirar(it);
            }
        }
        return resultado;
    }

    @SafeVarargs
    public static <T> Lista<T> mesclarOrdenado(Comparator<? super T> porChave, Lista<T>... listas) {
        Lista<T> resultado = new Lista<>();
        for (Lista<T> l : listas) resultado = mesclarDuas(resultado, l, porChave);
        return resultado;
    }

    private static <T> Lista<T> mesclarDuas(Lista<T> a, Lista<T> b, Comparator<? super T> cmp) {
        Lista<T> r = new Lista<>();
        Cursor<T> ca = new Cursor<>(a);
        Cursor<T> cb = new Cursor<>(b);

        while (ca.valido && cb.valido) {
            if (cmp.compare(ca.atual, cb.atual) <= 0) {
                r.inserirFim(ca.atual);
                ca.avancar();
            } else {
                r.inserirFim(cb.atual);
                cb.avancar();
            }
        }
        while (ca.valido) { r.inserirFim(ca.atual); ca.avancar(); }
        while (cb.valido) { r.inserirFim(cb.atual); cb.avancar(); }
        return r;
    }

    // 5. Interseção entre duas listas
    public static <T> Lista<T> intersecao(Lista<T> a, Lista<T> b) {
        Lista<T> r = new Lista<>();
        for (T x : a) {
            if (b.contem(x) && !r.contem(x)) r.inserirFim(x);
        }
        return r;
    }

    // 6. Balance-line
    public static class ResultadoBalanceLine<T> {
        public final Lista<T> emAmbas = new Lista<>();     // elementos vindos de A
        public final Lista<T> somenteEmA = new Lista<>();
        public final Lista<T> somenteEmB = new Lista<>();
    }

    public static <T> ResultadoBalanceLine<T> balanceLine(Lista<T> a, Lista<T> b, Comparator<? super T> porChave) {
        ResultadoBalanceLine<T> res = new ResultadoBalanceLine<>();
        Cursor<T> ca = new Cursor<>(a);
        Cursor<T> cb = new Cursor<>(b);

        while (ca.valido && cb.valido) {
            int c = porChave.compare(ca.atual, cb.atual);
            if (c == 0) {
                res.emAmbas.inserirFim(ca.atual);
                ca.avancar();
                cb.avancar();
            } else if (c < 0) {
                res.somenteEmA.inserirFim(ca.atual);
                ca.avancar();
            } else {
                res.somenteEmB.inserirFim(cb.atual);
                cb.avancar();
            }
        }
        while (ca.valido) { res.somenteEmA.inserirFim(ca.atual); ca.avancar(); }
        while (cb.valido) { res.somenteEmB.inserirFim(cb.atual); cb.avancar(); }
        return res;
    }

    // 7. Balanceamento de parênteses ( ), [ ] e { }
    public static boolean parentesesBalanceados(String expressao) {
        Pilha<Character> pilha = new Pilha<>();
        for (char c : expressao.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    pilha.empilhar(c);
                    break;
                case ')':
                case ']':
                case '}':
                    if (pilha.estaVazia() || !combinam(pilha.desempilhar(), c)) return false;
                    break;
                default:
                    break;
            }
        }
        return pilha.estaVazia();
    }

    private static boolean combinam(char abre, char fecha) {
        return (abre == '(' && fecha == ')')
                || (abre == '[' && fecha == ']')
                || (abre == '{' && fecha == '}');
    }

    private static class Cursor<T> {
        private final Iterator<T> it;
        T atual;
        boolean valido;

        Cursor(Lista<T> lista) {
            it = lista.iterator();
            avancar();
        }

        void avancar() {
            valido = it.hasNext();
            atual = valido ? it.next() : null;
        }
    }
}