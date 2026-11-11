public class Aluno {
    final int matricula;
    final String nome;

    public Aluno(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return matricula + ":" + nome;
    }
}