package org.example.app.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.dto.*;
import org.example.app.entity.Contact;
import org.example.app.service.contact.ContactService;

import java.util.Collections;
import java.util.List;

@Path("/contacts")
@Consumes(MediaType.APPLICATION_JSON)
public class ContactController {

    @Inject
    private ContactService service;

//    Creating new contact
    @POST
    @Consumes
    public Response create(final ContactDtoRequest request) {
        Contact contact = service.create(request);
        if (contact != null)
            return Response.ok()
                    .entity(ContactDtoCreateResponse.of(true,
                            contact))
                    .build();
        else return Response.ok()
                .entity(ContactDtoCreateResponse.of(false,
                        null))
                .build();
    }

//    Obtaining the entire contact list
    @GET
    public Response getAll() {
        List<Contact> list = service.getAll();
        if (list.isEmpty())
            return Response.ok()
                    .entity(ContactDtoListResponse.of(true,
                            Collections.emptyList()))
                    .build();
        else
            return Response.ok()
                    .entity(ContactDtoListResponse.of(false, list))
                    .build();
    }

//    ---- Path Params ----------------------

//    Obtaining contact by id
    @GET
    @Path("{id: [1-9][0-9]*}")
    public Response getById(@PathParam("id") final Long id) {
        Contact contact = service.getById(id);
        if (contact != null) {
            return Response.ok()
                    .entity(ContactDtoByIdResponse.of(id, true,
                            contact))
                    .build();
        } else {
            return Response.ok()
                    .entity(ContactDtoByIdResponse.of(id, false,
                            null))
                    .build();
        }
    }

//    Updating contact info by id
    @PUT
    @Path("{id: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") final Long id, final ContactDtoRequest request) {
        Contact contactToUpdate = service.getById(id);
        if (contactToUpdate != null) {
            Contact contactUpdated = service.update(id, request);
            return Response.ok()
                    .entity(ContactDtoUpdateResponse.of(id, true,
                            contactUpdated))
                    .build();
        } else {
            return Response.ok()
                    .entity(ContactDtoUpdateResponse.of(id, false,
                            null))
                    .build();
        }
    }

    @DELETE
    @Path("{id: [0-9]+}")
    public Response deleteById(@PathParam("id") final Long id) {
        if (service.deleteById(id)) {
            return Response.ok()
                    .entity(ContactDtoDeleteResponse.of(id, true))
                    .build();
        } else {
            return Response.ok()
                    .entity(ContactDtoDeleteResponse.of(id, false))
                    .build();
        }
    }
}
