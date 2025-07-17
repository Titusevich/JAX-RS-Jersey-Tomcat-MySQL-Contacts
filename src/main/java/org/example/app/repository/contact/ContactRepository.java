package org.example.app.repository.contact;

import org.example.app.dto.ContactDtoRequest;
import org.example.app.entity.Contact;
import org.example.app.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends BaseRepository<Contact, ContactDtoRequest> {

    @Override
    void save(ContactDtoRequest request);

    @Override
    Optional<List<Contact>> getAll();

    @Override
    Optional<Contact> getById(Long id);

    @Override
    void update(Long id, ContactDtoRequest request);

    @Override
    boolean deleteById(Long id);

    boolean isIdExists(Long id);

//     ---- Utils ----------------------

//     Отримання останнього запису

    Optional<Contact> getLastEntity();
}