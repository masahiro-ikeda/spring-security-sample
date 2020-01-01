package com.sample.web.security.session;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.sample.common.model.Facility;

public class SessionUserDeserializer extends JsonDeserializer<SessionUser> {

	@Override
	public SessionUser deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		JsonNode jsonNode = mapper.readTree(jp);

		List<Facility> facilities = mapper.convertValue(
				jsonNode.get("facilities"),
				new TypeReference<List<Facility>>() {
				});

		Facility facility = mapper.convertValue(
				jsonNode.get("facility"),
				new TypeReference<Facility>() {
				});

		SessionUser result = new SessionUser();
		result.setUserId(readJsonNode(jsonNode, "userId").asText());
		result.setUserName(readJsonNode(jsonNode, "userName").asText());
		result.setAuthority(readJsonNode(jsonNode, "authority").asText());
		result.setFacility(facility);
		result.setFacilities(facilities);

		return result;
	}

	private JsonNode readJsonNode(JsonNode jsonNode, String field) {
		return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
	}
}
