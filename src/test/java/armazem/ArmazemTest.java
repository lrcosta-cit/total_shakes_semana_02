package armazem;

import br.com.TDD.armazem.Armazem;
import br.com.TDD.ingredientes.Base;
import br.com.TDD.ingredientes.Ingrediente;
import br.com.TDD.ingredientes.TipoBase;
import org.junit.jupiter.api.*;

public class ArmazemTest {

    Armazem armazem;

    @BeforeEach
    void setup(){
        armazem = new Armazem();
    }

    @Test
    public void deveCadastrarUmIngredienteNoEstoque() {
        //given
        Ingrediente ingrediente = new Base(TipoBase.Sorvete);

        //when
        armazem.cadastrarIngredienteEmEstoque(ingrediente);

        //then
        Assertions.assertEquals(1, armazem.getEstoque().size());
    }

    @Test
    public void deveRetornarExcecaoQuandoCadastrarUmIngredienteJaExistenteNoEstoque() {
        //given
        Ingrediente ingrediente = new Base(TipoBase.Sorvete);

        //when
        armazem.cadastrarIngredienteEmEstoque(ingrediente);
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            armazem.cadastrarIngredienteEmEstoque(ingrediente);
        });

        //then
        Assertions.assertEquals("Ingrediente já cadastrado", e.getMessage());

    }

    @Test
    public void deveDescadastrarUmIngredienteDoEstoque() {
        //given
        Ingrediente ingrediente = new Base(TipoBase.Iorgute);
        armazem.getEstoque().put(ingrediente, 0);

        //when
        armazem.descadastrarIngredienteEmEstoque(ingrediente);

        //then
        Assertions.assertEquals(0, armazem.getEstoque().size());
    }

    @Test
    public void deveRetornarExcecaoAoDescadastrarUmIngredienteNaoExistenteNoEstoque() {
        //given
        Ingrediente ingrediente = new Base(TipoBase.Iorgute);

        //when
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            armazem.descadastrarIngredienteEmEstoque(ingrediente);
        });

        //then
        Assertions.assertEquals("Ingrediente não encontrado", e.getMessage());
    }

}
