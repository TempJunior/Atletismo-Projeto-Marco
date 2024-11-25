package services;

import models.Atleta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static services.AtletaService.geraNumeroDaCarteiraDoAtleta;

public class AtletaDAO {
    private Connection conn;
    AtletaService dadosAtleta = new AtletaService();

    AtletaDAO(Connection connection){
        this.conn = connection;
    }

    public void salvaNovoAtletaNoBanco(){
        String sql = "INSERT INTO atleta (carteiraDeCadastro, nome, pais, sexo, idade, melhorTempo)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        String carteirinha = geraNumeroDaCarteiraDoAtleta(6);


        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, carteirinha);
            preparedStatement.setString(2, dadosAtleta.cadastraNomeAtleta());
            preparedStatement.setString(3, dadosAtleta.cadastraPais());
            preparedStatement.setString(4, dadosAtleta.sexo());
            preparedStatement.setInt(5, dadosAtleta.cadastraIdade());
            preparedStatement.setDouble(6, dadosAtleta.melhorTempo());

            preparedStatement.execute();

            preparedStatement.close();
            conn.close();

            System.out.println("Atleta cadastrado com sucesso! ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Atleta> lista(){
        PreparedStatement ps;
        ResultSet resultSet;
        List<Atleta> atletas = new ArrayList<>();

        String sql = "SELECT * FROM atleta";

        try {
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();

            while (resultSet.next()){
                String carteirinha = resultSet.getString(1);
                String nome = resultSet.getString(2);
                String pais = resultSet.getString(3);
                String sexo = resultSet.getString(4);
                Integer idade = resultSet.getInt(5);
                Double melhorTempo = resultSet.getDouble(6);

                Atleta atleta = new Atleta(nome, pais, sexo, idade, melhorTempo, carteirinha);

                atletas.add(atleta);
            }
            ps.close();
            resultSet.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return atletas;
    }

    public Atleta retornaUmAtletaPorNumeroDaCarteirinha(String numeroCarteirinha) {
        Atleta atleta = null;
        String sql = "SELECT * FROM atleta WHERE carteiraDeCadastro = ?"; // Verifique se o nome da coluna está correto

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, numeroCarteirinha);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String carteirinha = resultSet.getString("carteiraDeCadastro"); // Use nomes de colunas
                String nome = resultSet.getString("nome");
                String pais = resultSet.getString("pais");
                String sexo = resultSet.getString("sexo");
                Integer idade = resultSet.getInt("idade");
                Double melhorTempo = resultSet.getDouble("melhorTempo");

                atleta = new Atleta(nome, pais, sexo, idade, melhorTempo, carteirinha);
            }

            resultSet.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return atleta;
    }

}
