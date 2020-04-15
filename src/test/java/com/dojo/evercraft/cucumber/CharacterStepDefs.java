package com.dojo.evercraft.cucumber;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CharacterStepDefs {
    RestTemplate restTemplate = new RestTemplate();
    final WireMockServer wireMockServer = new WireMockServer(8089);

//    configureFor("localhost", wireMockServer.port());
//
//    stubFor(get(urlEqualTo("/boss"))
//            .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "text/json")
//                        .withBody("Darth Plagueis")));


    @Given("a new game is started")
    public void aNewGameIsStarted() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("startButton")).click();
    }

    @When("I create a character")
    public void iCreateACharacter() {
    }

    @Then("my character shows up in the game")
    public void myCharacterShowsUpInTheGame() {
    }
}
