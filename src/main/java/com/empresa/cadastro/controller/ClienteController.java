package com.empresa.cadastro.controller;

import com.empresa.cadastro.messages.ClienteException;
import com.empresa.cadastro.model.Cliente;
import com.empresa.cadastro.model.ClienteViewModel;
import com.empresa.cadastro.repository.ClienteRepository;
import com.empresa.cadastro.model.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    private Mapper mapper;

    public ClienteController(ClienteRepository clienteRepository, Mapper mapper) {
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public String index(){
        return "Cadastro Clientes API";
    }

    @GetMapping("/list")
    public Iterable<Cliente> list(){
        return clienteRepository.findAll();
    }

    @GetMapping("/findByNome")
    public Iterable<Cliente> findByNome(@RequestParam (value = "q") String nome) {
       return clienteRepository.findClientesByNomeContaining(nome);
    }

    @PostMapping("/save")
    public Cliente save(@Valid @RequestBody ClienteViewModel cliente) {
        try {
            return this.clienteRepository.save(this.mapper.convertToClienteEntity(cliente));
        } catch (DateTimeParseException e) {
            throw new ClienteException(LocalDate.class, cliente.getDataNascimento());
        } catch (Exception e) {
            throw new ClienteException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ClienteException(e);
        } catch (Exception e) {
            throw new ClienteException(id);
        }
    }

}
