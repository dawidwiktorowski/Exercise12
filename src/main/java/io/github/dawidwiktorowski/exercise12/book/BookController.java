package io.github.dawidwiktorowski.exercise12.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books;

    public BookController() {
        books = new ArrayList<>();
        books.add(new Book(1L, "Spring", "Wallas", "123789456"));
        books.add(new Book(2L, "Java", "Horstman", "789456123"));
    }

    @GetMapping("")
    public List<Book> findAll(@RequestParam(required = false) String text) {
        if (text != null)
            return books.stream()
                    .filter(x -> x.getIsbn().toLowerCase().equals(text) || x.getTitle().equals(text))
                    .collect(Collectors.toList());
        else
            return books;
    }
}
