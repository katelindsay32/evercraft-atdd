package com.dojo.evercraft.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CharacterStepDefs {
    RestTemplate restTemplate = new RestTemplate();

    @Given("I setup stuff")
    public void iSetupStuff() {
        assertThat(this.restTemplate.getForObject("http://localhost:8080/greeting",
                String.class)).contains("Hello, World");
    }

    @When("I do stuff")
    public void iDoStuff() {
    }

    @Then("I see stuff")
    public void iSeeStuff() {
    }
}
