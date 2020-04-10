package com.dojo.evercraft.cucumber;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CharacterStepDefs {
    RestTemplate restTemplate = new RestTemplate();
    final WireMockServer wireMockServer = new WireMockServer(8089);

    @Given("I setup stuff")
    public void iSetupStuff() {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());

        stubFor(get(urlEqualTo("/boss"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/json")
                        .withBody("Darth Plagueis")));
    }

    @When("I do stuff")
    public void iDoStuff() {
    }

    @Then("I see stuff")
    public void iSeeStuff() {
        assertThat(this.restTemplate.getForObject("http://localhost:8080/greeting",
                String.class)).contains("Hello, World");
    }

    @Given("a new game is started")
    public void aNewGameIsStarted() {

    }

    @When("I create a character")
    public void iCreateACharacter() {
    }

    @Then("my character shows up in the game")
    public void myCharacterShowsUpInTheGame() {
    }
}
