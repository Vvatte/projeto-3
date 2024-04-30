package br.com.vvatte.dao;

import br.com.vvatte.dao.jdbc.ConnectionFactory;
import br.com.vvatte.domain.Cliente;
import br.com.vvatte.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO {
    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO TB_PRODUTO (ID, CODIGO, NOME) VALUES (NEXTVAL('SQ_PRODUTO'), ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, produto.getCodigo());
            stm.setString(2,produto.getNome());
            return stm.executeUpdate();
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (stm !=null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Produto consultar(Long codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM TB_PRODUTO WHERE CODIGO = ?";
            stm= connection.prepareStatement(sql);
            stm.setLong(1, codigo);
            rs = stm.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setCodigo(rs.getLong("codigo"));
                produto.setNome(rs.getString("nome"));
            }
            return produto;
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm !=null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer excluir(Produto produtoDB) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM TB_PRODUTO WHERE CODIGO = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, produtoDB.getCodigo());
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        } finally {
            if (stm !=null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public List<Produto> consultarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Produto> list = new ArrayList<>();
        Produto produto = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM TB_PRODUTO";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                produto = new Produto();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                Long codigo = rs.getLong("CODIGO");
                produto.setId(id);
                produto.setNome(nome);
                produto.setCodigo(codigo);
                list.add(produto);
            }
            return list;
        }catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer atualizar(Produto produtoBD) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE TB_PRODUTO SET NOME = ?, CODIGO = ? WHERE ID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, produtoBD.getNome());
            stm.setLong(2, produtoBD.getCodigo());
            stm.setLong(3, produtoBD.getId());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }


}
