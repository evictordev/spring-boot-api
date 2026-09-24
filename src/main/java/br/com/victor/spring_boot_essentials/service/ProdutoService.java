package br.com.victor.spring_boot_essentials.service;

import br.com.victor.spring_boot_essentials.database.model.ProdutoEntity;
import br.com.victor.spring_boot_essentials.dto.ProdutoDto;
import br.com.victor.spring_boot_essentials.exception.BadRequestException;
import br.com.victor.spring_boot_essentials.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private static final List<ProdutoEntity> PRODUTOS = new ArrayList<>();

    static {
        PRODUTOS.add(ProdutoEntity.builder()
                        .id(2)
                        .nome("Celular")
                        .preco(new BigDecimal(2000))
                        .quantidade(10)
                        .build());
        PRODUTOS.add(ProdutoEntity.builder()
                        .id(3)
                        .nome("Tablet")
                        .preco(new BigDecimal(20))
                        .quantidade(3000)
                        .build());
    }

    public List<ProdutoEntity> listAll() {
        return new ArrayList<>(PRODUTOS);
    }

    public ProdutoEntity createProduct(ProdutoDto produtoDto) throws BadRequestException {

        Integer indentificador = PRODUTOS.stream().mapToInt(ProdutoEntity::getId).max().orElse(0)+1;

        if (produtoDto.getNome() == null ||
                produtoDto.getPreco() == null ||
                produtoDto.getQuantidade() == null) {
            throw new BadRequestException("Todos os campos são obrigatórios");
        }

        ProdutoEntity newProductDto = ProdutoEntity.builder()
                .id(indentificador)
                .nome(produtoDto.getNome())
                .preco(produtoDto.getPreco())
                .quantidade(produtoDto.getQuantidade())
                .build();
        PRODUTOS.add(newProductDto);
        return newProductDto;
    }

    public ProdutoEntity updateProduct(ProdutoDto produtoDto, Integer id) throws NotFoundException {
        ProdutoEntity produto = PRODUTOS.stream().filter(p -> p.getId()
                                                    .equals(id))
                                                    .findAny()
                                                    .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        produto.setNome(produtoDto.getNome());
        produto.setPreco(produtoDto.getPreco());
        produto.setQuantidade(produtoDto.getQuantidade());

        return produto;
    }

    public void deleteProduct(Integer id){
        PRODUTOS.removeIf(p -> p.getId().equals(id));
    }
}
