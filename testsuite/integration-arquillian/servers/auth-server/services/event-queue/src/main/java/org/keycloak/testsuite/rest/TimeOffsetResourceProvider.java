/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.testsuite.rest;

import org.keycloak.common.util.Time;
import org.keycloak.services.resource.RealmResourceProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public class TimeOffsetResourceProvider implements RealmResourceProvider {

    @Override
    public Object getResource() {
        return this;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> get() {
        Map<String, String> response = new HashMap<>();
        response.put("currentTime", String.valueOf(Time.currentTime()));
        response.put("offset", String.valueOf(Time.getOffset()));
        return response;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> put(Map<String, String> time) {
        int offset = Integer.parseInt(time.get("offset"));
        Time.setOffset(offset);
        return get();
    }

    @Override
    public void close() {
    }

}
