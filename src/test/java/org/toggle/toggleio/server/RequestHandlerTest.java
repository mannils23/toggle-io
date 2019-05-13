/*package org.toggle.toggleio.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.toggle.toggleio.application.controller.ControllerStub;

class RequestHandlerTest {
    private RequestHandler requestHandler;

    @BeforeEach
    void setUp() {
        this.requestHandler = new RequestHandler(new ControllerStub());
    }


    @Test
    void handleRequestCorrectON() {
        final String input =
                "POST /on HTTP/1.1\n" + "Content-Type: application/json\n"
                        + "\r\n"
                        + "{\"token\":\"abcde12345\"}";

        final String expected = "HTTP/1.1 200 OK" + "\n\r\n";
        try {
            final String actual = requestHandler.handleRequest(input);
            assertEquals(expected, actual);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    void handleRequestCorrectOFF() {
        final String input =
                "POST /off HTTP/1.1\n"
                        + "Content-Type: application/json\n"
                        + "\r\n"
                        + "{\"token\":\"abcde12345\"}";
        final String expected = "HTTP/1.1 200 OK" + "\n\r\n";
        try {
            final String actual = requestHandler.handleRequest(input);
            assertEquals(expected, actual);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    void handleRequestCorrectSTATUS() {
        final String input =
                "POST /status HTTP/1.1\n" + "Content-Type: application/json\n"
                        + "\r\n"
                        + "{\"token\":\"abcde12345\"}";

        final String expected = "HTTP/1.1 200 OK\n" + "Content-Type: application/json\n" +
                "\r\n" +
                "{\"status_power\":\"ON\"}";
        try {
            final String actual = requestHandler.handleRequest(input);
            assertEquals(expected, actual);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    void handleRequestIncorrectEndpoint() {
        final String input =
                "POST /fish HTTP/1.1\n" + "\n";
        final String expected = HttpResponse.httpBadRequest();
        try {
            final String actual = requestHandler.handleRequest(input);
            assertEquals(expected, actual);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    void handleRequestIncorrectRequest() {
        final String input =
                "PST /status HTT/1.1\n" + "\r\n";
        final String expected = HttpResponse.httpBadRequest();
        try {
            final String actual = requestHandler.handleRequest(input);
            assertEquals(expected, actual);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

}*/