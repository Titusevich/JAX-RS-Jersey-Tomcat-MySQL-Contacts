package org.example.app.dto;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.Contact;

import java.util.Collections;
import java.util.List;

public record ContactDtoListResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        List<Contact> contactList) {

    public static final String SUCCESS_MESSAGE = "Contact list was fetched successfully.";
    public static final String FAILURE_MESSAGE = "Contact list wasn't found.";

    public static ContactDtoListResponse of(boolean isContactListEmpty, List<Contact> contactList) {
        if (isContactListEmpty)
            return new ContactDtoListResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE, Collections.emptyList());
        else
            return new ContactDtoListResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE, contactList);
    }
}
