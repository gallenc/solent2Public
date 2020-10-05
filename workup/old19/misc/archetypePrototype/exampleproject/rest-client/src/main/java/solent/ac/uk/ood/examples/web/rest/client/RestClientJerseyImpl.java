/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.web.rest.client;

import solent.ac.uk.ood.examples.model.Thing;
import solent.ac.uk.ood.examples.model.ReplyMessage;
import solent.ac.uk.ood.examples.model.RestInterface;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import java.util.logging.Level;

/**
 * This class implements the RestInterface as a ReST client which interacts with
 * ExampleProjectRestImpl
 *
 * @author cgallen
 */
public class RestClientJerseyImpl implements RestInterface {

	private WebTarget target;
	private MediaType mediaType = null;

	/**
	 * @param baseUrl   the url which will be targeted for the rest api e.g.
	 *                  "http://localhost:8084"
	 * @param mediaType the messages expected either MediaType.APPLICATION_JSON_TYPE
	 *                  or MediaType.APPLICATION_XML_TYPE
	 */
	public RestClientJerseyImpl(String baseUrl, MediaType mediaType) {

		Client client = ClientBuilder.newClient(createClientConfig());
                
                // e.g http://localhost:8084/exampleproject-web/rest/example/retrieve?id=32
		target = client.target(baseUrl).path("exampleproject-web/rest/example");
		this.mediaType = mediaType;
	}

	// used to provide logging for client messages
	protected static ClientConfig createClientConfig() {
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
				Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
		return config;
	}

	@Override
	public ReplyMessage retrieveMatchingEntites(Thing entityTempate) {

		Response response = null;
		ReplyMessage replyMessage = null;
		try {

			Invocation.Builder builder = target.path("/retrievematching").request(mediaType);
			response = builder.post(javax.ws.rs.client.Entity.entity(entityTempate, mediaType));

			// read reply message
			replyMessage = response.readEntity(ReplyMessage.class);
			// get error message if available
			if (response.getStatus() != 200) {
				String errorMessage = (replyMessage == null) ? "no remote error message"
						: replyMessage.getDebugMessage();
				throw new RuntimeException(
						"response status:" + response.getStatus() + " remote error message: " + errorMessage);
			}

			// responded with an OK message now check actually have a value
			if (replyMessage == null) {
				throw new RuntimeException("response status:" + response.getStatus() + " but no restMessage body ");
			}

			return replyMessage;
		} catch (Exception e) {
			throw new RuntimeException("cannot run rest client to /retrievematching: Exception:", e);
		}

	}

	@Override
	public ReplyMessage retrieveThing(Integer id) {
		Response response = null;
		ReplyMessage replyMessage = null;
		try {
			Invocation.Builder builder = target.path("retrieve").queryParam("id", id).request(mediaType);
			response = builder.get();

			replyMessage = response.readEntity(ReplyMessage.class);

			// get error message if available
			if (response.getStatus() != 200) {
				String errorMessage = (replyMessage == null) ? "no remote error message"
						: replyMessage.getDebugMessage();
				throw new RuntimeException(
						"response status:" + response.getStatus() + " remote error message: " + errorMessage);
			}

			// responded with an OK message now check actually have a value
			if (replyMessage == null) {
				throw new RuntimeException("response status:" + response.getStatus() + " but no restMessage body ");
			}

			return replyMessage;

		} catch (Exception e) {
			throw new RuntimeException("cannot run rest client to retrieveThing: Exception:", e);
		}

	}
}
