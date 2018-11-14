import com.coxautodev.graphql.tools.SchemaParser;
import db.DataElementRepository;
import db.NamespaceRepository;
import db.SlotRepository;
import db.ValueDomainRepository;
import de.samply.config.util.FileFinderUtil;
import de.samply.mdr.dal.dto.User;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;
import org.apache.logging.log4j.core.config.Configurator;
import resolver.DataElementsResolver;
import resolver.NamespaceResolver;
import resolver.Query;
import util.AuthContext;
import util.MDRConfig;
import util.UserFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private String fallback = "";

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        NamespaceRepository namespaceRepository = new NamespaceRepository();
        DataElementRepository dataElementRepository = new DataElementRepository();
        SlotRepository slotRepository = new SlotRepository();
        ValueDomainRepository valueDomainRepository=new ValueDomainRepository();
        return SchemaParser.newParser().file("schema.graphqls").resolvers(new Query(namespaceRepository, dataElementRepository, slotRepository,valueDomainRepository),new NamespaceResolver(dataElementRepository),new DataElementsResolver(namespaceRepository,valueDomainRepository,slotRepository)).build().makeExecutableSchema();
    }

    @Override
    protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        UserFactory fUser = new UserFactory();

        fallback = request.get().getServletContext().getRealPath("/WEB-INF");
        config();

        String token = request.get().getHeader("Authorization");
        User user;
        if (token == null) {
            user = new User();
        } else {
            user = fUser.getUser(token);
        }
        return new AuthContext(user, request, response);
    }

    private void config() {
        String projectName = "samply";
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            MDRConfig.setProjectName(projectName);
             MDRConfig.initialize(fallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
