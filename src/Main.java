import database.Database;
import database.DatabaseClass;
import outputMessages.Success;
import person.exceptions.*;
import person.Person;
import person.exceptions.InvalidGenderException;
import person.exceptions.InvalidYearException;
import person.exceptions.PersonIdAlreadyExistsException;
import person.exceptions.PersonIdNotFoundException;
import show.Show;
import show.exceptions.*;

import java.io.*;
import java.util.Scanner;

public class Main {

    //Name of the file
    private static final String FILE_NAME = "db.dat";

    /**
     * Main method where Scanner and Database are initialized
     *
     * @param args contains the supplied command-line arguments as an array of String objects
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Database db = load();

        commands(in, db);
        in.close();
    }

    /**
     * Reads a command from console and executes the method associated with that command
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commands(Scanner in, Database db) {
        Commands command = getCommand(in);
        while (!command.equals(Commands.QUIT)) {
            switch (command) {
                case ADDPERSON:
                    commandAddPerson(in, db);
                    break;
                case ADDSHOW:
                    commandAddShow(in, db);
                    break;
                case ADDPARTICIPATION:
                    commandAddParticipation(in, db);
                    break;
                case PREMIERE:
                    commandPremiere(in, db);
                    break;
                case REMOVESHOW:
                    commandRemove(in, db);
                    break;
                case TAGSHOW:
                    commandTagShow(in, db);
                    break;
                case INFOSHOW:
                    break;
                case RATESHOW:
                    commandRate(in, db);
                    break;
                case INFOPERSON:
                    break;
                case LISTSHOWSPERSON:
                    break;
                case LISTPARTICIPATIONS:
                    break;
                case LISTBESTSHOWS:
                    break;
                case LISTSHOWS:
                    break;
                case LISTTAGGEDSHOWS:
                    break;
            }
            command = getCommand(in);
        }
        commandQuit(db);
    }


    /**
     * Command 1
     * Adds a person to the database
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandAddPerson(Scanner in, Database db) {
        try {
            String id = in.next();
            int year = in.nextInt();
            String email = in.next();
            String telephone = in.next();
            String gender = in.next();
            String name = in.nextLine().trim();
            db.addPerson(id, year, email, telephone, gender, name);
            System.out.println(Success.PERSON_ADDED);
        } catch (InvalidYearException | InvalidGenderException | PersonIdAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Command 2
     * Adds a show to the database
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandAddShow(Scanner in, Database db) {
        try {
            String idShow = in.next();
            int year = in.nextInt();
            String title = in.nextLine().trim();
            db.addShow(idShow, year, title);
            System.out.println(Success.SHOW_ADDED);
        } catch (InvalidShowYearException | ShowIDExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Command 3
     * Adds a participation
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandAddParticipation(Scanner in, Database db) {
        try {
            String personID = in.next();
            String showID = in.next();
            String description = in.nextLine().trim();
            db.addParticipation(personID, showID, description);
            System.out.println(Success.PARTICIPATION_ADDED);
        } catch (PersonIdNotFoundException | ShowIdNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Command 4
     * Premieres a show
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandPremiere(Scanner in, Database db) {
        try {
            String showID = in.nextLine();
            db.premiereShow(showID);
            System.out.println(Success.SHOW_PREMIERED);
        } catch (ShowNotInProductionException | ShowIdNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Command 5
     * Premieres a show
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandRemove(Scanner in, Database db) {
        try {
            String showID = in.nextLine();
            db.removeShow(showID);
            System.out.println(Success.SHOW_REMOVED);
        } catch (ShowNotInProductionException | ShowIdNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Command 6
     * Tag a show
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandTagShow(Scanner in, Database db) {
        try {
            String showID = in.next();
            String tag = in.nextLine().trim();
            db.tagShow(showID, tag);
            System.out.println(Success.TAG_ADDED);
        } catch (ShowIdNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Command 7
     * Shows information about a show
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandInfoShow(Scanner in, Database db) {
        try {
            String showID = in.next();
            Show s = db.getShow(showID);
            System.out.printf(Success.INFO_SHOW_HEADERS, s.getShowID(), s.getTitle(), s.getYear(), s.getRating());
            Iterator<String> it = s.iteratorTags();
            while (it.hasNext())
                System.out.printf(Success.INFO_SHOW_TAG, it.next());
        } catch (ShowIdNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Command 8
     * Reviews a show
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandRate(Scanner in, Database db) {
        try {
            String showID = in.next();
            int stars = in.nextInt();
            db.reviewShow(showID, stars);
        } catch (InvalidShowRatingException | ShowInProductionException | ShowIdNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Command 9
     * Checks information about a person
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandInfoPerson(Scanner in, Database db) {
        try {
            String personID = in.nextLine();
            Person p = db.getPerson(personID);
            System.out.printf(Success.PERSON_INFO, p.getPersonID(), p.getName(),p.getYear() ,p.getEmail() ,
                    p.getTelephone(), p.getGender());
        } catch (PersonIdNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Command 11
     *
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandListParticipations(Scanner in, Database db) {

    }

    /**
     * Command 15
     * Outputs in  |the console a goodbye message
     *
     * @param db database which is been saved
     */
    private static void commandQuit(Database db) {
        System.out.println("Serializing and quitting...");
        save(db);
    }

    /**
     * Loads database from a file
     *
     * @return the loaded database
     */
    private static Database load() {
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(FILE_NAME));
            Database db = (Database) file.readObject();
            file.close();
            return db;
        } catch (FileNotFoundException e) {
            return new DatabaseClass();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Saves the database in a file
     *
     * @param db database which is been saved
     */
    private static void save(Database db) {
        try {
            ObjectOutputStream file = new ObjectOutputStream(
                    new FileOutputStream(FILE_NAME));
            file.writeObject(db);
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns Command which corresponds to the command given by the user
     *
     * @param in input where the data will be read from
     * @return Command which corresponds to the command given by the user
     */
    private static Commands getCommand(Scanner in) {
        try {
            String comm = in.next().toUpperCase();
            return Commands.valueOf(comm);
        } catch (IllegalArgumentException e) {
            return Commands.UNKNOWN;
        }
    }
}
