package com.company.messages.repositories;

import com.company.messages.model.Message;
import com.company.messages.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MessageRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Message m) {
        em.persist(m);
    }

    public List<Message> findAll() {
        return em.createQuery("SELECT m FROM Message m", Message.class).getResultList();
    }
}
