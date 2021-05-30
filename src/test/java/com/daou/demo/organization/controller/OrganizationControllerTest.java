package com.daou.demo.organization.controller;

import com.daou.demo.organization.controller.dto.ResponseDto;
import com.daou.demo.organization.util.BusinessException;
import com.daou.demo.organization.util.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrganizationControllerTest {

    private static final String address = "http://localhost:9000/api/organizations";

    @DisplayName("param 이 입력되지 않을시 최상위 기준 모든 부서가 검색이 되어야 한다")
    @Test
    public void getOrganizations() throws IOException {
        Response response = given()
                .when()
                .get(address);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto source = objectMapper.readValue(getFile("jsonDummy/getOrganizations.json"), ResponseDto.class);
        ResponseDto target = objectMapper.readValue(response.asString(), ResponseDto.class);
        assertThat(target).isEqualTo(source);
    }

    @DisplayName("deptOnly 가 false 인 경우 부서원도 함께 출력된다")
    @Test
    public void getOrganizations1() throws IOException {
        Response response = given()
                .param("deptOnly", false)
                .when()
                .get(address);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto source = objectMapper.readValue(getFile("jsonDummy/getOrganizations1.json"), ResponseDto.class);
        ResponseDto target = objectMapper.readValue(response.asString(), ResponseDto.class);
        assertThat(target).isEqualTo(source);
    }

    @DisplayName("deptCode 부서를 포함하여 하위부서를 응답한다 - 1")
    @Test
    public void getOrganizations2() throws IOException {
        Response response = given()
                .param("deptOnly", true)
                .param("deptCode", "B1")
                .when()
                .get(address);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto source = objectMapper.readValue(getFile("jsonDummy/getOrganizations2.json"), ResponseDto.class);
        ResponseDto target = objectMapper.readValue(response.asString(), ResponseDto.class);
        assertThat(target).isEqualTo(source);
    }

    @DisplayName("deptCode 부서를 포함하여 하위부서를 응답한다 - 2")
    @Test
    public void getOrganizations3() throws IOException {
        Response response = given()
                .param("deptOnly", true)
                .param("deptCode", "A")
                .when()
                .get(address);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto source = objectMapper.readValue(getFile("jsonDummy/getOrganizations3.json"), ResponseDto.class);
        ResponseDto target = objectMapper.readValue(response.asString(), ResponseDto.class);
        assertThat(target).isEqualTo(source);
    }

    @DisplayName("deptCode 부서를 포함하여 하위부서를 응답한다 - 2")
    @Test
    public void getOrganizationsExceptionTest() {
        Response response = given()
                .param("deptOnly", true)
                .param("deptCode", "NotValid")
                .when()
                .get(address);

        response.then()
                .statusCode(400)
                .body("code", equalTo(ErrorCode.ERROR_CODE_001.getCode()))
                .body("message", equalTo(ErrorCode.ERROR_CODE_001.getMessage()));
    }


    private File getFile(String path) throws IOException {
        return new ClassPathResource(path).getFile();
    }
}