package com.KinoXPTest;

import com.KinoXP.model.AddMovieFormViewModel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paula on 26/02/16.
 */

public class AddMovieModelFormViewModulTest {
        AddMovieFormViewModel addMovieFormViewModel = new AddMovieFormViewModel();

    @Test
    public void testInsertMovie() throws Exception {
        addMovieFormViewModel.getActorFromDatabase("theActor");
        //I ( krystian) comment it out, because i added new value to constructor // date//
       // addMovieFormViewModel.insertMovie("newMovie", "90", "2016", "plot", "director", "posterPath", "theActor",
            //    "theatreName", "genre", "12");
        assertEquals("newMovie", addMovieFormViewModel.getActorFromDatabase("theActor"));
    }
}