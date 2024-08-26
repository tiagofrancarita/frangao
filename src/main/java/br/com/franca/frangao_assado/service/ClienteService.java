package br.com.franca.frangao_assado.service;

import br.com.franca.frangao_assado.entity.Cliente;
import br.com.franca.frangao_assado.entity.Endereco;
import br.com.franca.frangao_assado.entity.dto.ClienteAtualizacaoDTO;
import br.com.franca.frangao_assado.entity.dto.EnderecoDTO;
import br.com.franca.frangao_assado.entity.enums.TipoEndereco;
import br.com.franca.frangao_assado.exception.ClienteNotFoundException;
import br.com.franca.frangao_assado.repository.ClienteRepository;
import br.com.franca.frangao_assado.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public Cliente criarCliente(Cliente cliente, Endereco endereco) {
        // Validar o cliente antes de salvar
        validarCliente(cliente);

        // Salvar o cliente para gerar o ID
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Definir tipo e ID para o endereço usando o ID gerado do cliente
        endereco.setTipoEndereco(TipoEndereco.CLIENTE);
        endereco.setEntidadeId(clienteSalvo.getId()); // Use o ID do cliente salvo

        // Salvar o endereço com o ID correto
        enderecoRepository.save(endereco);

        return clienteSalvo;
    }

    public List<Cliente> buscarTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new ClienteNotFoundException("Nenhum cliente cadastrado.");
        }
        return clientes;
    }

    public Cliente buscarClientePorId(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente atualizarCliente(UUID clienteId, ClienteAtualizacaoDTO clienteAtualizacaoDTO) {
        // Verificar se o cliente existe
        Cliente clienteExistente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualizar os dados do cliente
        clienteExistente.setNome(clienteAtualizacaoDTO.getNome());
        clienteExistente.setEmail(clienteAtualizacaoDTO.getEmail());
        clienteExistente.setTelefone(clienteAtualizacaoDTO.getTelefone());
        clienteExistente.setDataNascimento(clienteAtualizacaoDTO.getDataNascimento());

        // Atualizar o endereço se fornecido
        if (clienteAtualizacaoDTO.getEndereco() != null) {
            Endereco enderecoExistente = enderecoRepository.findByEntidadeIdAndTipoEndereco(clienteId, TipoEndereco.CLIENTE)
                    .orElse(new Endereco()); // Cria novo endereço se não existir

            // Atualizar os dados do endereço
            enderecoExistente.setRua(clienteAtualizacaoDTO.getEndereco().getRua());
            enderecoExistente.setNumero(clienteAtualizacaoDTO.getEndereco().getNumero());
            enderecoExistente.setBairro(clienteAtualizacaoDTO.getEndereco().getBairro());
            enderecoExistente.setCidade(clienteAtualizacaoDTO.getEndereco().getCidade());
            enderecoExistente.setEstado(clienteAtualizacaoDTO.getEndereco().getEstado());
            enderecoExistente.setCep(clienteAtualizacaoDTO.getEndereco().getCep());

            // Associa o endereço ao cliente
            enderecoExistente.setEntidadeId(clienteExistente.getId());
            enderecoExistente.setTipoEndereco(TipoEndereco.CLIENTE);

            // Salva o endereço
            enderecoRepository.save(enderecoExistente);
        }

        // Salvar o cliente atualizado
        return clienteRepository.save(clienteExistente);
    }

    @Transactional
    public void deletarCliente(UUID clienteId) {
        // Verificar se o cliente existe
        Cliente clienteExistente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Verificar e deletar o endereço associado
        enderecoRepository.deleteByEntidadeIdAndTipoEndereco(clienteId, TipoEndereco.CLIENTE);

        // Deletar o cliente
        clienteRepository.delete(clienteExistente);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getNome().length() < 10) {
            throw new ValidationException("Nome deve ter pelo menos 10 caracteres");
        }

        if (!StringUtils.hasText(cliente.getEmail()) || !cliente.getEmail().contains("@")) {
            throw new ValidationException("E-mail inválido");
        }

        if (cliente.getDataNascimento().after(new Date())) {
            throw new ValidationException("Data de nascimento deve ser anterior à data atual");
        }

        if (cliente.getTelefone().length() < 10) {
            throw new ValidationException("Telefone deve ter pelo menos 10 caracteres");
        }
    }


}
