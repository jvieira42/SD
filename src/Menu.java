/**
 *
 */
public class Menu {

    public int option;


    public int getOption() {
        return option;
    }

    public  void setOption(int option) {
        this.option = option;
    }

    public void show() {
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
                        "* 4 - Reserve a Slot by auction   *\n" +
                        "* 5 - Release a Slot              *\n" +
                        "* 0 - Logout                      *\n" +
                        "-----------------------------------\n");
                break;
        }
    }

}
