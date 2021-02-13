package Numeros;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LojinhaWEBTest {

    private WebDriver navegador;
 
    @Before
    public void setup() {
        System.setProperty("webdrvier.chrome.driver", "/usr/bin/chromedriver");
        navegador = new ChromeDriver();
        navegador.get("http://165.227.93.41/lojinha-web");
        navegador.findElement(By.id("usuario")).sendKeys("admin");
        navegador.findElement(By.id("senha")).sendKeys("admin");
        navegador.findElement(By.cssSelector(".btn")).click();
    }

    @After
    public void tearDown() {
        navegador.quit();
    }

    @Test
    public void testValidarDadosDeUmProduto() {
        navegador.findElements(By.linkText("PS4")).get(0).click();
        String produtoNome = navegador.findElement(By.id("produtonome")).getAttribute("value");
        Assert.assertEquals("PS4", produtoNome);

        String componenteNome = navegador.findElements(By.cssSelector(".title")).get(0).getText();
        Assert.assertEquals("Lojinha controle", componenteNome);
    }

    @Test
    public void testAdicionarUmNovoProduto() {
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();
        navegador.findElement(By.id("produtonome")).sendKeys("PS100");
        navegador.findElement(By.id("produtovalor")).sendKeys("40000");
        navegador.findElement(By.id("produtocores")).sendKeys("BRANCO,PRETO");
        navegador.findElements(By.cssSelector(".btn")).get(0).click();

        String mensagem = navegador.findElement(By.cssSelector(".toast")).getText();
        Assert.assertEquals("Produto adicionado com sucesso", mensagem);
    }
}
