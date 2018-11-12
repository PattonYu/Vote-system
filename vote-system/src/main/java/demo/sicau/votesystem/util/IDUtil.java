package demo.sicau.votesystem.util;
/**
 * @author PattonYu
 * @date 2018/11/3 20:24
 * Description:
 */
public class IDUtil {

    public static String getUUID(){
        String UUID = String.valueOf(java.util.UUID.randomUUID());
        return UUID.replace("-","");
    }
}