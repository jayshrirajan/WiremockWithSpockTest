package com.example.WireMockAndSpockTest.integration




import com.example.WireMock.service.RestTemplateService
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.springframework.boot.test.context.SpringBootTest

import static com.github.tomakehurst.wiremock.client.WireMock.*
import spock.lang.Specification
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import static org.junit.Assert.assertEquals
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;


@SpringBootTest
class WireMockTest extends Specification {

    @Rule
    WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.wireMockConfig().dynamicPort())

//   @Rule
//   WireMockRule wireMockRule = new WireMockRule(8081)

    private RestTemplateService restTemplateService

    private RestTemplate restTemplate

    def setup()  {
        wireMockRule.start()
        this.restTemplate = new RestTemplate()
        int wireMockPort = wireMockRule.port()
        // Construct the base URL dynamically
        String baseUrl = "http://localhost:" + wireMockPort + "/rest"
        this.restTemplateService = new RestTemplateService(restTemplate, baseUrl)
        //this.restTemplateService = new RestTemplateService(restTemplate, "http://localhost:8081/rest/")

    }
    def teardown() {
        wireMockRule.stop()
    }

    def "testGetTotalItems"() {
        // Set up WireMock stub
        given:
        this.wireMockRule.stubFor(get(urlEqualTo("/rest/totalItem"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("4")))

        // Send HTTP request to WireMock server via YourService
        when:
        ResponseEntity<String> responseEntity = restTemplateService.getTotalItems()

        // Assert that the response body matches the expected value
        then:
        assertEquals("4", responseEntity.getBody())
    }

    def "testGetItem"() {

        given:
        this.wireMockRule.stubFor(get(urlEqualTo("/rest/getItem"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"item\": \"chips\"}")))

        when:
        ResponseEntity<String> responseEntity = restTemplateService.getItem()

        then:
        assertEquals("{\"item\": \"chips\"}", responseEntity.getBody())
    }




}




