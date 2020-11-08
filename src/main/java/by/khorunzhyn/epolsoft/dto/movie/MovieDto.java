package by.khorunzhyn.epolsoft.dto.movie;

import by.khorunzhyn.epolsoft.dto.BaseDto;
import by.khorunzhyn.epolsoft.entity.MovieGenre;

public class MovieDto extends BaseDto {

    private String title;
    private MovieGenre movieGenre;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieGenre getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(MovieGenre movieGenre) {
        this.movieGenre = movieGenre;
    }
}
