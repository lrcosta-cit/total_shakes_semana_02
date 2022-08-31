package armazem;

import br.com.TDD.armazem.Armazem;
import br.com.TDD.exceptions.DuplicatedIngredienteException;
import br.com.TDD.exceptions.IngredienteNotFoundException;
import br.com.TDD.ingredientes.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArmazemTest {

    Armazem armazem;
    Ingrediente baseSorvete;
    Ingrediente baseIogurte;

    Ingrediente frutaBanana;

    @BeforeAll
    void init(){
        armazem = new Armazem();
        baseSorvete = new Base(TipoBase.Sorvete);
        baseIogurte = new Base(TipoBase.Iorgute);
        frutaBanana = new Fruta(TipoFruta.Banana);
    }

    @BeforeEach
    void setup(){
        armazem.getEstoque().put(baseSorvete, 5);
    }

    @AfterEach
    void clear(){
        if (armazem.getEstoque().size() > 0) {
            armazem.getEstoque().clear();
        }
    }

    @Test
    @DisplayName("Cadastrar Ingrediente no Estoque")
    public void deveCadastrarUmIngredienteNoEstoque() {
        armazem.cadastrarIngredienteEmEstoque(baseIogurte);
        Assertions.assertEquals(2, armazem.getEstoque().size());
    }

    @Test
    @DisplayName("Retornar Exceção ao Cadastrar Ingrediente já existente no estoque")
    public void deveRetornarExcecaoQuandoCadastrarUmIngredienteJaExistenteNoEstoque() {
        Assertions.assertThrows(DuplicatedIngredienteException.class, () -> {
            armazem.cadastrarIngredienteEmEstoque(baseSorvete);
        });
    }

    @Test
    @DisplayName("Descadastrar um Ingrediente do Estoque")
    public void deveDescadastrarUmIngredienteDoEstoque() {
        armazem.descadastrarIngredienteEmEstoque(baseSorvete);
        Assertions.assertEquals(0, armazem.getEstoque().size());
    }

    @Test
    @DisplayName("Retornar Exceção ao Descadastrar Ingrediente inexistente")
    public void deveRetornarExcecaoAoDescadastrarUmIngredienteNaoExistenteNoEstoque() {
        Assertions.assertThrows(IngredienteNotFoundException.class, () -> {
            armazem.descadastrarIngredienteEmEstoque(frutaBanana);
        });
    }

    @Test
    @DisplayName("Adicionar quantidade a Ingrediente já cadastrado")
    public void deveAdicionarQuantidadeDeIngredienteNoEstoque(){
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(baseSorvete, 1);
        Assertions.assertEquals(6, armazem.getEstoque().get(baseSorvete));
    }

    @Test
    @DisplayName("Retornar Exceção ao Adicionar Quantidade a Ingrediente não existente")
    public void deveRetornarExcecaoAoAdicionarQuantidadeAIngredienteNaoExistente(){
        Assertions.assertThrows(IngredienteNotFoundException.class, () -> {
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(frutaBanana, 1);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("Retornar Exceção ao Adicionar Quantidade Inválida ou Negativa a Ingrediente não existente")
    public void deveRetornarExcecaoAoAdicionarQuantidadeInvalidaAIngrediente(final int input){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(baseSorvete, input);
        });
    }

    @Test
    @DisplayName("Reduzir quantidade de Ingrediente no Estoque")
    public void deveReduzirQuantidadeDeIngredienteNoEstoque(){
        armazem.reduzirQuantidadeDoIngredienteEmEstoque(baseSorvete, 1);
        Assertions.assertEquals(4, armazem.getEstoque().get(baseSorvete));
    }

    @Test
    @DisplayName("Retornar exceção ao reduzir quantidade de Ingrediente inexistente no estque")
    public void deveRetornarExcecaoAoReduzirQuantidadeDeIngredienteNaoExistente(){
        Assertions.assertThrows(IngredienteNotFoundException.class, () -> {
           armazem.reduzirQuantidadeDoIngredienteEmEstoque(frutaBanana, 1);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("Retornar Exceção ao Reduzir quantidade inválida de Ingrediente do Estoque")
    public void deveRetornarExcecaoAoReduzirQuantidadeInvalidaAIngrediente(final int input){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           armazem.reduzirQuantidadeDoIngredienteEmEstoque(baseSorvete, input);
        });
    }

    @Test
    @DisplayName("Retornar a Quantidade em Estoque de um Ingrediente")
    public void deveRetornarAQuantidadeEmEstoqueDoIngredienteCadastrado(){
        int qtde = armazem.consultarQuantidadeDoIngredienteEmEstoque(baseSorvete);
        Assertions.assertEquals(5, qtde);
    }

    @Test
    @DisplayName("Retornar Exceção ao Consultar Quantidade de Ingrediente inexistente no estoque")
    public void deveRetornarExcecaoAoConsultarQuantidadeDeIngredienteNaoExistente(){
        Assertions.assertThrows(IngredienteNotFoundException.class, () -> {
            armazem.consultarQuantidadeDoIngredienteEmEstoque(frutaBanana);
        });
    }

}
