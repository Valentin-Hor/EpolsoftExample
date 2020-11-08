package by.khorunzhyn.epolsoft.dto.actor;

import by.khorunzhyn.epolsoft.data.Gender;
import by.khorunzhyn.epolsoft.dto.BaseDto;
import by.khorunzhyn.epolsoft.dto.address.AddressDto;
import by.khorunzhyn.epolsoft.dto.movie.MovieDto;

import java.time.LocalDate;
import java.util.List;

public class ActorDto extends BaseDto {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private AddressDto address;
    private List<MovieDto> movies;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<MovieDto> getMovieList() {
        return movies;
    }

    public void setMovieList(List<MovieDto> movieList) {
        this.movies = movieList;
    }
}
