import services.AtletaService;

import java.util.Scanner;

public class Application {
    private AtletaService aplication = new AtletaService();

    public static void main(String[] args) {
        Application main = new Application();
        main.exibeMenu();
    }

    public void exibeMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Opção 1: Cadastrar novo Atleta.");
                    aplication.cadastraNovoAtleta();
                    break;
                case 2:
                    System.out.println("Opção 2: Exibir todos atletas cadastrados.");
                    aplication.todosAtletasCadastrados();
                    break;
                case 3:
                    System.out.println("Opção 3: Exibir atleta mais rapido e do país vencedor. ");
                    aplication.atletaMaisRapidoEPaisVencedor();
                    break;
                case 4:
                    System.out.println("Excluir um usuario pelo numero de registro da carteirinha: ");
                    aplication.excluiAtleta();
                    break;
                case 5:
                    System.out.println("Buscar atleta por numero da carteirinha: ");
                    aplication.buscaAtletaPorCarteirinha();
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

    }

    private static void exibirMenu() {
        System.out.println("===== MENU =====");
        System.out.println("1. Cadastrar novo Atleta. ");
        System.out.println("2. Exibir todos atletas cadastrados. ");
        System.out.println("3. Exibir atleta mais rapido e do país vencedor. ");
        System.out.println("4. Excluir usuario pelo numero de registro na carteirinha. ");
        System.out.println("5. Buscar por numero da carteirinha. ");
        System.out.println("6. Sair do programa. ");
        System.out.println("================");
    }
}