package br.com.TDD.armazem;

import br.com.TDD.exceptions.DuplicatedIngredienteException;
import br.com.TDD.exceptions.IngredienteNotFoundException;
import br.com.TDD.ingredientes.Ingrediente;

import java.util.TreeMap;

public class Armazem {

    private TreeMap<Ingrediente, Integer> estoque;

    public Armazem() {
        this.estoque = new TreeMap<Ingrediente, Integer>();
    }

    public Armazem(TreeMap<Ingrediente, Integer> estoque) {
        this.estoque = estoque;
    }

    public TreeMap<Ingrediente, Integer> getEstoque() {
        return estoque;
    }

    public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        validaIngrediente(ingrediente);
        if (this.estoque.containsKey(ingrediente)) {
            throw new DuplicatedIngredienteException("Ingrediente já cadastrado");
        }
        this.estoque.put(ingrediente, 0);
    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        validaIngrediente(ingrediente);
        if (!this.estoque.containsKey(ingrediente)){
            throw new IngredienteNotFoundException("Ingrediente não encontrado");
        }
        this.estoque.remove(ingrediente);

    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
        validaIngrediente(ingrediente);
        validaQuantidade(quantidade);
        if(!this.estoque.containsKey(ingrediente)){
            throw new IngredienteNotFoundException("Ingrediente não encontrado");
        }
        this.estoque.replace(ingrediente, this.estoque.get(ingrediente), this.estoque.get(ingrediente) + quantidade);
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
        validaIngrediente(ingrediente);
        validaQuantidade(quantidade);
        if(!this.estoque.containsKey(ingrediente)) {
            throw new IngredienteNotFoundException("Ingrediente não encontrado");
        }
        if(this.estoque.get(ingrediente) < quantidade) {
            throw new IllegalArgumentException("Quantidade inválida");
        }
        this.estoque.replace(ingrediente, this.estoque.get(ingrediente), this.estoque.get(ingrediente) - quantidade);
    }

    public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {
        validaIngrediente(ingrediente);
        if(!this.estoque.containsKey(ingrediente)){
            throw new IngredienteNotFoundException("Ingrediente não encontrado");
        }
        return this.estoque.get(ingrediente);
    }

    public void validaIngrediente(Ingrediente ingrediente){
        if(ingrediente == null){
            throw new IllegalArgumentException("Ingrediente inválido");
        }
    }

    public void validaQuantidade(Integer quantidade){
        if(quantidade <= 0){
            throw new IllegalArgumentException("Quantidade inválida");
        }
    }

}
