import BdFI.*;
import BdFI.database.exceptions.*;
import BdFI.person.exceptions.*;
import BdFI.show.exceptions.*;
import dataStructures.Iterator;
import outputMessages.Error;
import outputMessages.Success;

import java.io.*;
import java.util.Scanner;

/**
 * Main class
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public class Main {

    //Name of the file
    private static final String FILE_NAME = "db.dat";

    /**
     * Array containing possible genders
     */
    private static final String[] genders = {"FEMALE", "MALE", "OTHER", "NOT-PROVIDED"};

    /**
     * Main method where Scanner and Database are initied
     *
     * @param args contains the supplied command-line arguments as an array of String
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
                    commandInfoShow(in, db);
                    break;
                case RATESHOW:
                    commandRate(in, db);
                    break;
                case INFOPERSON:
                    commandInfoPerson(in, db);
                    break;
                case LISTSHOWSPERSON:
                    commandListShowsPerson(in, db);
                    break;
                case LISTPARTICIPATIONS:
                    commandListShowParticipation(in, db);
                    break;
                case LISTBESTSHOWS:
                    commandListBestShows(in, db);
                    break;
                case LISTTAGGEDSHOWS:
                    commandListTaggedShows(in, db);
                    break;
                case LISTSHOWS:
                    commandListShowsByRating(in, db);
                    break;
                default:
                    break;
            }
            command = getCommand(in);
            System.out.println();
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
        int year = -1;
        try {
            String id = in.next();
            year = in.nextInt();
            String email = in.next();
            String telephone = in.next();
            String gender = in.next();
            String name = in.nextLine().trim();
            validateGender(gender);
            db.addPerson(id, year, email, telephone, gender, name);
            System.out.println(Success.PERSON_ADDED);
        } catch (InvalidYearException e) {
            System.out.println(Error.INVALID_PERSON_YEAR);
        } catch (InvalidGenderException e) {
            if (year <= 0) System.out.println(Error.INVALID_PERSON_YEAR);
            else System.out.println(Error.INVALID_GENDER);
        } catch (PersonIdAlreadyExistsException e) {
            System.out.println(Error.PERSON_EXISTS);
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
        } catch (InvalidShowYearException e) {
            System.out.println(Error.INVALID_SHOW_YEAR);
        } catch (ShowIDExistsException e) {
            System.out.println(Error.SHOW_EXISTS);
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
            String showID = in.next().toUpperCase();
            String description = in.nextLine().trim();
            db.addParticipation(personID, showID, description);
            System.out.println(Success.PARTICIPATION_ADDED);
        } catch (PersonIdNotFoundException e) {
            System.out.println(Error.PERSON_DOESNT_EXIST);
        } catch (ShowIdNotFoundException e) {
            System.out.println(Error.SHOW_DOESNT_EXIST);
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
            String showID = in.nextLine().trim();
            db.premiereShow(showID);
            System.out.println(Success.SHOW_PREMIERED);
        } catch (ShowNotInProductionException e) {
            System.out.println(Error.SHOW_FINISHED_PRODUCTION);
        } catch (ShowIdNotFoundException e) {
            System.out.println(Error.SHOW_DOESNT_EXIST);
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
            String showID = in.nextLine().trim();
            db.removeShow(showID);
            System.out.println(Success.SHOW_REMOVED);
        } catch (ShowNotInProductionException e) {
            System.out.println(Error.SHOW_FINISHED_PRODUCTION);
        } catch (ShowIdNotFoundException e) {
            System.out.println(Error.SHOW_DOESNT_EXIST);
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
            System.out.println(Error.SHOW_DOESNT_EXIST);
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
            String showID = in.nextLine().trim();
            Show s = db.getShow(showID);
            System.out.printf(Success.INFO_SHOW_HEADERS, s.getShowID(), s.getTitle(), s.getYear(), s.getRating());
            Iterator<String> it = s.iteratorTags();
            while (it.hasNext())
                System.out.printf(Success.INFO_SHOW_TAG, it.next());
        } catch (ShowIdNotFoundException e) {
            System.out.println(Error.SHOW_DOESNT_EXIST);
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
            in.nextLine();
            db.reviewShow(showID, stars);
            System.out.println(Success.SHOW_RATED);
        } catch (InvalidShowRatingException e) {
            System.out.println(Error.INVALID_RATING);
        } catch (ShowInProductionException e) {
            System.out.println(Error.SHOW_IN_PRODUCTION);
        } catch (ShowIdNotFoundException e) {
            System.out.println(Error.SHOW_DOESNT_EXIST);
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
            String personID = in.nextLine().trim();
            Person p = db.getPerson(personID);
            System.out.printf(Success.PERSON_INFO, p.getPersonID(), p.getName(), p.getYear(), p.getEmail(),
                    p.getTelephone(), p.getGender());
        } catch (PersonIdNotFoundException e) {
            System.out.println(Error.PERSON_DOESNT_EXIST);
        }
    }

    /**
     * Command 10
     * Lists the shows where the person with given participates
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandListShowsPerson(Scanner in, Database db) {
        try {
            String personID = in.nextLine().trim();
            Iterator<Show> itShows = db.showsByPersonID(personID);
            while (itShows.hasNext()) {
                Show next = itShows.next();
                System.out.printf(Success.PERSON_SHOW, next.getShowID(), next.getTitle(),
                        next.getYear(), next.getRating());
            }

        } catch (PersonIdNotFoundException e) {
            System.out.println(Error.PERSON_DOESNT_EXIST);
        } catch (PersonHasNoShowsException e) {
            System.out.println(Error.PERSON_HAS_NO_SHOWS);
        }
    }

    /**
     * Command 11
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandListShowParticipation(Scanner in, Database db) {
        try {
            String showID = in.nextLine().trim();
            Iterator<Participation> it = db.iteratorParticipationByShow(showID);
            while (it.hasNext()) {
                Participation part = it.next();
                Person p = part.getPerson();
                System.out.printf(Success.SHOW_PARTICIPATION, p.getPersonID(), p.getName(), p.getYear(), p.getEmail(), p.getTelephone(), p.getGender(), part.getDescription());
            }
        } catch (ShowIdNotFoundException e) {
            System.out.println(Error.SHOW_DOESNT_EXIST);
        } catch (ShowHasNoParticipationsException e) {
            System.out.println(Error.SHOW_NO_PARTICIPATIONS);
        }
    }

    /**
     * Command 12
     * Lists the show(s) with the highest ratings
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandListBestShows(Scanner in, Database db) {
        try {
            in.nextLine();
            Iterator<Show> it = db.listBestShows();
            while (it.hasNext()) {
                Show s = it.next();
                System.out.printf(Success.SHOW_LIST, s.getShowID(), s.getTitle(), s.getYear(),
                        s.getRating());
            }

        } catch (NoShowsException e) {
            System.out.println(Error.NO_SHOWS);
        } catch (NoFinishedShowsException e) {
            System.out.println(Error.NO_FINISHED_PRODUCTIONS);
        } catch (NoRatedShowsException e) {
            System.out.println(Error.NO_RATED_PRODUCTIONS);
        }
    }

    /**
     * Command 13
     * Lists the shows with a given rating
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandListShowsByRating(Scanner in, Database db) {
        try {
            int rating = in.nextInt();
            in.nextLine();
            Iterator<Show> itShows = db.listShows(rating);
            while (itShows.hasNext()) {
                Show next = itShows.next();
                System.out.printf(Success.SHOW_LIST, next.getShowID(), next.getTitle(),
                        next.getYear(), next.getRating());
            }

        } catch (InvalidShowRatingException e) {
            System.out.println(Error.INVALID_RATING);
        } catch (NoShowsException e) {
            System.out.println(Error.NO_SHOWS);
        } catch (NoFinishedShowsException e) {
            System.out.println(Error.NO_FINISHED_PRODUCTIONS);
        } catch (NoRatedShowsException e) {
            System.out.println(Error.NO_RATED_PRODUCTIONS);
        } catch (NoProductionsWithRatingException e) {
            System.out.println(Error.NO_PRODUCTION_WITH_RATING);
        }
    }

    /**
     * Command 14
     * Lists the shows with a given tag
     *
     * @param in input where the data will be read from
     * @param db Database where this action will be performed
     */
    private static void commandListTaggedShows(Scanner in, Database db) {
        try {
            String tag = in.nextLine().trim();
            Iterator<Show> itShows = db.listTaggedShows(tag);
            while (itShows.hasNext()) {
                Show next = itShows.next();
                System.out.printf(Success.SHOW_LIST, next.getShowID(), next.getTitle(),
                        next.getYear(), next.getRating());
            }

        } catch (NoShowsException e) {
            System.out.println(Error.NO_SHOWS);
        } catch (NoTaggedProductionsException e) {
            System.out.println(Error.NO_TAGGED_PRODUCTIONS);
        } catch (NoShowsWithTagException e) {
            System.out.println(Error.NO_SHOWS_WITH_TAG);
        }
    }

    /**
     * Command 15
     * Outputs in the console a goodbye message
     *
     * @param db database which is been saved
     */
    private static void commandQuit(Database db) {
        System.out.println(Success.CLOSE_PROGRAM);
        System.out.println();
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

    /**
     * Checks if gender is valid
     *
     * @param gender gender to be checked
     * @throws InvalidGenderException if gender is not valid
     */
    private static void validateGender(String gender) throws InvalidGenderException {
        for (String s : genders)
            if (s.compareToIgnoreCase(gender) == 0)
                return;
        throw new InvalidGenderException();
    }
}
