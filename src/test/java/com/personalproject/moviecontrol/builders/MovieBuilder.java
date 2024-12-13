package com.personalproject.moviecontrol.builders;

import com.personalproject.moviecontrol.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieBuilder {

    private UUID id = UUID.randomUUID();
    private String title = "Inception";
    private String genre = "Sci-Fi";
    private int releaseYear = 2010;

    public Movie create() {
        return Movie.builder()
                .id(id)
                .title(title)
                .genre(genre)
                .releaseYear(releaseYear)
                .build();
    }

    public List<Movie> createList(int listSize) {
        List<Movie> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(create());
        }
        return list;
    }

//    //Outra forama de criar listas
//    public List<Movie> createList(int size) {
//        return IntStream.rangeClosed(1, size)
//                .mapToObj(i -> create())
//                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
//    }

}
