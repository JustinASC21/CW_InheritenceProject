import java.util.UUID;

    public class UUIDGenerator {
        public static String generator() {
            UUID u = UUID.randomUUID();
            return u.toString();
        }
    }


