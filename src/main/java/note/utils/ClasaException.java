package note.utils;

public class ClasaException extends Exception {
    private String message="";
    public ClasaException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
