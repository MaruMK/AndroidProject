package net.caesarlegion.drugimpact.Model;

/**
 * Created by ian on 2016-09-05.
 * Imported by Maxime, didn't have anything to change
 * so I left the original creator's tag
 */
public class DatabaseException extends Throwable {
    public DatabaseException(String s) {
        super(s);
    }

    public DatabaseException(Exception e) {
        super(e);
    }
}
