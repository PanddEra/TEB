import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean peselIsVerified = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your PESEL: ");
        String pesel = scanner.nextLine();

        if (pesel == null || pesel.length() != 11) {
            pesel = "55030101193";
        }

        String[] split = pesel.split("(?!^)");
        String gender = "unknown";

        try{
        gender = checkGender(split) == 'M' ? "Men" : "Women";
        }
        catch(NotAPESELException e){
            peselIsVerified = false;
        }

        System.out.println("Gender is : " + gender);
        try{
            peselIsVerified = peselIsVerified ? verifyPESEL(split) : false;
        }
        catch (NotAPESELException e){
            peselIsVerified = false;
        }

        if (peselIsVerified) {
            System.out.println("PESEL has been verified!");
        } else {
            System.out.println("PESEL verification has failed! Check your PESEL and try again.");
        }
    }

    private static boolean verifyPESEL(String[] split) throws NotAPESELException{
        int s = 0;
        for (int i = 0; i < split.length - 1; i++) {
            int currentlyChecked = Integer.parseInt(split[i]);
            switch (i) {
                case 0, 4, 8:
                    s += currentlyChecked;
                    break;
                case 1, 5, 9:
                    s += currentlyChecked * 3;
                    break;
                case 2, 6:
                    s += currentlyChecked * 7;
                    break;
                case 3, 7:
                    s += currentlyChecked * 9;
                    break;
                default:
                    s = 0;
            }
        }
        int m = s % 10;
        int r;
        if (m == 0) {
            r = 0;
        } else {
            r = 10 - m;
        }

        try{
            return r == Integer.parseInt(split[split.length - 1]);
        }
        catch(Exception e) {
            throw new NotAPESELException();
        }

    }

    private static char checkGender(String[] split) throws NotAPESELException {
        try{
            if (Integer.parseInt(split[9]) % 2 == 0) {
                return 'K';
            } else {
                return 'M';
            }
        }
        catch (Exception e){
            throw new NotAPESELException();
        }

    }
}