import java.time.LocalDate;

import br.gov.cesarschool.poo.fidelidade.cliente.dao.ClienteDAO;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Endereco;
import br.gov.cesarschool.poo.fidelidade.cliente.negocio.ResultadoInclusaoCliente;
import br.gov.cesarschool.poo.fidelidade.util.StringUtil;
import br.gov.cesarschool.poo.fidelidade.util.ValidadorCPF;
import br.gov.cesarschool.poo.fidelidade.cartao.negocio.CartaoFidelidadeMediator;

public class ClienteMediator {
    
    private static ClienteMediator instancia;
    private ClienteDAO repositorioCliente;
    private CartaoFidelidadeMediator cartaoMediator;
    
    private ClienteMediator() {
        repositorioCliente = new ClienteDAO();
        cartaoMediator = CartaoFidelidadeMediator.getInstance();
    }
    
    public static synchronized ClienteMediator getInstance() {
        if (instancia == null) {
            instancia = new ClienteMediator();
        }
        return instancia;
    }
    
    public ResultadoInclusaoCliente incluir(Cliente cliente) {
        String mensagemErro = validar(cliente);
        if (mensagemErro != null) {
            return new ResultadoInclusaoCliente(0, mensagemErro);
        }
        String numeroCartao = cartaoMediator.gerarCartao(cliente);
        cliente.setNumeroCartao(numeroCartao);
        repositorioCliente.incluir(cliente);
        return new ResultadoInclusaoCliente(numeroCartao, null);
    }
    
    public String alterar(Cliente cliente) {
        String mensagemErro = validar(cliente);
        if (mensagemErro != null) {
            return mensagemErro;
        }
        Cliente clienteAntigo = repositorioCliente.buscar(cliente.getCpf());
        if (clienteAntigo == null) {
            return "Cliente não encontrado";
        }
        cliente.setNumeroCartao(clienteAntigo.getNumeroCartao());
        repositorioCliente.alterar(cliente);
        return null;
    }
    
    private String validar(Cliente cliente) {
        if (!ValidadorCPF.validar(cliente.getCpf())) {
            return "CPF inválido";
        }
        if (StringUtil.estaVazia(cliente.getNomeCompleto())) {
            return "Nome completo obrigatório";
        }
        if (cliente.getSexo() == null) {
            return "Sexo obrigatório";
        }
        LocalDate dataLimite = LocalDate.now().minusYears(17);
        if (cliente.getDataNascimento().isAfter(dataLimite)) {
            return "Cliente menor de idade";
        }
        if (cliente.getRenda() < 0) {
            return "Renda inválida";
        }
        Endereco endereco = cliente.getEndereco();
        if (endereco == null) {
            return "Endereço obrigatório";
        }
        if (StringUtil.estaVazia(endereco.getLogradouro()) || endereco.getLogradouro().length() < 4) {
            return "Logradouro inválido";
        }
        if (endereco.getNumero() < 0) {
            return "Número inválido";
        }
        if (StringUtil.estaVazia(endereco.getCidade())) {
            return "Cidade inválida";
        }
        if (StringUtil.estaVazia(endereco.getEstado())) {
            return "Estado inválido";
        }
        if (StringUtil.estaVazia(endereco.getPais())) {

            return "Pais inválido";
        }