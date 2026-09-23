package br.com.victor.spring_boot_essentials.controller;

import br.com.victor.spring_boot_essentials.database.model.ProdutoEntity;
import br.com.victor.spring_boot_essentials.dto.ProdutoDto;
import br.com.victor.spring_boot_essentials.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/produtos")
@RequiredArgsConstructor

public class ProdutoController {

    private final ProdutoService produtoService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoEntity> getProdutoService() {
        return produtoService.listAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoEntity createProduct(@RequestBody ProdutoDto produtoDto){
        return produtoService.createProduct(produtoDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoEntity updateProduct(@PathVariable Integer id, @RequestBody ProdutoDto produtoDto){
        return produtoService.updateProduct(produtoDto, id);
    }
}
