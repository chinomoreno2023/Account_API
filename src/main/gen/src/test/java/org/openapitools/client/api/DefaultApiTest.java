/*
 * Account API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.AuthRequestDto;
import org.openapitools.client.model.AuthResponseDto;
import org.openapitools.client.model.CreateUserRequest;
import java.time.LocalDate;
import org.openapitools.client.model.TransferRequestDto;
import org.openapitools.client.model.UpdateEmailRequest;
import org.openapitools.client.model.UpdatePhoneRequest;
import org.openapitools.client.model.UserDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for DefaultApi
 */
@Disabled
public class DefaultApiTest {

    private final DefaultApi api = new DefaultApi();

    /**
     * User login
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void authLoginPostTest() throws ApiException {
        AuthRequestDto authRequestDto = null;
        AuthResponseDto response = api.authLoginPost(authRequestDto);
        // TODO: test validations
    }

    /**
     * Transfer money
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void transferPostTest() throws ApiException {
        TransferRequestDto transferRequestDto = null;
        String response = api.transferPost(transferRequestDto);
        // TODO: test validations
    }

    /**
     * Update user email
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usersEmailIdPutTest() throws ApiException {
        Integer id = null;
        UpdateEmailRequest updateEmailRequest = null;
        UserDto response = api.usersEmailIdPut(id, updateEmailRequest);
        // TODO: test validations
    }

    /**
     * Update user phone
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usersPhoneIdPutTest() throws ApiException {
        Integer id = null;
        UpdatePhoneRequest updatePhoneRequest = null;
        UserDto response = api.usersPhoneIdPut(id, updatePhoneRequest);
        // TODO: test validations
    }

    /**
     * Create new user
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usersPostTest() throws ApiException {
        CreateUserRequest createUserRequest = null;
        UserDto response = api.usersPost(createUserRequest);
        // TODO: test validations
    }

    /**
     * Search users
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usersSearchGetTest() throws ApiException {
        String name = null;
        String email = null;
        String phone = null;
        LocalDate dateOfBirth = null;
        Integer page = null;
        Integer size = null;
        Object response = api.usersSearchGet(name, email, phone, dateOfBirth, page, size);
        // TODO: test validations
    }

}
