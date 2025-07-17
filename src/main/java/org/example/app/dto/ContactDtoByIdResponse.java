package org.example.app.dto;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.Contact;

public record ContactDtoByIdResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Contact contact) {

    public static final String SUCCESS_MESSAGE = "Contact with id %s was fetched successfully.";
    public static final String FAILURE_MESSAGE = "Contact with id %s wasn't found.";

    public static ContactDtoByIdResponse of(Long id, boolean isContactFound, Contact contact) {
        if (isContactFound)
            return new ContactDtoByIdResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), contact);
        else
            return new ContactDtoByIdResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}
