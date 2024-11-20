package services;

import exceptionsValidation.ValidaIdadeException;
import models.Atleta;
import models.PaisesValidos;

import java.util.*;

public class AtletaService {
    private Scanner leitura = new Scanner(System.in);
    private List<Atleta> listaDeAtletas = new ArrayList<>();

    private Atleta criaAtleta(String nome, Enum<PaisesValidos> pais, char sexo, int idade, double melhorTempo) {
        return new Atleta(nome, pais, sexo, idade, melhorTempo);
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
                    cadastraNovoAtleta();
                    break;
                case 2:
                    System.out.println("Opção 2: Exibir todos atletas cadastrados.");
                    todosAtletasCadastrados();
                    break;
                case 3:
                    System.out.println("Opção 3: Exibir atleta mais rapido e do país vencedor. ");
                    atletaMaisRapidoEPaisVencedor();
                    break;
                case 4:
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
        System.out.println("4. Sair");
        System.out.println("================");
    }

    private void atletaMaisRapidoEPaisVencedor(){
        List<Atleta> listaColetada = Collections.singletonList(listaDeAtletas.stream()
                .sorted(Comparator.comparing(Atleta::getMelhorTempo))
                .toList()
                .getFirst());

        listaColetada.forEach(System.out::println);
    }

    private List<Atleta> todosAtletasCadastrados() {
        if (listaDeAtletas.isEmpty()) {
            System.out.println("A lista está vazia! Nenhum atleta está cadastrado.");
        } else {
            listaDeAtletas.forEach(System.out::println);
        }
        return new ArrayList<>(listaDeAtletas);
    }


    private void cadastraNovoAtleta() {
        try {
            Atleta atletas = criaAtleta(cadastraNomeAtleta(), cadastraPais(), sexo(), cadastraIdade(), melhorTempo());
            listaDeAtletas.add(atletas);
            System.out.println("Atleta cadastrado com sucesso! ");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    private String cadastraNomeAtleta() {
        String nome = "";
        try {
            System.out.println("Qual o nome do atleta? ");
            nome = leitura.nextLine();
            if(nome.isEmpty()){
                nome = leitura.nextLine();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Valor invalido! ");
        }
        return nome;
    }

    private Enum<PaisesValidos> cadastraPais() {
        PaisesValidos paisEscolhido = null;
        while (paisEscolhido == null) {
            System.out.print("Escolha um país (MEX, USA, CAN): ");
            String input = leitura.nextLine().toUpperCase();

            try {
                paisEscolhido = PaisesValidos.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("País inválido. Por favor, tente novamente.");
            }
        }
        return paisEscolhido;
    }

    private int cadastraIdade() {
        int idade = 0;
        boolean idadeVerdadeira = false;
        do {
            try {
                System.out.println("Qual a idade do Atleta? (A idade deve ser entre 18 e 35 anos)");
                idade = leitura.nextInt();
                if (idade < 18 || idade > 35) {
                    throw new ValidaIdadeException("Idade invalida. Apenas pessoas entre 18 e 35 anos podem ser cadastradas!");
                } else {
                    idadeVerdadeira = true;
                }
            } catch (ValidaIdadeException e) {
                System.out.println(e.getMessage());
            }
        } while (!idadeVerdadeira);

        return idade;
    }

    private char sexo() {
        String sexo = "";
        try {
            System.out.println("Digite o sexo do Atleta: 'M' OU 'F' ");
            sexo = leitura.nextLine();

        } catch (IllegalArgumentException e) {
            System.out.println("Valor invalido, digite apenas 'M' ou 'F' ");
        }

        return sexo.charAt(0);
    }

    private double melhorTempo() {
        System.out.println("Qual o melhor tempo do atleta? ");
        double tempo = leitura.nextDouble();

        return tempo;
    }

}