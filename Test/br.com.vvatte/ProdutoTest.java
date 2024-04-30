package br.com.vvatte;

import br.com.vvatte.dao.IProdutoDAO;
import br.com.vvatte.dao.ProdutoDAO;
import br.com.vvatte.domain.Cliente;
import br.com.vvatte.domain.Produto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProdutoTest {

    private IProdutoDAO produtoDAO;

    @Test
    public void cadastrarTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setNome("Arroz");
        produto.setCodigo(14l);
        Integer quantidade = produtoDAO.cadastrar(produto);
        assertTrue(quantidade == 1);

        Produto produtoDB = produtoDAO.consultar(produto.getCodigo());
        assertNotNull(produtoDB);
        assertNotNull(produtoDB.getId());
        assertEquals(produto.getCodigo(), produtoDB.getCodigo());
        assertEquals(produto.getNome(), produtoDB.getNome());

        Integer quantDel = produtoDAO.excluir(produtoDB);
        assertNotNull(quantDel == 1);
    }

    @Test
    public void consultarTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo(27l);
        produto.setNome("carne");
        Integer quantia = produtoDAO.cadastrar(produto);
        assertTrue(quantia == 1);

        Produto produtoDB = produtoDAO.consultar(27l);
        assertNotNull(produtoDB);
        assertEquals(produto.getCodigo(), produtoDB.getCodigo());
        assertEquals(produto.getNome(), produtoDB.getNome());

        Integer contDel = produtoDAO.excluir(produtoDB);
        assertTrue(contDel == 1);
    }

    @Test
    public void excluirTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo(21l);
        produto.setNome("queijo");
        Integer quantia = produtoDAO.cadastrar(produto);
        assertTrue(quantia == 1);

        Produto produtoDB = produtoDAO.consultar(21l);
        assertNotNull(produtoDB);
        assertEquals(produto.getCodigo(), produtoDB.getCodigo());
        assertEquals(produto.getNome(), produtoDB.getNome());

        Integer contDel = produtoDAO.excluir(produtoDB);
        assertTrue(contDel == 1);
    }

    @Test
    public void buscarTodos() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo(21l);
        produto.setNome("queijo");
        Integer quantia = produtoDAO.cadastrar(produto);
        assertTrue(quantia == 1);

        Produto produto2 = new Produto();
        produto2.setCodigo(27l);
        produto2.setNome("carne");
        Integer quantia2 = produtoDAO.cadastrar(produto2);
        assertTrue(quantia2 == 1);

        Produto produto3 = new Produto();
        produto3.setNome("Arroz");
        produto3.setCodigo(14l);
        Integer quantidade = produtoDAO.cadastrar(produto3);
        assertTrue(quantidade == 1);

        List<Produto> list = produtoDAO.consultarTodos();
        assertNotNull(list);
        assertEquals(3, list.size());

        int contDel = 0;
        for (Produto prod : list) {
            produtoDAO.excluir(prod);
            contDel++;
        }
        assertEquals(list.size(), contDel);

        list = produtoDAO.consultarTodos();
        assertEquals(list.size(), 0);
    }

    @Test
    public void atualizarTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo(21l);
        produto.setNome("queijo");
        Integer quantia = produtoDAO.cadastrar(produto);
        assertTrue(quantia == 1);

        Produto produtoBD = produtoDAO.consultar(21l);
        assertNotNull(produtoBD);
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        produtoBD.setCodigo(13l);
        produtoBD.setNome("pao");
        Integer qtd = produtoDAO.atualizar(produtoBD);
        assertTrue(qtd == 1);

    }
}
