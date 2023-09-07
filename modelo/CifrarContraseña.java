package modelo;

public class CifrarContraseña {

    public static String getHash(String txt, String hashType) {

        try {

            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(txt.getBytes());

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xff) | 0x100).substring(1,3));
            }
                return sb.toString();

        }catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static String md5 (String txt) { // MD5
        return CifrarContraseña.getHash(txt, "MD5"); //hash
    }   
    public static String sha1(String txt){ // SHA1
        return CifrarContraseña.getHash(txt, "SHA1"); //hash
    }
    
}