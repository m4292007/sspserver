package channel.adapter;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import com.atg.openssp.common.core.connector.JsonPostConnector;
import com.atg.openssp.common.core.entry.SessionAgent;
import com.atg.openssp.common.exception.BidProcessingException;


public class TrialAdapterConnector implements AdapterConnector {

	private static final long serialVersionUID = -6978598309872993590L;

	private final JsonPostConnector jsonPostConnector;

	private final SSPAdapter sspAdapter;

	public TrialAdapterConnector(final SSPAdapter sspAdapter) {
		this.sspAdapter = sspAdapter;
		jsonPostConnector = new JsonPostConnector();
	}

	@Override
	public String connect(final SessionAgent sessionAgent) throws BidProcessingException {
		/**
		 * TrialAdapterBuilder.java의 override 한 build()에서 null을 리턴하는데
		 * 아래 jsonRequest의 값이 있을까? 2020.06.15 
		 */
		final String jsonRequest = new TrialAdapterBuilder(sessionAgent).build();

		final HttpPost httpPost = new HttpPost(sspAdapter.getEndpoint());
		return jsonPostConnector.connect(new StringEntity(jsonRequest, ContentType.APPLICATION_JSON), httpPost);
	}

}
