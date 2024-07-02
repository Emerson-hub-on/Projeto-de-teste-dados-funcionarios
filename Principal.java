import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static List<Funcionario> funcionarios = new ArrayList<>();

    public static void main(String[] args) {

        // Declaração da função de inserir todos os funcionários na lista
        initializeFuncionarios();

        // Declaração da função de remover "João" da lista
        funcionarios.removeIf(func -> func.getNome().equalsIgnoreCase("João"));

        // Declaração da função de imprimir todos os funcionários
        printFuncionarios(funcionarios);

        // Declaração da função de 10% de aumento no salário 
        funcionarios.forEach(func -> func.setSalario(func.getSalario().multiply(BigDecimal.valueOf(1.1))));

        // Declaração da função de agrupar funcionários por função
        Map<String, List<Funcionario>> groupedByFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        // Declaração da função de imprimir todos os funcionários separados por função
        printGroupedByFuncao(groupedByFuncao);

        // Declaração da função de Imprimir funcionários com data de aniversário no mês 10 e 12
        printBirthdaysInMonths(10, 12);

        // Declaração de função para imprimir o funcionário mais velho
        printOldestEmployee();

        // Declaração da função de imprimir os funcionários em ordem alfabética
        printFuncionariosAlphabetically();

        // Declaração da função de imprimir o total dos salários dos funcionários
        printTotalSalary();

        // Declaração da função de imprimir quantos salários mínimos ganha cada funcionário
        printSalariesRelativeToMinimumWage();
    }

    private static void initializeFuncionarios() {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }

    private static void printFuncionarios(List<Funcionario> funcionarios) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(String.format("%-15s %-15s %-15s %-15s", "Nome", "Data Nascimento", "Salário", "Função"));
        System.out.println("-------------------------------------------------------------");
        for (Funcionario func : funcionarios) {
            System.out.println(String.format("%-15s %-15s %-15.2f %-15s",
                    func.getNome(),
                    func.getDataNascimento().format(dateFormatter),
                    func.getSalario(),
                    func.getFuncao()));
        }
    }
    

    private static void printGroupedByFuncao(Map<String, List<Funcionario>> groupedByFuncao) {
        for (Map.Entry<String, List<Funcionario>> entry : groupedByFuncao.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            System.out.println(String.format("%-15s %-15s", "Nome", "Salário"));
            System.out.println("-----------------------------");
            for (Funcionario func : entry.getValue()) {
                System.out.println(String.format("%-15s %-15.2f", func.getNome(), func.getSalario()));
            }
        }
    }
    


    private static void printBirthdaysInMonths(int... months) {
        System.out.println("\nFuncionários com aniversário em outubro e dezembro:");
        System.out.println(String.format("%-15s %-15s", "Nome", "Data Nascimento"));
        System.out.println("-----------------------------");
        for (Funcionario func : funcionarios) {
            int month = func.getDataNascimento().getMonthValue();
            for (int m : months) {
                if (month == m) {
                    System.out.println(String.format("%-15s %-15s",
                            func.getNome(),
                            func.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                }
            }
        }
    }
    
    

    private static void printOldestEmployee() {
        Funcionario oldest = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        long idade = ChronoUnit.YEARS.between(oldest.getDataNascimento(), LocalDate.now());
        System.out.println("\nFuncionário com maior idade:");
        System.out.println(String.format("%-15s %-15s", "Nome", "Idade"));
        System.out.println("-----------------------------");
        System.out.println(String.format("%-15s %-15d", oldest.getNome(), idade));
    }
    

    private static void printFuncionariosAlphabetically() {
        List<Funcionario> sortedFuncionarios = new ArrayList<>(funcionarios);
        sortedFuncionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\nFuncionários em ordem alfabética: \n");
        printFuncionarios(sortedFuncionarios);
    }

    private static void printTotalSalary() {
        BigDecimal totalSalary = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários:");
        System.out.println(String.format("%-15s", "Total"));
        System.out.println("-----------------------------");
        System.out.println(String.format("%-15.2f", totalSalary));
    }
    
    
    private static void printSalariesRelativeToMinimumWage() {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários em relação ao salário mínimo:\n");
        System.out.println(String.format("%-15s %-15s %-15s", "Nome", "Salário", "Salários Mínimos"));
        System.out.println("-------------------------------------------------------------");
        for (Funcionario func : funcionarios) {
            BigDecimal salarioMinimos = func.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println(String.format("%-15s %-15.2f %-15.2f",
                    func.getNome(), func.getSalario(), salarioMinimos));
        }
    }
    
}
