package br.com.franca.frangao_assado.controller;

import br.com.franca.frangao_assado.entity.Cliente;
import br.com.franca.frangao_assado.entity.Endereco;
import br.com.franca.frangao_assado.entity.dto.ClienteAtualizacaoDTO;
import br.com.franca.frangao_assado.entity.dto.ClienteEnderecoDTO;
import br.com.franca.frangao_assado.entity.enums.TipoEndereco;
import br.com.franca.frangao_assado.exception.ClienteNotFoundException;
import br.com.franca.frangao_assado.service.ClienteService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/criarCliente")
    public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteEnderecoDTO clienteEnderecoDTO) {
        try {
            Cliente cliente = clienteEnderecoDTO.getCliente();
            Endereco endereco = clienteEnderecoDTO.getEndereco();
            Cliente clienteCriado = clienteService.criarCliente(cliente, endereco);
            return new ResponseEntity<>(clienteCriado, HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscarTodosClientes")
    public ResponseEntity<?> buscarTodosClientes() {
        try {
            List<Cliente> clientes = clienteService.buscarTodosClientes();
            return ResponseEntity.ok(clientes);
        } catch (ValidationException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("buscarClientePorId/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable UUID id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("atualizarCliente/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable("id") UUID id, @RequestBody ClienteAtualizacaoDTO clienteAtualizacaoDTO) {

        // Chama o serviço para atualizar o cliente e o endereço
        try {
            Cliente clienteAtualizadoSalvo = clienteService.atualizarCliente(id, clienteAtualizacaoDTO);
            return ResponseEntity.ok(clienteAtualizadoSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/deletarCliente/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") UUID id) {
        try {
            clienteService.deletarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}