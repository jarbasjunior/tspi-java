package Numeros;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import numeros.Numeros;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class LojinhaAPITest {

    private String token;

    @Before
    public void setup(){
        RestAssured.baseURI = "http://165.227.93.41";
        RestAssured.basePath = "lojinha";

        token = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                            "    \"usuariologin\": \"gilda-hamill\",\n" +
                            "    \"usuariosenha\": \"123456\"\n" +
                        "}")
            .when()
                .post("login")
            .then()
                .extract()
                    .path("data.token");
    }

    @Test
    public void testBuscarDadosDeUmProdutoEspeifico() {
        RestAssured
                .given()
                    .header("token", token)
                .when()
                    .get("/produto/9208")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("data.produtonome", Matchers.equalTo("Bacon"))
                        .body("data.componentes[0].componentenome", Matchers.equalTo("Chips"))
                        .body("message", Matchers.equalTo("Detalhando dados do produto"));
    }

    @Test
    public void testBuscarDadosDeUmComponenteEspeifico() {
        RestAssured
                .given()
                    .header("token", token)
                .when()
                    .get("/produto/9208/componente/4178")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("data.componentenome", Matchers.equalTo("Chips"))
                        .body("data.componentequantidade", Matchers.equalTo(1))
                        .body("message", Matchers.equalTo("Detalhando dados do componente de produto"));
    }
}
