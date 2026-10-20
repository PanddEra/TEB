public class NotAPESELException extends Exception{
    @Override
    public String toString() {
        return "To nie jest dobry PESEL";
    }
}
