package util;

import db.ConnectionFactory;
import de.samply.auth.client.jwt.JWTAccessToken;
import de.samply.auth.rest.Scope;
import de.samply.mdr.dal.dto.User;
import de.samply.mdr.dao.MDRConnection;
import de.samply.mdr.dao.UserDAO;
import exceptions.InvalidAccessTokenException;
import graphql.GraphQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserFactory {

    private static final Logger logger = LogManager.getLogger(UserFactory.class);

    private User user = null;

    public User getUser(String token){
            try(MDRConnection mdr = ConnectionFactory.get(0)) {
                if(token == null) {
                    logger.debug("Using 'GET' parameter 'access_token'");
                    throw new GraphQLException("No Authentication Header");
                } else {
                    String[] inp = token.split(" ");

                    if(inp[0].equals("Bearer") && inp.length >= 2) {
                        token = token.replaceAll("^Bearer\\s+", "").replaceAll("\\s+", "");
                        logger.debug("Using Header 'Authorization'");
                    } else {
                        logger.debug("Access token has an invalid format");
                        throw new InvalidAccessTokenException();
                    }
                }

                if(token != null) {
                    JWTAccessToken accessToken = new JWTAccessToken(MDRConfig.getPublicKey(), token);

                    if(! accessToken.getScopes().contains(Scope.MDR.getIdentifier())) {
                        logger.debug("AccessToken does not contain MDR scope");
                        throw new InvalidAccessTokenException();
                    }

                    if(accessToken.isValid()) {
                        logger.debug("Access token is valid.");
                        String subject = accessToken.getClaimsSet().getSubject();
                        user = mdr.get(UserDAO.class).getUserByIdentity(subject);

                        if(user == null) {
                            logger.debug("Access token is valid, but the user is unknown");
                        }
                    } else {
                        logger.debug("Access token is invalid");
                        throw new InvalidAccessTokenException();
                    }
                } else {
                    logger.debug("No access token found, assuming anonymous access");
                }
            } catch (Exception e) {
                /**
                 * Ignore the invalid access token
                 */
                e.printStackTrace();
            }

            if(user == null) {
                user = new User();
            }
        return user;
        }
}
