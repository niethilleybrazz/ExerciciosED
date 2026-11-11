import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        testarOperacoes();
    }

    private static void testarOperacoes() {
        Comparator<Integer> natural = Comparator.naturalOrder();
        Comparator<Aluno> porMatricula = Comparator.comparingInt(Aluno::getMatricula);

        // 1. Inserção ordenada
        Lista<Aluno> turma = new Lista<>();
        Operacoes.inserirOrdenado(turma, new Aluno(105, "Carla"), porMatricula);
        Operacoes.inserirOrdenado(turma, new Aluno(101, "Ana"), porMatricula);
        Operacoes.inserirOrdenado(turma, new Aluno(103, "Bruno"), porMatricula);
        System.out.println("\n1. Inserção ordenada: " + turma);

        // 2. Partição
        Lista<Integer> numeros = criar(1, 2, 3, 4, 5, 6);
        Operacoes.Particao<Integer> p = Operacoes.particionar(numeros, n -> n % 2 == 0);
        System.out.println("2. Partição: pares " + p.atendem + " | ímpares " + p.naoAtendem);

        // 3. Inversão com pilha
        System.out.println("3. Inversão: " + Operacoes.inverter(numeros));

        // 4. Combinação de listas
        Lista<Integer> l1 = criar(1, 4, 7), l2 = criar(2, 5, 8, 11), l3 = criar(3, 6);
        System.out.println("4. Concatenar: " + Operacoes.concatenar(l1, l2, l3));
        System.out.println("   Intercalar: " + Operacoes.intercalar(l1, l2, l3));
        System.out.println("   Mesclar:    " + Operacoes.mesclarOrdenado(natural, l1, l2, l3));

        // 5. Interseção
        System.out.println("5. Interseção: " + Operacoes.intersecao(criar(1, 2, 3, 4), criar(3, 4, 5)));

        // 6. Balance-line
        Operacoes.ResultadoBalanceLine<Integer> r =
                Operacoes.balanceLine(criar(1, 3, 5, 8), criar(2, 3, 8, 10), natural);
        System.out.println("6. Balance-line: em ambas " + r.emAmbas
                + " | só em A " + r.somenteEmA + " | só em B " + r.somenteEmB);

        // 7. Parênteses
        System.out.println("7. \"{[(a + b)]}\" balanceado? " + Operacoes.parentesesBalanceados("{[(a + b)]}"));
        System.out.println("   \"((a + b)\" balanceado?   " + Operacoes.parentesesBalanceados("((a + b)"));
    }

    @SafeVarargs
    private static <T> Lista<T> criar(T... itens) {
        Lista<T> l = new Lista<>();
        for (T x : itens) l.inserirFim(x);
        return l;
    }
}