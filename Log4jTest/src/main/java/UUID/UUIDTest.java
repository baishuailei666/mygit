package UUID;

import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
        System.out.println(" * * * " + System.currentTimeMillis());
        for (int i=0;i<200;i++){
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            System.out.println(uuid);

        }
        System.out.println(" * * * " + System.currentTimeMillis());
    }
}
