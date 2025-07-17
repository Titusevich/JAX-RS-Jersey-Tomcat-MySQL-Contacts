package org.example.app.dto;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.Contact;

public record ContactDtoCreateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Contact contact) {

    public static final String SUCCESS_MESSAGE = "Contact was created.";
    public static final String FAILURE_MESSAGE = "Contact wasn't created.";

    public static ContactDtoCreateResponse of(boolean isContactCreated, Contact contact) {
        if (isContactCreated)
            return new ContactDtoCreateResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE, contact);
        else
            return new ContactDtoCreateResponse(
                    Response.Status.NO_CONTENT.getStatusCode(),
                    Response.Status.NO_CONTENT.getReasonPhrase(),
                    false, FAILURE_MESSAGE, null);
    }
}
