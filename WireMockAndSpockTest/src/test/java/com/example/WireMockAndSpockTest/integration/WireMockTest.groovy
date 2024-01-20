package com.example.WireMockAndSpockTest.integration

import com.example.WireMock.model.Item
import com.example.WireMock.model.VendItemRequest
import com.example.WireMock.service.RestTemplateService
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.HttpClientErrorException

import static com.github.tomakehurst.wiremock.client.WireMock.*
import spock.lang.Specification
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.HttpStatus
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

    def setup() {
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

    def "testInitialize"() {
        given:
        wireMockRule.stubFor(post(urlEqualTo("/rest/initialize"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"status\": \"success\"}")))

        when:
        List<Item> itemList = [new Item(1, "A", "Soda", 1, 1.0)]
        ResponseEntity<String> responseEntity = restTemplateService.initializeItems(itemList)

        // Assert that the response body matches the expected value
        then:
        assertEquals("{\"status\": \"success\"}", responseEntity.getBody())
    }

    def "testAddChange"() {
        given:
        wireMockRule.stubFor(post(urlEqualTo("/rest/add-change"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"status\": \"success\"}")))

        // Perform add-change operation using RestTemplateService
        when:
        Map<Double, Integer> denominations = [1.0: 1, 2.0: 1]
        ResponseEntity<String> responseEntity = restTemplateService.addChange(denominations)

        then:
        assertEquals("{\"status\": \"success\"}", responseEntity.getBody())
    }

    def "testVendItem"() {
        given:
        wireMockRule.stubFor(put(urlEqualTo("/rest/vend-items"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"status\": \"success\"}")))

        when:
        List<VendItemRequest> vendItemRequests = [new VendItemRequest(1, 1.0, 1)]
        ResponseEntity<String> responseEntity = restTemplateService.vendItems(vendItemRequests)

        then:
        assertEquals("{\"status\": \"success\"}", responseEntity.getBody())
    }

    def "testInsufficientBalance"() {
        given:
        def vendItemRequests = [new VendItemRequest(1, 0.5, 1)]

        and:
        wireMockRule.stubFor(put(urlEqualTo("/rest/vend-items"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"error\": \"Insufficient balance\"}")))

        when:
        ResponseEntity<String> responseEntity

        then:
        // Use a try-catch block to handle the exception
        try {
            responseEntity = restTemplateService.vendItems(vendItemRequests)
        } catch (HttpClientErrorException.BadRequest e) {
            e.responseBodyAsString == "{\"error\": \"Insufficient balance\"}"
        }

    }
    def "testItemNotFound"() {
        given:
        def vendItemRequests = [new VendItemRequest(10, 1.0, 1)]

        and:
        wireMockRule.stubFor(put(urlEqualTo("/rest/vend-items"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"error\": \"Item not found\"}")))

        when:
        ResponseEntity<String> responseEntity

        then:
        try {
            responseEntity = restTemplateService.vendItems(vendItemRequests)
        } catch (HttpClientErrorException.NotFound e) {
            e.responseBodyAsString == "{\"error\": \"Item not found\"}"
        }

    }
}










