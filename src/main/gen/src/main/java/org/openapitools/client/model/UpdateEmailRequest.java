/*
 * Account API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;
import org.openapitools.client.model.UpdateUserOperationType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openapitools.client.JSON;

/**
 * UpdateEmailRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-05-14T10:53:31.997379800+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class UpdateEmailRequest {
  public static final String SERIALIZED_NAME_OLD_EMAIL = "oldEmail";
  @SerializedName(SERIALIZED_NAME_OLD_EMAIL)
  private String oldEmail;

  public static final String SERIALIZED_NAME_NEW_EMAIL = "newEmail";
  @SerializedName(SERIALIZED_NAME_NEW_EMAIL)
  private String newEmail;

  public static final String SERIALIZED_NAME_OPERATION_TYPE = "operationType";
  @SerializedName(SERIALIZED_NAME_OPERATION_TYPE)
  private UpdateUserOperationType operationType;

  public UpdateEmailRequest() {
  }

  public UpdateEmailRequest oldEmail(String oldEmail) {
    this.oldEmail = oldEmail;
    return this;
  }

  /**
   * Get oldEmail
   * @return oldEmail
   */
  @javax.annotation.Nonnull
  public String getOldEmail() {
    return oldEmail;
  }

  public void setOldEmail(String oldEmail) {
    this.oldEmail = oldEmail;
  }


  public UpdateEmailRequest newEmail(String newEmail) {
    this.newEmail = newEmail;
    return this;
  }

  /**
   * Get newEmail
   * @return newEmail
   */
  @javax.annotation.Nullable
  public String getNewEmail() {
    return newEmail;
  }

  public void setNewEmail(String newEmail) {
    this.newEmail = newEmail;
  }


  public UpdateEmailRequest operationType(UpdateUserOperationType operationType) {
    this.operationType = operationType;
    return this;
  }

  /**
   * Get operationType
   * @return operationType
   */
  @javax.annotation.Nonnull
  public UpdateUserOperationType getOperationType() {
    return operationType;
  }

  public void setOperationType(UpdateUserOperationType operationType) {
    this.operationType = operationType;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateEmailRequest updateEmailRequest = (UpdateEmailRequest) o;
    return Objects.equals(this.oldEmail, updateEmailRequest.oldEmail) &&
        Objects.equals(this.newEmail, updateEmailRequest.newEmail) &&
        Objects.equals(this.operationType, updateEmailRequest.operationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(oldEmail, newEmail, operationType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateEmailRequest {\n");
    sb.append("    oldEmail: ").append(toIndentedString(oldEmail)).append("\n");
    sb.append("    newEmail: ").append(toIndentedString(newEmail)).append("\n");
    sb.append("    operationType: ").append(toIndentedString(operationType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("oldEmail");
    openapiFields.add("newEmail");
    openapiFields.add("operationType");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("oldEmail");
    openapiRequiredFields.add("operationType");
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to UpdateEmailRequest
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!UpdateEmailRequest.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in UpdateEmailRequest is not found in the empty JSON string", UpdateEmailRequest.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!UpdateEmailRequest.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `UpdateEmailRequest` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : UpdateEmailRequest.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("oldEmail").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `oldEmail` to be a primitive type in the JSON string but got `%s`", jsonObj.get("oldEmail").toString()));
      }
      if ((jsonObj.get("newEmail") != null && !jsonObj.get("newEmail").isJsonNull()) && !jsonObj.get("newEmail").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `newEmail` to be a primitive type in the JSON string but got `%s`", jsonObj.get("newEmail").toString()));
      }
      // validate the required field `operationType`
      UpdateUserOperationType.validateJsonElement(jsonObj.get("operationType"));
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!UpdateEmailRequest.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'UpdateEmailRequest' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<UpdateEmailRequest> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(UpdateEmailRequest.class));

       return (TypeAdapter<T>) new TypeAdapter<UpdateEmailRequest>() {
           @Override
           public void write(JsonWriter out, UpdateEmailRequest value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public UpdateEmailRequest read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of UpdateEmailRequest given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of UpdateEmailRequest
   * @throws IOException if the JSON string is invalid with respect to UpdateEmailRequest
   */
  public static UpdateEmailRequest fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, UpdateEmailRequest.class);
  }

  /**
   * Convert an instance of UpdateEmailRequest to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

