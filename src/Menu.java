/**
 *
 */
public class Menu {

    private int option;

    public void setMenu() {
        switch (option) {
            case 1:
                System.out.println("------------- Menu -------------\n" +
                        "* 1 - LogIn                      *\n" +
                        "* 2 - SignIn                     *\n" +
                        "* 0 - Exit                       *\n" +
                        "----------------------------------\n");
                break;

            case 2:
                System.out.println("------------- Menu -------------\n" +
                        "* 1 - Check Reserved Slots        *\n" +
                        "* 2 - Reserve a Slot              *\n" +
                        "* 0 - Exit                        *\n" +
                        "-----------------------------------\n");
        }
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}
