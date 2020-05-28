package com.empresa.cadastro.controller;

import com.empresa.cadastro.messages.ProdutoException;
import com.empresa.cadastro.model.Produto;
import com.empresa.cadastro.model.ProdutoViewModel;
import com.empresa.cadastro.model.Mapper;
import com.empresa.cadastro.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    private Mapper mapper;

    public ProdutoController(ProdutoRepository produtoRepository, Mapper mapper) {
        this.produtoRepository = produtoRepository;
        this.mapper = mapper;
    }

    @GetMapping("/list")
    public Iterable<Produto> list(){
        return this.produtoRepository.findAll();
    }

    @GetMapping("/findByNome")
    public Iterable<Produto> findByNome(@RequestParam (value = "q") String nome) {
        return this.produtoRepository.findProdutosByNomeContaining(nome);
    }

    @PostMapping("/save")
    public Produto save(@Valid @RequestBody ProdutoViewModel produto) {
        return this.produtoRepository.save(this.mapper.convertToProdutoEntity(produto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            this.produtoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ProdutoException(e);
        } catch (Exception e) {
            throw new ProdutoException(id);
        }
    }

}
