package com.lojabiblioteca.model;

import com.lojabiblioteca.model.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Author> authors;

    @Column(nullable = false)
    @NotBlank(message = "Campo língua é obrigatório")
    private String language;

    @Column(nullable = false)
    @NotNull(message = "Campo publicado é obrigatório")
    private LocalDate publisher;

    @Column(nullable = false)
    @Min(1)
    private int pages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
