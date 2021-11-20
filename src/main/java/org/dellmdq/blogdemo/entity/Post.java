package org.dellmdq.blogdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    private String title;
    private String message;
    private String image;
    private String creationDate = LocalDateTime.now().toString();
    private String deleteAt;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Nullable
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}


