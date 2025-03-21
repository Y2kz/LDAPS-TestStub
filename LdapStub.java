import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LdapBindTest {

    public static void main(String[] args) {
        // LDAP server URL with LDAPS (LDAP over SSL)
        String ldapUrl = "ldaps://<REPLACE_IP_OR_URL>:<636 OR REPLACE_PORT>";  // Replace with your LDAP server URL
        String username = "<REPLACE_USER>";  // Replace with the Bind DN or username
        String password = "<REPLACE_PASSWORD>";  // Replace with the password
        //String bindDN = "CN=Test.Ldap,OU=Accounts,OU=Users,DC=test,DC=in";  // Base DN (if needed)
        String bindDN = "<REPLACE_DN>";
        // Set up the environment for the LDAP connection
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapUrl);   // LDAPS server URL
        env.put(Context.SECURITY_AUTHENTICATION, "simple"); // Simple authentication
        env.put(Context.SECURITY_PRINCIPAL, bindDN); // DN to bind
        env.put(Context.SECURITY_CREDENTIALS, password); // Password for the bind DN
        env.put(Context.SECURITY_PROTOCOL, "ssl");

        try {
            // Create the initial context to perform the bind
            DirContext ctx = new InitialDirContext(env);

            System.out.println("Successfully bound to the LDAP server using LDAPS!");
            // Close the context after binding
            ctx.close();
        } catch (NamingException e) {
            System.err.println("Failed to bind to the LDAP server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
