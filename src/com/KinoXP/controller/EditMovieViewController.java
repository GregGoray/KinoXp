package com.KinoXP.controller;

import com.KinoXP.model.EditMovieViewModel;
import com.KinoXP.view.EditMovieView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Created by Girts Zemitis on 25/02/2016.
 * https://github.com/GirtsZemitis
 */
public class EditMovieViewController {

    private EditMovieViewModel manageMovieViewModul;
    private EditMovieView editMovieView;
    private AddMovieFormViewController addMovieFormViewController;

    //CONSTRUCTORS
    public EditMovieViewController(EditMovieView editMovieView, EditMovieViewModel manageMovieViewModul) {
        this.editMovieView = editMovieView;
        this.manageMovieViewModul = manageMovieViewModul;
    }
    public EditMovieViewController(){
    }



    public void editMovieButtonAction(TextField titleTxtText, TextField playingTimeInMinutesTxtText, TextField releaseYearTxtText,
                                        String plotTxtText, TextField directorTxtText, TextField posterPathTxtText, String mainActorTxtText,
                                        String movieTheaterTxt, TextField genreTxtText, TextField ageLimitTxtText, String titleInput,
                                        String playingTimeInMinutesInput, String releaseYearInput, String plotInput,
                                        String directorInput, String posterPathInput, String mainActorInput, String movieTheaterInput,
                                        String genreInput, String ageLimitInput){

        // Call other controller - to reduce code redundancy
        addMovieFormViewController = new AddMovieFormViewController();

        if (addMovieFormViewController.areFieldsEmpty(titleTxtText.getText(), playingTimeInMinutesTxtText.getText(),  releaseYearTxtText.getText(),
                 plotTxtText,  directorTxtText.getText(),  posterPathTxtText.getText(),  mainActorTxtText,
                 movieTheaterTxt, genreTxtText.getText(), ageLimitTxtText.getText()) == true) {
            editMovieView.updateAlertMessage("Some fields are missing");
        }
        else if (addMovieFormViewController.ifFieldHasInteger(playingTimeInMinutesTxtText, releaseYearTxtText, ageLimitTxtText) == false) {
            editMovieView.updateAlertMessage("Fields: Duration, Year, Age Limit accept only numbers");

        }
        else if (addMovieFormViewController.isUrlValid(posterPathTxtText) == false) {
            editMovieView.updateAlertMessage("Wrong Url Format");

        }

        else {

            if(!playingTimeInMinutesTxtText.equals(playingTimeInMinutesInput)){
                manageMovieViewModul.editPlayingInMinutes(titleInput, Integer.parseInt(playingTimeInMinutesTxtText.getText()));
            }
            if(!releaseYearTxtText.equals(releaseYearInput)){
                manageMovieViewModul.editReleaseYear(titleInput, Integer.parseInt(releaseYearTxtText.getText()));
            }
            if(!plotTxtText.equals(plotInput)){
                manageMovieViewModul.editPlot(titleInput, plotTxtText);
            }
            if(!directorTxtText.equals(directorInput)){
                manageMovieViewModul.editDirector(titleInput, directorTxtText.getText());
            }
            if(!posterPathTxtText.equals(posterPathInput)){
                manageMovieViewModul.editPoster(titleInput, posterPathTxtText.getText());
            }
            if(!mainActorTxtText.equals(mainActorInput)){
                manageMovieViewModul.editMainActor(titleInput, mainActorTxtText);
            }
            if(!movieTheaterTxt.equals(movieTheaterInput)){
                manageMovieViewModul.editMovieTheater(titleInput, movieTheaterTxt);
            }
            if (!genreTxtText.equals(genreInput)) {
                manageMovieViewModul.editGenre(titleInput, genreTxtText.getText());
            }
            if (!ageLimitTxtText.equals(ageLimitInput)) {
                manageMovieViewModul.editAgeLimit(titleInput, Integer.parseInt(ageLimitTxtText.getText()));
            }
            if (!titleTxtText.equals(titleInput)) {
                manageMovieViewModul.editTitle(titleInput, titleTxtText.getText());
            }

            NewMovieViewController newMovieViewController = new NewMovieViewController();
            newMovieViewController.newMovieViewDisplay();
            editMovieView.closeStage();

        }
    }


    //Fields Validation method - displays "recoration" - related to ControlFx - not fully functional yet
    public void validateEditFieldsControlsFx(TextField titleInput, TextField playingTimeInMinutesInput, TextField releaseYearInput,
                                         TextArea plotInput, TextField directorInput, TextField posterPathInput, TextArea castInput,
                                         ComboBox theatreNameInput, TextField genreInput, TextField ageLimitInput) {
        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(titleInput, Validator.createEmptyValidator("Empty Title Field"));
        validationSupport.registerValidator(playingTimeInMinutesInput, Validator.createEmptyValidator("Empty Duration Field"));
        validationSupport.registerValidator(releaseYearInput, Validator.createEmptyValidator("Empty Release Year Field"));
        validationSupport.registerValidator(plotInput, Validator.createEmptyValidator("Empty Plot Field"));
        validationSupport.registerValidator(directorInput, Validator.createEmptyValidator("Empty Director Field"));
        validationSupport.registerValidator(posterPathInput, Validator.createEmptyValidator("Empty Url Field"));
        validationSupport.registerValidator(castInput, Validator.createEmptyValidator("Empty  Cast Field"));
        validationSupport.registerValidator(theatreNameInput, Validator.createEmptyValidator("Empty Theatres Name Field"));
        validationSupport.registerValidator(genreInput, Validator.createEmptyValidator("Empty Genre Field"));
        validationSupport.registerValidator(ageLimitInput, Validator.createEmptyValidator("Empty AgeLimit Field"));
    }



    public void deleteMovieButtonAction(String titleTxtText) {
        manageMovieViewModul.deleteMovie(titleTxtText);
    }

    public void deleteFileAction(){
        manageMovieViewModul.deletefile();
    }



}
