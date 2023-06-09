package stocksageservice.converters;

import org.junit.jupiter.api.Test;
import stocksageservice.dynamodb.models.Query;
import stocksageservice.models.QueryModel;
import stocksageservice.test.helper.QueryTestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {

    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toQueryModel_convertsQuery() {
        Query query = QueryTestHelper.generateQuery();

        QueryModel queryModel = modelConverter.toQueryModel(query);

        assertEquals(query.getUsername(), queryModel.getUsername());
        assertEquals(query.getQueryId(), queryModel.getQueryId());
        assertEquals(query.getDateRequested(), queryModel.getDateRequested());
        assertEquals(query.getStartDate(), queryModel.getStartDate());
        assertEquals(query.getEndDate(), queryModel.getEndDate());
        assertEquals(query.getFrequency(), queryModel.getFrequency());
        assertEquals(query.getSymbol(), queryModel.getSymbol());
        assertEquals(query.getSaved(), queryModel.getSaved());
    }
}
