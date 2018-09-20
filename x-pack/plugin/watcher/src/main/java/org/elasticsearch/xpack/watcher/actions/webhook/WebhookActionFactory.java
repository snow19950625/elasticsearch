/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.watcher.actions.webhook;

import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.xpack.core.watcher.actions.ActionFactory;
import org.elasticsearch.xpack.watcher.common.http.HttpClient;
import org.elasticsearch.xpack.watcher.common.text.TextTemplateEngine;

import java.io.IOException;

public class WebhookActionFactory extends ActionFactory {

    private final HttpClient httpClient;
    private final TextTemplateEngine templateEngine;

    public WebhookActionFactory(Settings settings, HttpClient httpClient, TextTemplateEngine templateEngine) {

        super(Loggers.getLogger(ExecutableWebhookAction.class, settings));
        this.httpClient = httpClient;
        this.templateEngine = templateEngine;
    }

    @Override
    public ExecutableWebhookAction parseExecutable(String watchId, String actionId, XContentParser parser) throws IOException {
        return new ExecutableWebhookAction(WebhookAction.parse(watchId, actionId, parser),
                actionLogger, httpClient, templateEngine);

    }
}