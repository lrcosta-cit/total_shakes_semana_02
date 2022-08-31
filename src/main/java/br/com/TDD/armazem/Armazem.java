package br.com.TDD.armazem;

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

    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {

    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {

    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {

    }

    public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {
        return 0;
    }



}
