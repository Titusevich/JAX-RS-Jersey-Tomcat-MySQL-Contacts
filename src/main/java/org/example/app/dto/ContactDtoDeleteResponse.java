package org.example.app.dto;

import jakarta.ws.rs.core.Response;

public record ContactDtoDeleteResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message) {

    public static final String SUCCESS_MESSAGE = "Contact with id %s was successfully deleted.";
    public static final String FAILURE_MESSAGE = "Contact with id %s wasn't found.";

    public static ContactDtoDeleteResponse of(Long id, boolean isContactFound) {
        if (isContactFound)
            return new ContactDtoDeleteResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.GONE.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id));
        else
            return new ContactDtoDeleteResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id));
    }
}
