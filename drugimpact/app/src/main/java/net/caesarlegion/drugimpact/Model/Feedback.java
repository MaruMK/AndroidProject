package net.caesarlegion.drugimpact.Model;

/**
 * Created by Gabriel Charlebois on 2017-12-15.
 * Purpose: Store the user's feedback and format it in a way the server can understand.
 */

public class Feedback {

    private String userId;
    private String text;


    //Unused classes for parsing feedback into an object
    /*private static class Links {
        private Self self;
    }
    private static class Self {
        private String href;
    }
    private Links _links;
*/

    public Feedback(String userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    public String toJSON(){
        return "{\"userId\":\"" + this.userId + "\", \"text\":\"" + this.text + "\"}";
    }

}
