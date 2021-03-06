/**
 * Copyright (c) 2020 Robert Bosch GmbH. All Rights Reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package org.hyperledger.aries.api.connection;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.hyperledger.aries.MockedTestBase;
import org.junit.jupiter.api.Test;

import okhttp3.mockwebserver.MockResponse;

class MockedConnectionTest extends MockedTestBase {

    @Test
    void testGetConnections() throws Exception {
        String json = loader.load("files/connections.json");
        server.enqueue(new MockResponse().setBody(json));

        final Optional<List<ConnectionRecord>> c = ac.connections();
        assertTrue(c.isPresent());
        assertEquals(13, c.get().size());
    }

    @Test
    void testGetConnectionsFiltered() throws Exception {
        String json = loader.load("files/connectionsActive.json");
        server.enqueue(new MockResponse().setBody(json));

        final Optional<List<ConnectionRecord>> c = ac.connections(
                ConnectionFilter.builder().state(ConnectionState.active).build());
        assertTrue(c.isPresent());
        assertEquals(9, c.get().size());
    }

    @Test
    void testGetConnectionIds() throws Exception {
        String json = loader.load("files/connections.json");
        server.enqueue(new MockResponse().setBody(json));

        List<String> c = ac.connectionIds();
        assertEquals(13, c.size());
    }

    @Test
    void testGetActiveConnectionIds() throws Exception {
        String json = loader.load("files/connectionsActive.json");
        server.enqueue(new MockResponse().setBody(json));

        List<String> c = ac.connectionIds(ConnectionFilter.builder().state(ConnectionState.active).build());
        assertEquals(9, c.size());
    }

    @Test
    void testCreateInvitation() throws Exception {
        String json = loader.load("files/invitation.json");
        server.enqueue(new MockResponse().setBody(json));

        final Optional<CreateInvitationResponse> inv = ac.connectionsCreateInvitation();
        assertTrue(inv.isPresent());
        assertTrue(inv.get().getConnectionId().startsWith("d16dc0bf"));
    }

    @Test
    void testReceiveInvitation() throws Exception {
        String json = loader.load("files/connection.json");
        server.enqueue(new MockResponse().setBody(json));

        final Optional<ConnectionRecord> con = ac.connectionsReceiveInvitation(new ReceiveInvitationRequest(), null);
        assertTrue(con.isPresent());
        assertTrue(con.get().getConnectionId().startsWith("ce43c882"));
    }

}
