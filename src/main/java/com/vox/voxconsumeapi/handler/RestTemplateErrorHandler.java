package com.vox.voxconsumeapi.handler;

import com.vox.voxconsumeapi.exception.NotFoundException;
import com.vox.voxconsumeapi.exception.ServiceUnAvailableException;
import com.vox.voxconsumeapi.exception.UnAuthorizedException;
import com.vox.voxconsumeapi.exception.UnProcessableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()) {
            if (response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                throw new ServiceUnAvailableException("Service is currently unavailable");
            }
        } else if (response.getStatusCode().is4xxClientError()) {

            String convert = response.getStatusCode() == HttpStatus.UNAUTHORIZED ? null :
                    StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());

            if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new UnAuthorizedException("Unauthorized access");
            }

            if(response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY){
                throw new UnProcessableException(convert);
            }

            if(response.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new NotFoundException(convert);
            }

        }

    }

}
