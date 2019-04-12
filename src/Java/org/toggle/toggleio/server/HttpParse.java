package org.toggle.toggleio.server;

import org.json.HTTP;
import org.json.JSONObject;

/**
 * Parses a HTTP request
 */
public class HttpParse {

  /**
   * Parse a HTTP request and returns the endpoint from header
   * @exception IllegalArgumentException not a valid http request
   * @param request http request
   * @return endpoint
   */
  public static String parseUrlEndpoint(String request) throws IllegalArgumentException{
    if (!validHTTP(request)) {
      throw new IllegalArgumentException("Not a valid HTTP request");
    }

    String[] httpParts = request.split("\n\n");
    httpParts = httpParts[0].split("\n");
    httpParts = httpParts[0].split(" ");
    String endpoint = httpParts[1];

    return endpoint;
  }

  /**
   * Parse a HTTP request and returns the Content type from header if it exist
   * @param request Valid HTTP request
   * @return Content type
   */
  public static String parseContentType(String request) throws IllegalArgumentException{
    if (!validHTTP(request)) {
      throw new IllegalArgumentException("Not a valid HTTP request");
    }
    String[] httpParts = request.split("\n\n");
    JSONObject JSONrequest = HTTP.toJSONObject(httpParts[0]);

    String contentType = JSONrequest.getString("Content-Type");
    return contentType;
  }

  /**
   * Checks if a HTTP request have valid amount of fields, it does not check the content
   * of the fields
   * @param httpString to check
   * @return if it has valid amount of fields
   */
  private static boolean validHTTP(String httpString) {
    if (httpString.length() < 1) {
      return false;
    }
    String[] httpParts = httpString.split("\n\n");
    if (httpParts.length > 2 || httpParts.length < 1) {
      return false;
    }
    httpParts = httpParts[0].split("\n");
    httpParts = httpParts[0].split(" ");
    if (!(httpParts.length == 3)) {
      return false;
    }

    return true;
  }

}
