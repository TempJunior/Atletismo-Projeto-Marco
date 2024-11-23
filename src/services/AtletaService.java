package services;

import dao.ConnectionFactory;
import exceptionsValidation.ValidaIdadeException;
import models.Atleta;
import models.PaisesValidos;
import models.SexoPermitido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class AtletaService {

    private ConnectionFactory connection;

    //Metodo construtor de AtletaService para fazer conexão com ConnectionFactory
    public AtletaService(){
        this.connection = new ConnectionFactory();
    }
    private Scanner leitura = new Scanner(System.in);
    private List<Atleta> listaDeAtletas = new ArrayList<>();

    @Deprecated
    private Atleta criaAtleta(String nome, String pais, String sexo, int idade, double melhorTempo, String carteirinha) {
        return new Atleta(nome, pais, sexo, idade, melhorTempo, carteirinha);
    }


    public void excluiAtleta(){
        boolean atletaEncontrado = false;

        while(!atletaEncontrado) {
            System.out.println("Qual o número da carteirinha que deseja excluir?");
            String numeroCarteirinha = leitura.nextLine();

            if(numeroCarteirinha.isEmpty()){
                numeroCarteirinha = leitura.nextLine();
            }

            Iterator<Atleta> iterator = listaDeAtletas.iterator();

            while (iterator.hasNext()) {
                Atleta atleta = iterator.next();
                if (atleta.getCarteiraDeCadastro().equals(numeroCarteirinha)) {
                    iterator.remove();
                    atletaEncontrado = true;
                    System.out.println("Atleta excluído com sucesso!");
                    break; // Continua no loop para não interromper a execução do menu
                }
            }

            if (!atletaEncontrado) {
                System.out.println("Não existe essa carteirinha.");
            }
        }
    }

    public static String geraNumeroDaCarteiraDoAtleta(int n) {
        Random random = new Random();
        StringBuilder sequencia = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int numero = random.nextInt(10); // Gera um número entre 0 e 9
            sequencia.append(numero);
        }

        return sequencia.toString();
    }

    public void atletaMaisRapidoEPaisVencedor(){
        try{
            List<Atleta> listaColetada = Collections.singletonList(listaDeAtletas.stream()
                    .sorted(Comparator.comparing(Atleta::getMelhorTempo))
                    .toList()
                    .getFirst());

            listaColetada.forEach(System.out::println);

        }catch (NoSuchElementException e){
            System.out.println("Nenhum atleta cadastrado. ");
        }

    }

    public List<Atleta> todosAtletasCadastrados() {
        if (listaDeAtletas.isEmpty()) {
            System.out.println("A lista está vazia! Nenhum atleta está cadastrado.");
        } else {
            listaDeAtletas.forEach(System.out::println);
        }
        return new ArrayList<>(listaDeAtletas);
    }


    public void cadastraNovoAtleta() {

        String sql = "INSERT INTO atleta (carteiraDeCadastro, nome, pais, sexo, idade, melhorTempo)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        String carteirinha = geraNumeroDaCarteiraDoAtleta(6);

        Connection conn = connection.recuperaConection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, carteirinha);
            preparedStatement.setString(2,cadastraNomeAtleta());
            preparedStatement.setString(3, cadastraPais());
            preparedStatement.setString(4, sexo());
            preparedStatement.setInt(5, cadastraIdade());
            preparedStatement.setDouble(6, melhorTempo());

            preparedStatement.execute();

            System.out.println("Atleta cadastrado com sucesso! ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    //Metodo para fazer o CASTING do Enum<PaisesValidos> para String
    public static String paisToString(PaisesValidos pais) {
        return pais != null ? pais.name() : null;
    }

    private String cadastraPais() {
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
        return paisToString(paisEscolhido);
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

    //Metodo para fazer o CASTING do Enum<SexoPermitido> para String
    public static String sexoToString(SexoPermitido sexo) {
        return sexo != null ? sexo.name() : null;
    }

    private String sexo() {
        SexoPermitido sexoEscolhido = null;
        while (sexoEscolhido == null) {
            System.out.print("Escolha um sexo ('F', 'FEMININO' OU 'M', 'MASCULINO'): ");
            String input = leitura.nextLine().toUpperCase();

            try {
                sexoEscolhido = SexoPermitido.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Sexo inválido. Por favor, tente novamente.");
            }
        }
        return sexoToString(sexoEscolhido);
    }

    private double melhorTempo() {
        System.out.println("Qual o melhor tempo do atleta? ");
        double tempo = leitura.nextDouble();

        return tempo;
    }

}