import java.io.IOException;

/**
 *
 */
public class Menu {

    public static int option;


    public static int getOption() {
        return option;
    }

    public static void setOption(int option) {
        Menu.option = option;
    }

    public static void Manager() throws IOException, ClassNotFoundException {
        if(option == 0) startMenu();
        if(option == 1) loginMenu();
    }

    public static void startMenu() {
        System.out.println("-------------- Menu --------------\n" +
                "* 1 - LogIn                      *\n" +
                "* 2 - SignIn                     *\n" +
                "* 0 - Exit                       *\n" +
                "----------------------------------\n");
    }

    public static void loginMenu() {
        System.out.println("-------------- Menu --------------\n" +
                "* 1 - Check Reserved Slots        *\n" +
                "* 2 - Check Current Debt          *\n" +
                "* 3 - Reserve a Slot              *\n" +
                "* 4 - Release a Slot              *\n" +
                "* 0 - Exit                        *\n" +
                "-----------------------------------\n");
    }

/*
OLD MENU

    public void setMenu() {
        switch (option) {
            case 1:
                System.out.println("-------------- Menu --------------\n" +
                        "* 1 - LogIn                      *\n" +
                        "* 2 - SignIn                     *\n" +
                        "* 0 - Exit                       *\n" +
                        "----------------------------------\n");
                break;

            case 2:
                System.out.println("-------------- Menu --------------\n" +
                        "* 1 - Check Reserved Slots        *\n" +
                        "* 2 - Check Current Debt          *\n" +
                        "* 3 - Reserve a Slot              *\n" +
                        "* 4 - Release a Slot              *\n" +
                        "* 0 - Exit                        *\n" +
                        "-----------------------------------\n");
                break;

        }
    }
     */
}
