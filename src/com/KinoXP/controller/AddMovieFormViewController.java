package com.KinoXP.controller;

        import com.KinoXP.model.AddMovieFormViewModel;
        import com.KinoXP.model.MovieWeek;
        import com.KinoXP.view.AddMovieFormView;
        import com.KinoXP.view.NewMovieView;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;
        import org.apache.commons.validator.routines.UrlValidator;
       import org.controlsfx.validation.ValidationSupport;
        import org.controlsfx.validation.Validator;

        import javax.imageio.ImageIO;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;
        import java.net.URL;

/**
 * Created by Paula on 26/02/2016.
 */
public class AddMovieFormViewController {

    public void addMovieFormViewDisplay(){
        AddMovieFormView addMovieFormView = new AddMovieFormView();
        addMovieFormView.getAddMovieView();
    }

    private AddMovieFormView addMovieFormView;
    private AddMovieFormViewModel addMovieFormViewModel;

    //CONSTRUCTORS
    public AddMovieFormViewController(AddMovieFormView addMovieFormView, AddMovieFormViewModel addMovieFormViewModel) {
        this.addMovieFormView = addMovieFormView;
        this.addMovieFormViewModel = addMovieFormViewModel;
    }

    public AddMovieFormViewController(){};



    //METHOD WHICH PASSES THE INPUT FROM THE VIEW TO THE MODEL
    public void addMovieToDB(String titleInput, String playingTimeInMinutesInput, String releaseYearInput,
                             String plotInput, String directorInput, String posterPathInput, String castInput,
                             String theatreNameInput, String genreInput, String ageLimitInput, java.sql.Date date) {
        try {
            addMovieFormViewModel.insertMovie(titleInput, playingTimeInMinutesInput, releaseYearInput, plotInput,
                    directorInput, posterPathInput, castInput, theatreNameInput, genreInput, ageLimitInput, date);

        } catch (Exception e) {
            System.out.println("Exception in addMovieToDB() from Controller: " + e.getMessage());
        }
    }

    //Fields Validation method - displays "recoration" - related to ControlFx - not fully functional yet
    public void validateFieldsControlsFx(TextField titleInput, TextField playingTimeInMinutesInput, TextField releaseYearInput,
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
        if(validationSupport.isInvalid()) {
            System.out.println("ERROR GREG!");
        }


    }

    public boolean isUrlValid(TextField urlField) {
        // because of tight deadline, I used apache commonsLibrary
        // to validate Url address by: UrlValidator object
        // source: http://commons.apache.org/proper/commons-validator/download_validator.cgi?Preferred=http%3A%2F%2Fmirrors.dotsrc.org%2Fapache%2F

        boolean result = false;

        String[] schemes = {"http","https"}; // DEFAULT schemes = "http", "https", "ftp"
        UrlValidator urlValidator = new UrlValidator();

        if (urlValidator.isValid(urlField.getText())) {
            // let it be <3
            System.out.println("valid");
            result = true;
        } else {

            System.out.println("url is fucked up");
            result = false;
        }
        return result;
    }

    public boolean ifFieldHasInteger(TextField duration, TextField releaseYearInput, TextField ageLimit) {
        boolean result = true;
        float dummy1;
        float dummy2;
        float dummy3;
        float floatResult;
        try {
            dummy1 = Float.parseFloat(duration.getText());
            dummy2 = Float.parseFloat(releaseYearInput.getText());
            dummy3 = Float.parseFloat(ageLimit.getText());
            floatResult = dummy1 + dummy2 + dummy3;


        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
    public boolean areFieldsEmpty(String titleInput, String playingTimeInMinutesInput, String releaseYearInput,
                                  String plotInput, String directorInput, String posterPathInput, String castInput,
                                  String theatreNameInput, String genreInput, String ageLimitInput) {
        boolean result = false;

        //if any field is empty
        if(titleInput.equals("") || playingTimeInMinutesInput.equals("") || releaseYearInput.equals("") ||
                plotInput.equals("") || directorInput.equals("") || posterPathInput.equals("") || castInput.equals("") ||
                theatreNameInput.equals("") || genreInput.equals("") || ageLimitInput.equals("")) {

            result = true ;
        }

        return result;
    }

    public void validateFieldsAndAction(TextField titleInput, TextField playingTimeInMinutesInput,
                                        TextField releaseYearInput,
                                        TextArea plotInput, TextField directorInput, TextField posterPathInput, TextArea castInput,
                                        String theatreNameInput, TextField genreInput, TextField ageLimitInput, java.sql.Date date){

        if (areFieldsEmpty(titleInput.getText(), playingTimeInMinutesInput.getText(), releaseYearInput.getText(),
                plotInput.getText(), directorInput.getText(), posterPathInput.getText(), castInput.getText(), theatreNameInput, genreInput.getText(), ageLimitInput.getText())) {

            addMovieFormView.updateAlertMessage("Some fields are missing");
        } else if (ifFieldHasInteger(playingTimeInMinutesInput, releaseYearInput, ageLimitInput) == false) {
            addMovieFormView.updateAlertMessage("Fields: Duration, Year, Age Limit accept only numbers");

        } else if (isUrlValid(posterPathInput) == false) {
            addMovieFormView.updateAlertMessage("Wrong Url Format");

        } else {
            /**
             *
             */
            ManageMovieScheduleController manageMovieScheduleController = new ManageMovieScheduleController();
            //save movie in Db with default schedule
            addMovieToDB(titleInput.getText(), playingTimeInMinutesInput.getText(),
                    releaseYearInput.getText(), plotInput.getText(), directorInput.getText(), posterPathInput.getText(),
                    castInput.getText(), theatreNameInput, genreInput.getText(), ageLimitInput.getText(),date);
            MovieWeek movieWeek = new MovieWeek();
            manageMovieScheduleController.insertMovie(movieWeek.save(movieWeek.getObservableList()));
            makeFileFromURL(addMovieFormView.getUrl(),addMovieFormView.getTitle());
            addMovieFormView.closeStage();
            NewMovieView newMovieView = new NewMovieView();
            newMovieView.start();
            /**
             *
             */
        }
    }

    //making file from url method
    public void makeFileFromURL(String urlString, String titleString){

        try {
            URL url = new URL(urlString);
            BufferedImage img = ImageIO.read(url);
            File file = new File("res/" + titleString+".png");
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}


