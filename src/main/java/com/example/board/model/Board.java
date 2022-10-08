package com.example.board.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;//SummerNode Lib <html>Tag

    @Column(nullable = false, length = 20)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY) // Many = Board, One = User
    @JoinColumn(name = "userId")
    private User user;//DB는 Object를 저장할 수 있다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id desc")
    private List<Reply> replys;

    @CreationTimestamp
    private Timestamp createDate;
}
