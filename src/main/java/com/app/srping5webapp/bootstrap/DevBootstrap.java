package com.app.srping5webapp.bootstrap;

import com.app.srping5webapp.model.Author;
import com.app.srping5webapp.model.Book;
import com.app.srping5webapp.model.Publisher;
import com.app.srping5webapp.repository.AuthorRepository;
import com.app.srping5webapp.repository.BookRepository;
import com.app.srping5webapp.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;

    private BookRepository bookRepository;

    private PublisherRepository publisherRepository;

    @Autowired
    public DevBootstrap(
            final AuthorRepository authorRepository,
            final BookRepository bookRepository,
            final PublisherRepository publisherRepository)
    {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher publisher = new Publisher("Harper Collins", "LA");
        Book ddd = new Book("Domain Driven Design", "1234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(publisher);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Jonson");
        Publisher publisher2 = new Publisher("Worx", "NY");
        Book noEjb = new Book("JavaEE Development without EJB", "223344", publisher2);
        rod.getBooks().add(noEjb);

        authorRepository.save(rod);
        publisherRepository.save(publisher2);
        bookRepository.save(noEjb);
    }
}
