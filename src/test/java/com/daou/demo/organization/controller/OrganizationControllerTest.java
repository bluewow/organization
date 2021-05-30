package com.daou.demo.organization.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class OrganizationControllerTest {

    private static final String address = "http://localhost:9000/api/organizations";

    @DisplayName("param 이 입력되지 않을시 최상위 기준 모두 검색이 되어야 한다")
    @Test
    public void getOrganizations() {

        Response res = given()
                .when()
                .get(address);

        res.then()
                .statusCode(200)
                .log().all();

    }
}