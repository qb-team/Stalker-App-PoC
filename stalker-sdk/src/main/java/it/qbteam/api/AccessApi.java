/**
 * Stalker API
 * API di Stalker di Imola Informatica sviluppato da qbteam
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: qbteamswe@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package it.qbteam.api;

import it.qbteam.ApiInvoker;
import it.qbteam.ApiException;
import it.qbteam.Pair;

import it.qbteam.model.*;

import java.util.*;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import it.qbteam.model.OrganizationAuthenticatedAccess;
import it.qbteam.model.PlaceAuthenticatedAccess;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class AccessApi {
  String basePath = "http://localhost:8080";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public void addHeader(String key, String value) {
    getInvoker().addDefaultHeader(key, value);
  }

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  /**
  * Returns all the authenticated accesses in an organization registered.
  * Returns all the authenticated accesses in an organization registered.
   * @param organizationId ID of an organization
   * @return List<OrganizationAuthenticatedAccess>
  */
  public List<OrganizationAuthenticatedAccess> getAccessListInOrganization (Long organizationId) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'organizationId' is set
    if (organizationId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'organizationId' when calling getAccessListInOrganization",
        new ApiException(400, "Missing the required parameter 'organizationId' when calling getAccessListInOrganization"));
    }

    // create path and map variables
    String path = "/access/organization/{organizationId}/authenticated".replaceAll("\\{" + "organizationId" + "\\}", apiInvoker.escapeString(organizationId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] {  };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (List<OrganizationAuthenticatedAccess>) ApiInvoker.deserialize(localVarResponse, "array", OrganizationAuthenticatedAccess.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Returns all the authenticated accesses in an organization registered.
   * Returns all the authenticated accesses in an organization registered.
   * @param organizationId ID of an organization
  */
  public void getAccessListInOrganization (Long organizationId, final Response.Listener<List<OrganizationAuthenticatedAccess>> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'organizationId' is set
    if (organizationId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'organizationId' when calling getAccessListInOrganization",
        new ApiException(400, "Missing the required parameter 'organizationId' when calling getAccessListInOrganization"));
    }

    // create path and map variables
    String path = "/access/organization/{organizationId}/authenticated".replaceAll("\\{format\\}","json").replaceAll("\\{" + "organizationId" + "\\}", apiInvoker.escapeString(organizationId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((List<OrganizationAuthenticatedAccess>) ApiInvoker.deserialize(localVarResponse,  "array", OrganizationAuthenticatedAccess.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Returns all the authenticated accesses in an organization registered of one or more users (userIds are separated by commas).
  * Returns all the authenticated accesses in an organization registered of one or more users (userIds are separated by commas).
   * @param userIds One or more userIds
   * @param organizationId ID of an organization
   * @return List<OrganizationAuthenticatedAccess>
  */
  public List<OrganizationAuthenticatedAccess> getAccessListInOrganizationOfUsers (List<Long> userIds, Long organizationId) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'userIds' is set
    if (userIds == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'userIds' when calling getAccessListInOrganizationOfUsers",
        new ApiException(400, "Missing the required parameter 'userIds' when calling getAccessListInOrganizationOfUsers"));
    }
    // verify the required parameter 'organizationId' is set
    if (organizationId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'organizationId' when calling getAccessListInOrganizationOfUsers",
        new ApiException(400, "Missing the required parameter 'organizationId' when calling getAccessListInOrganizationOfUsers"));
    }

    // create path and map variables
    String path = "/access/organization/{organizationId}/authenticated/{userIds}".replaceAll("\\{" + "userIds" + "\\}", apiInvoker.escapeString(userIds.toString())).replaceAll("\\{" + "organizationId" + "\\}", apiInvoker.escapeString(organizationId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] {  };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (List<OrganizationAuthenticatedAccess>) ApiInvoker.deserialize(localVarResponse, "array", OrganizationAuthenticatedAccess.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Returns all the authenticated accesses in an organization registered of one or more users (userIds are separated by commas).
   * Returns all the authenticated accesses in an organization registered of one or more users (userIds are separated by commas).
   * @param userIds One or more userIds   * @param organizationId ID of an organization
  */
  public void getAccessListInOrganizationOfUsers (List<Long> userIds, Long organizationId, final Response.Listener<List<OrganizationAuthenticatedAccess>> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'userIds' is set
    if (userIds == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'userIds' when calling getAccessListInOrganizationOfUsers",
        new ApiException(400, "Missing the required parameter 'userIds' when calling getAccessListInOrganizationOfUsers"));
    }
    // verify the required parameter 'organizationId' is set
    if (organizationId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'organizationId' when calling getAccessListInOrganizationOfUsers",
        new ApiException(400, "Missing the required parameter 'organizationId' when calling getAccessListInOrganizationOfUsers"));
    }

    // create path and map variables
    String path = "/access/organization/{organizationId}/authenticated/{userIds}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "userIds" + "\\}", apiInvoker.escapeString(userIds.toString())).replaceAll("\\{" + "organizationId" + "\\}", apiInvoker.escapeString(organizationId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((List<OrganizationAuthenticatedAccess>) ApiInvoker.deserialize(localVarResponse,  "array", OrganizationAuthenticatedAccess.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Returns all the authenticated accesses in a place registered.
  * Returns all the authenticated accesses in a place registered.
   * @param placeId ID of a place
   * @return List<PlaceAuthenticatedAccess>
  */
  public List<PlaceAuthenticatedAccess> getAccessListInPlace (Long placeId) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'placeId' is set
    if (placeId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'placeId' when calling getAccessListInPlace",
        new ApiException(400, "Missing the required parameter 'placeId' when calling getAccessListInPlace"));
    }

    // create path and map variables
    String path = "/access/place/{placeId}/authenticated".replaceAll("\\{" + "placeId" + "\\}", apiInvoker.escapeString(placeId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] {  };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (List<PlaceAuthenticatedAccess>) ApiInvoker.deserialize(localVarResponse, "array", PlaceAuthenticatedAccess.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Returns all the authenticated accesses in a place registered.
   * Returns all the authenticated accesses in a place registered.
   * @param placeId ID of a place
  */
  public void getAccessListInPlace (Long placeId, final Response.Listener<List<PlaceAuthenticatedAccess>> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'placeId' is set
    if (placeId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'placeId' when calling getAccessListInPlace",
        new ApiException(400, "Missing the required parameter 'placeId' when calling getAccessListInPlace"));
    }

    // create path and map variables
    String path = "/access/place/{placeId}/authenticated".replaceAll("\\{format\\}","json").replaceAll("\\{" + "placeId" + "\\}", apiInvoker.escapeString(placeId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((List<PlaceAuthenticatedAccess>) ApiInvoker.deserialize(localVarResponse,  "array", PlaceAuthenticatedAccess.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Returns all the authenticated accesses in a place registered of one or more users (userIds are separated by commas).
  * Returns all the authenticated accesses in a place registered of one or more users (userIds are separated by commas).
   * @param userIds One or more userIds
   * @param placeId ID of a place
   * @return List<PlaceAuthenticatedAccess>
  */
  public List<PlaceAuthenticatedAccess> getAccessListInPlaceOfUsers (List<Long> userIds, Long placeId) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'userIds' is set
    if (userIds == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'userIds' when calling getAccessListInPlaceOfUsers",
        new ApiException(400, "Missing the required parameter 'userIds' when calling getAccessListInPlaceOfUsers"));
    }
    // verify the required parameter 'placeId' is set
    if (placeId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'placeId' when calling getAccessListInPlaceOfUsers",
        new ApiException(400, "Missing the required parameter 'placeId' when calling getAccessListInPlaceOfUsers"));
    }

    // create path and map variables
    String path = "/access/place/{placeId}/authenticated/{userIds}".replaceAll("\\{" + "userIds" + "\\}", apiInvoker.escapeString(userIds.toString())).replaceAll("\\{" + "placeId" + "\\}", apiInvoker.escapeString(placeId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] {  };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (List<PlaceAuthenticatedAccess>) ApiInvoker.deserialize(localVarResponse, "array", PlaceAuthenticatedAccess.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Returns all the authenticated accesses in a place registered of one or more users (userIds are separated by commas).
   * Returns all the authenticated accesses in a place registered of one or more users (userIds are separated by commas).
   * @param userIds One or more userIds   * @param placeId ID of a place
  */
  public void getAccessListInPlaceOfUsers (List<Long> userIds, Long placeId, final Response.Listener<List<PlaceAuthenticatedAccess>> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'userIds' is set
    if (userIds == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'userIds' when calling getAccessListInPlaceOfUsers",
        new ApiException(400, "Missing the required parameter 'userIds' when calling getAccessListInPlaceOfUsers"));
    }
    // verify the required parameter 'placeId' is set
    if (placeId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'placeId' when calling getAccessListInPlaceOfUsers",
        new ApiException(400, "Missing the required parameter 'placeId' when calling getAccessListInPlaceOfUsers"));
    }

    // create path and map variables
    String path = "/access/place/{placeId}/authenticated/{userIds}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "userIds" + "\\}", apiInvoker.escapeString(userIds.toString())).replaceAll("\\{" + "placeId" + "\\}", apiInvoker.escapeString(placeId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((List<PlaceAuthenticatedAccess>) ApiInvoker.deserialize(localVarResponse,  "array", PlaceAuthenticatedAccess.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
}
