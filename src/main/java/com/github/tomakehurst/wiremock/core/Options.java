/*
 * Copyright (C) 2011 Thomas Akehurst
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
package com.github.tomakehurst.wiremock.core;

import com.github.tomakehurst.wiremock.common.*;
import com.github.tomakehurst.wiremock.extension.Extension;
import com.github.tomakehurst.wiremock.http.CaseInsensitiveKey;
import com.github.tomakehurst.wiremock.http.HttpServerFactory;
import com.github.tomakehurst.wiremock.http.trafficlistener.WiremockNetworkTrafficListener;
import com.github.tomakehurst.wiremock.security.Authenticator;
import com.github.tomakehurst.wiremock.standalone.MappingsLoader;
import com.github.tomakehurst.wiremock.verification.notmatched.NotMatchedRenderer;
import com.google.common.base.Optional;

import java.util.List;
import java.util.Map;

public interface Options {

    int DEFAULT_PORT = 8080;
    int DYNAMIC_PORT = 0;
    int DEFAULT_CONTAINER_THREADS = 10;
    String DEFAULT_BIND_ADDRESS = "0.0.0.0";

    int portNumber();
    HttpsSettings httpsSettings();
    JettySettings jettySettings();
    int containerThreads();
    boolean browserProxyingEnabled();
    ProxySettings proxyVia();
    FileSource filesRoot();
    MappingsLoader mappingsLoader();
    MappingsSaver mappingsSaver();
    Notifier notifier();
    boolean requestJournalDisabled();
    Optional<Integer> maxRequestJournalEntries();
    String bindAddress();
    List<CaseInsensitiveKey> matchingHeaders();
    boolean shouldPreserveHostHeader();
    String proxyHostHeader();
    HttpServerFactory httpServerFactory();
    <T extends Extension> Map<String, T> extensionsOfType(Class<T> extensionType);
    WiremockNetworkTrafficListener networkTrafficListener();
    Authenticator getAdminAuthenticator();
    boolean getHttpsRequiredForAdminApi();
    NotMatchedRenderer getNotMatchedRenderer();
    AsynchronousResponseSettings getAsynchronousResponseSettings();
}
