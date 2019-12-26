package com.sample.security;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.List;

public class LoginUserModelDeserializer extends JsonDeserializer<LoginUserModel> {

    @Override
    public LoginUserModel deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        List<SimpleGrantedAuthority> authorities =
                mapper.convertValue(
                        jsonNode.get("authorities"),
                        new TypeReference<List<SimpleGrantedAuthority>>() {
                        }
                );

        LoginUserModel result = new LoginUserModel(
                readJsonNode(jsonNode, "username").asText(),
                readJsonNode(jsonNode, "password").asText(),
                authorities,
                readJsonNode(jsonNode, "facilityId").asText()
        );
        return result;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
