package br.com.vvatte;

import br.com.vvatte.dao.ClienteDAO;
import br.com.vvatte.dao.IClienteDAO;
import br.com.vvatte.domain.Cliente;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ClienteTest {

    private IClienteDAO clienteDAO;

    @Test
    public void cadastrarTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCpf(7l);
        cliente.setNome("Vinicius");
        cliente.setTel(9955221543l);
        cliente.setEndereco("Brasil");
        cliente.setNumero(27l);
        cliente.setCidade("Campo Bom");
        cliente.setEstado("RS");
        Integer quantidade = clienteDAO.cadastrar(cliente);
        assertTrue(quantidade == 1);

        Cliente clienteDB = clienteDAO.consultar(cliente.getCpf());
        assertNotNull(clienteDB);
        assertNotNull(clienteDB.getId());
        assertEquals(cliente.getCpf(), clienteDB.getCpf());
        assertEquals(cliente.getNome(), clienteDB.getNome());

        Integer quantidadeDel =clienteDAO.excluir(clienteDB);
        assertNotNull(quantidadeDel);
    }

    @Test
    public void consultarTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCpf(4l);
        cliente.setNome("Benjamin");
        cliente.setTel(42562189l);
        cliente.setEndereco("Irlanda");
        cliente.setNumero(2l);
        cliente.setCidade("Belfast");
        cliente.setEstado("Bel");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.consultar(4l);
        assertNotNull(clienteBD);
        assertEquals(cliente.getCpf(), clienteBD.getCpf());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = clienteDAO.excluir(clienteBD);
        assertTrue(countDel == 1);
    }

    @Test
    public void excluirTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCpf(9l);
        cliente.setNome("Mary");
        cliente.setTel(42562189l);
        cliente.setEndereco("Alemanha");
        cliente.setNumero(6l);
        cliente.setCidade("Berlin");
        cliente.setEstado("Ber");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.consultar(9l);
        assertNotNull(clienteBD);
        assertEquals(cliente.getCpf(), clienteBD.getCpf());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = clienteDAO.excluir(clienteBD);
        assertTrue(countDel == 1);
    }

    @Test
    public void buscarTodos() throws Exception{
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCpf(9l);
        cliente.setNome("Mary");
        cliente.setTel(42562189l);
        cliente.setEndereco("Alemanha");
        cliente.setNumero(6l);
        cliente.setCidade("Berlin");
        cliente.setEstado("Ber");
        Integer quantidade = clienteDAO.cadastrar(cliente);
        assertTrue(quantidade == 1);

        Cliente cliente2 = new Cliente();
        cliente2.setCpf(4l);
        cliente2.setNome("Benjamin");
        cliente2.setTel(42562189l);
        cliente2.setEndereco("Irlanda");
        cliente2.setNumero(2l);
        cliente2.setCidade("Belfast");
        cliente2.setEstado("Bel");
        Integer quantidade2 = clienteDAO.cadastrar(cliente2);
        assertTrue(quantidade2 == 1);

        Cliente cliente3 = new Cliente();
        cliente3.setCpf(7l);
        cliente3.setNome("Vinicius");
        cliente3.setTel(9955221543l);
        cliente3.setEndereco("Brasil");
        cliente3.setNumero(27l);
        cliente3.setCidade("Campo Bom");
        cliente3.setEstado("RS");
        Integer quantidade3 = clienteDAO.cadastrar(cliente3);
        assertTrue(quantidade3 == 1);

        List<Cliente> list = clienteDAO.consultarTodos();
        assertNotNull(list);
        assertEquals(3, list.size());

        int countDel = 0;
        for (Cliente cli : list) {
            clienteDAO.excluir(cli);
            countDel++;
        }
        assertEquals(list.size(), countDel);

        list = clienteDAO.consultarTodos();
        assertEquals(list.size(), 0);

    }

    @Test
    public void atualizarTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCpf(7l);
        cliente.setNome("Vinicius");
        cliente.setTel(9955221543l);
        cliente.setEndereco("Brasil");
        cliente.setNumero(27l);
        cliente.setCidade("Campo Bom");
        cliente.setEstado("RS");
        Integer quantidade = clienteDAO.cadastrar(cliente);
        assertTrue(quantidade == 1);

        Cliente clienteBD = clienteDAO.consultar(7l);
        assertNotNull(clienteBD);
        assertEquals(cliente.getCpf(), clienteBD.getCpf());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteBD.setCpf(20l);
        clienteBD.setNome("Ronaldo");
        clienteBD.setTel(51573512l);
        clienteBD.setEndereco("Russia");
        clienteBD.setNumero(3l);
        clienteBD.setCidade("Porto");
        clienteBD.setEstado("Uss");
        Integer quantidade2 = clienteDAO.atualizar(clienteBD);
        assertTrue(quantidade2 == 1);

        Cliente clienteBD1 = clienteDAO.consultar(7l);
        assertNull(clienteBD1);

        Cliente clienteBD2 = clienteDAO.consultar(20l);
        assertNotNull(clienteBD2);
        assertEquals(clienteBD.getId(), clienteBD2.getId());
        assertEquals(clienteBD.getCpf(), clienteBD2.getCpf());
        assertEquals(clienteBD.getNome(), clienteBD2.getNome());

        List<Cliente> list = clienteDAO.consultarTodos();
        for (Cliente cli : list) {
            clienteDAO.excluir(cli);
        }
    }
}
