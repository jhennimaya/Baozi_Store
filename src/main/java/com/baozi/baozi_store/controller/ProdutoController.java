    package com.baozi.baozi_store.controller;

    import com.baozi.baozi_store.model.Produto;
    import com.baozi.baozi_store.repository.ProdutoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.server.ResponseStatusException;

    import java.util.List;
    import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Produto> buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
public Produto atualizar(
        @PathVariable Long id,
        @RequestBody Produto produtoAtualizado) {

    Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Produto não encontrado"));

    produto.setNome(produtoAtualizado.getNome());
    produto.setPreco(produtoAtualizado.getPreco());
    produto.setEstoque(produtoAtualizado.getEstoque());

    return produtoRepository.save(produto);
}
    
}
