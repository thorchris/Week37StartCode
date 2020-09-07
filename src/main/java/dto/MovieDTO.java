/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;

/**
 *
 * @author thorc
 */
public class MovieDTO {
    private Long id;
    private int year;
    private String title;
    private String actors;

    public MovieDTO(Movie emp) {
        this.id = emp.getId();
        this.year = emp.getYear();
        this.title = emp.getTitle();
        this.actors = emp.getActors();
    }
    
    
}
