package org.example.app.service.contact;

import jakarta.inject.Inject;
import org.example.app.dto.ContactDtoRequest;
import org.example.app.entity.Contact;
import org.example.app.repository.contact.ContactRepository;

import java.util.Collections;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    @Inject
    private ContactRepository repository;

    @Override
    public Contact create(ContactDtoRequest request) {
        repository.save(request);
        return repository.getLastEntity().orElse(null);
    }

    @Override
    public List<Contact> getAll() {
        return repository.getAll().orElse(Collections.emptyList());
    }

    // ---- Path Param ----------------------

    @Override
    public Contact getById(Long id) {
        return repository.getById(id).orElse(null);
    }

    @Override
    public Contact update(Long id, ContactDtoRequest request) {
        if (repository.getById(id).isPresent()) {
            repository.update(id, request);
        }
        return repository.getById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.isIdExists(id)) {
            repository.deleteById(id);
            return true;
        } else return false;
    }
}
