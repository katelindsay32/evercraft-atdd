package com.dojo.evercraft.cucumber;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.PendingException;
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
WebDriver driver = new ChromeDriver();


    @Given("a new game is started")
    public void aNewGameIsStarted() {

        driver.get("http://localhost:8080/");
        driver.findElement(By.id("startButton")).click();
    }

    @When("I create a character")
    public void iCreateACharacter() {
        driver.findElement(By.id("characterName")).sendKeys("aragorn");
        driver.findElement(By.id("armourAmount")).sendKeys("10");
        driver.findElement(By.id("hitPoints")).sendKeys("5");
        driver.findElement(By.id("strength")).sendKeys("13");
        driver.findElement(By.id("dexterity")).sendKeys("13");
        driver.findElement(By.id("constitution")).sendKeys("13");
        driver.findElement(By.id("wisdom")).sendKeys("13");
        driver.findElement(By.id("intelligence")).sendKeys("13");
        driver.findElement(By.id("charisma")).sendKeys("13");

    }

    @Then("my character shows up in the game")
    public void myCharacterShowsUpInTheGame() {
        throw new PendingException();
    }
}
