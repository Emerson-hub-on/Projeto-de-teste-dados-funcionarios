import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

     @Override
    public String toString() {
        return String.format("| %-10s | %-10s | %13s | %-15s |",
                getNome(),
                getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                String.format("%,.2f", salario),
                funcao);
            }

    

}
