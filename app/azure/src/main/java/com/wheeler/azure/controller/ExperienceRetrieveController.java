package com.wheeler.azure.controller;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.wheeler.azure.exception.ExceptionHandler;
import com.wheeler.core.dao.model.Experience;
import com.wheeler.core.dto.model.ExperienceDto;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ExperienceRetrieveController extends AzureSpringBootRequestHandler<Optional<?>, List<Experience>> {

    /**
     * retrieves experience data
     *
     * @param request the http request
     * @param context the execution context
     * @return a http response message
     */
    @FunctionName("experienceRetrieve")
    public HttpResponseMessage retrieve(
            @HttpTrigger(
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    methods = {HttpMethod.GET},
                    name = "req",
                    route = "experience/retrieve")
                    HttpRequestMessage<Void> request,
            final ExecutionContext context) {

        try {
            List<Experience> data = handleRequest(Optional.empty(), context);
            context.getLogger().info(String.format("received %d experience records", data.size()));
            return request.createResponseBuilder(HttpStatus.valueOf(200)).body(new ExperienceDto(data)).build();
        }
        catch(Exception e){
            return new ExceptionHandler(context, e, request).asHttpResponse();
        }
    }
}
