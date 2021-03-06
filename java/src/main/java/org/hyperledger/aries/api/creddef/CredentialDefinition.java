/**
 * Copyright (c) 2020 Robert Bosch GmbH. All Rights Reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package org.hyperledger.aries.api.creddef;

import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Schemas are the same for all clients once they are agreed upon.
 */
@Data @NoArgsConstructor @AllArgsConstructor
public final class CredentialDefinition {

    private String ver;

    private String id;

    @SerializedName("schemaId")
    private String schemaId;

    private String type;

    private String tag;

    private JsonObject value;

    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static final class CredentialDefinitionRequest {
        private String tag;
        Boolean supportRevocation;
        private String schemaId;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static final class CredentialDefinitionResponse {
        @SerializedName(value = "cred_def_id", alternate = "credential_definition_id")
        private String credentialDefinitionId;
    }

    @Data @NoArgsConstructor
    public static final class CredentialDefinitionsCreated {
        private List<String> credentialDefinitionIds;
    }
}
