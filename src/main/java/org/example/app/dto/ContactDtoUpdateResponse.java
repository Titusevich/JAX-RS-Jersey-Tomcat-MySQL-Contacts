package org.example.app.dto;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.Contact;

public record ContactDtoUpdateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Contact contact) {

    public static final String SUCCESS_MESSAGE = "Contact with id %s was successfully updated.";
    public static final String FAILURE_MESSAGE = "Contact with id %s wasn't found.";

    public static ContactDtoUpdateResponse of(Long id, boolean isContactFound, Contact contactUpdated) {
        if (isContactFound)
            return new ContactDtoUpdateResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), contactUpdated);
        else
            return new ContactDtoUpdateResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), contactUpdated);
    }
}
