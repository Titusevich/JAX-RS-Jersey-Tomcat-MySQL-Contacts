package org.example.app.service.contact;

import org.example.app.dto.ContactDtoRequest;
import org.example.app.entity.Contact;
import org.example.app.service.BaseService;

import java.util.List;

public interface ContactService extends BaseService<Contact, ContactDtoRequest> {

    Contact create(ContactDtoRequest request);
    List<Contact> getAll();

    // ---- Path Params ----------------------

    Contact getById(Long id);
    Contact update(Long id, ContactDtoRequest request);
    boolean deleteById(Long id);
}
