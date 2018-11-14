/**
 * Copyright (C) 2015 Working Group on Joint Research, University Medical Center Mainz
 * Contact: info@osse-register.de
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 *
 * If you modify this Program, or any covered work, by linking or combining it
 * with Jersey (https://jersey.java.net) (or a modified version of that
 * library), containing parts covered by the terms of the General Public
 * License, version 2.0, the licensors of this Program grant you additional
 * permission to convey the resulting work.
 */
package util;

import java.io.FileNotFoundException;
import java.security.PublicKey;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import db.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import de.samply.auth.client.jwt.KeyLoader;
import de.samply.common.config.OAuth2Client;
import de.samply.common.config.ObjectFactory;
import de.samply.common.config.Postgresql;
import de.samply.config.util.JAXBUtil;
import de.samply.mdr.dao.MDRConnection;
import de.samply.mdr.dao.Vocabulary;
import de.samply.sdao.DAOException;
import de.samply.sdao.json.JSONResource;

/**
 * The current configuration for this rest interface. This includes the database
 * credentials and the OAuth2 credentials.
 *
 */
public class MDRConfig {

    private static final Logger logger = LogManager.getLogger(MDRConfig.class);

    /**
     * The OAuth2 configuration
     */
    private static OAuth2Client oauth2;

    private static PublicKey publicKey;

    private static Postgresql psql;

    private static int dbVersion = 0;

    private static String projectName = "samply";

    public static void initialize(String fallback) throws FileNotFoundException, JAXBException, SAXException,
            ParserConfigurationException, DAOException {
        psql = JAXBUtil.findUnmarshall("mdr.postgres.xml", getJAXBContext(), Postgresql.class, projectName, fallback);
        oauth2 = JAXBUtil.findUnmarshall("mdr.oauth2.xml", getJAXBContext(), OAuth2Client.class, projectName, fallback);
        publicKey = KeyLoader.loadKey(oauth2.getHostPublicKey());

        logger.debug("Establishing first connection to check the db version");
        MDRConnection mdr = ConnectionFactory.get(0);
        mdr.checkDbVersion();

        JSONResource config = mdr.getConfig();
        dbVersion = config.getProperty(Vocabulary.DB_VERSION).asInteger();
    }

    private static JAXBContext jaxbContext;

    private synchronized static JAXBContext getJAXBContext() throws JAXBException {
        if(jaxbContext == null) {
            jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        }
        return jaxbContext;
    }

    /**
     * @return the publicKey
     */
    public static PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * @return the psql
     */
    public static Postgresql getPsql() {
        return psql;
    }

    /**
     * @return the dbVersion
     */
    public static int getDbVersion() {
        return dbVersion;
    }

    /**
     * @return the projectName
     */
    public static String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public static void setProjectName(String projectName) {
        MDRConfig.projectName = projectName;
    }

}
