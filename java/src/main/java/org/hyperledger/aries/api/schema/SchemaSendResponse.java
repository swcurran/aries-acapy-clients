/**
 * Copyright (c) 2020 Robert Bosch GmbH. All Rights Reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package org.hyperledger.aries.api.schema;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SchemaSendResponse {
    private String schemaId;
    private Schema schema;

    @Data @NoArgsConstructor
    public static final class Schema {
        private String ver;
        private String id;
        private String name;
        private String version;
        @SerializedName("attrNames")
        private List<String> attrNames;
        @SerializedName("seqNo")
        private Integer seqNo;
    }
}
