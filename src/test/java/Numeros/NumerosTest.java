package Numeros;

import numeros.Numeros;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumerosTest {

    private Numeros numeros;

    @Before
    public void setup(){
        numeros = new Numeros();
    }

    @Test
    public void testValidarSeUmNumeroEUmaUNidade(){
        boolean eUnidadde = numeros.EUmaUnidade(9);
        Assert.assertTrue(eUnidadde);
    }

    @Test
    public void testValidarSeUmNumeroNaoEUmaUNidade(){
        boolean eUnidadde = numeros.EUmaUnidade(10);
        Assert.assertFalse(eUnidadde);
    }
}
