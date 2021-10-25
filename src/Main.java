import database.Database;
import database.DatabaseClass;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Database db = new DatabaseClass();

        commands(in, db);
        in.close();

    }

    private static void commands(Scanner in, Database db) {
        Commands command = getCommand(in);
        while (!command.equals(Commands.QUIT)) {
            switch (command) {
                case ADDPERSON:
                    break;
                case ADDSHOW:
                    break;
                case ADDPARTICIPATION:
                    break;
                case PREMIERE:
                    break;
                case REMOVESHOW:
                    break;
                case TAGSHOW:
                    break;
                case INFOSHOW:
                    break;
                case RATESHOW:
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
    }
    //save

    private static Commands getCommand(Scanner in) {
        try {
            String comm = in.next().toUpperCase();
            return Commands.valueOf(comm);
        } catch (IllegalArgumentException e) {
            return Commands.UNKNOWN;
        }
    }
}
