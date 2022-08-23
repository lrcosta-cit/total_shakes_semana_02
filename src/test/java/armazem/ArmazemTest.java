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
        armazem.cadastrarIngredienteEmEstoque(ingrediente);

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        });

    }

}
