package net.caesarlegion.drugimpact.Model;

/**
 * Created by Connor on 2017-12-15.
 */

public class User {
    //private int user_id;
    private String email;
    private String pass;
    private String displayName;
    private String dob;
    private double weight;
    private String encryptionKey;
    private Links _links;

    public User(Integer user_id,String email,String pass,String displayName,String dob,double weight) {
       //this.user_id = user_id;
       this.email = email;
       this.pass = pass;
       this.displayName = displayName;
       this.dob = dob;
       this.weight = weight;
       this.encryptionKey = "null";
       this._links = new Links();
    }

    private static class Links
    {
        private User.Self self;
        private User.user user;
    }
    private static class Self
    {
        private String href;
    }
    private static class user
    {
        private String href;
    }
}
